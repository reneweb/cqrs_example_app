package de.rweber.wishlist.event_store;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;

import de.rweber.wishlist.ServiceBus;
import de.rweber.wishlist.command_side.events.Event;
import de.rweber.wishlist.logging.ComplexLogMessages;
import de.rweber.wishlist.utils.PropertyConverter;

public class EventStore implements IEventStore {

	private static final Logger logger = Logger.getLogger( "WebPageLogger" );
	
    public EventStore()
    {
    }
    
    public void SaveEvents(UUID aggregateId, List<Event> events, int expectedVersion)
    {
    	logger.log(Level.INFO, ComplexLogMessages.eventStoreWriteMessage(events, "AggregateId", aggregateId.toString()));
    	
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListEvents", aggregateId.toString());

    	Query query = new Query("WishListEvent", aggregateKey);
        List<Entity> storedEvents = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
    	
        if(!storedEvents.isEmpty())
        {
        	if((long)storedEvents.get(storedEvents.size() - 1).getProperty("Version") != expectedVersion && expectedVersion != -1)
	        {
        		logger.log(Level.WARNING, ComplexLogMessages.eventStoreConcurrencyMessage("AggregateId", aggregateId.toString()));
        		throw new ConcurrencyException("Concurrency problem: The data is outdated and thus the action could not be completed. Please refresh the page to get the most recent data.");
	        }
        }

		int i = expectedVersion;
        
        for(Event event : events)
        {
            i++;
            event.Version = i;
            
            Entity eventEntity = new Entity("WishListEvent", aggregateKey);
            eventEntity.setProperty("Id", aggregateId.toString());
            eventEntity.setProperty("EventData", PropertyConverter.convertObjectToBlob(event));
            eventEntity.setProperty("Version", i);
            eventEntity.setProperty("Date", new Date()); // Only used to sort data for log
            
            datastore.put(eventEntity);
            
            ServiceBus.getServiceBus().Publish(event);
        }	
    }
    
    public  List<Event> GetEventsForAggregate(UUID aggregateId)
    {
    	logger.log(Level.INFO, ComplexLogMessages.eventStoreReadMessage("AggregateId", aggregateId.toString()));
    	
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListEvents", aggregateId.toString());
    	
    	Query query = new Query("WishListEvent", aggregateKey);
        List<Entity> storedEvents = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        ArrayList<Event> eventData = new ArrayList<Event>();
    	for(Entity e : storedEvents)
    	{
    		eventData.add(PropertyConverter.convertBlobToObject(Event.class, (Blob)e.getProperty("EventData")));
    	}
    	
    	if(eventData.isEmpty())
    	{
    		logger.log(Level.WARNING, ComplexLogMessages.exceptionMessage("AggregateNotFoundException", "Aggregate was not found."));
    		throw new AggregateNotFoundException("Aggregate was not found.");
    	}
    	
        return eventData;
    }
    
    //For event store output
    public static Map<UUID, List<Event>> GetEvents()
    {
    	Map<UUID, List<Event>> aggregates = new LinkedHashMap<UUID, List<Event>>();
    	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    	Query query = new Query("WishListEvent").addSort("Date", Query.SortDirection.DESCENDING);
        List<Entity> storedEvents = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
    	for(Entity e : storedEvents)
    	{
    		Event event = PropertyConverter.convertBlobToObject(Event.class, (Blob)e.getProperty("EventData"));    		
    		
    		if(aggregates.containsKey(event.Id))
    		{
    			List<Event> eventData = aggregates.get(event.Id);
    			eventData.add(0, event);
    		}
    		else
    		{
    			List<Event> savedEventData = new ArrayList<Event>();
    			savedEventData.add(event);
    			aggregates.put(event.Id, savedEventData);
    		}
    	}
    	
        return aggregates;
    }
}
