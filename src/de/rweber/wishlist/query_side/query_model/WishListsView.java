package de.rweber.wishlist.query_side.query_model;

import java.util.ArrayList;
import java.util.List;
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

import de.rweber.wishlist.command_side.events.ItemAddedToWishListEvent;
import de.rweber.wishlist.command_side.events.ItemRemovedFromWishListEvent;
import de.rweber.wishlist.command_side.events.NameOfWishListChangedEvent;
import de.rweber.wishlist.command_side.events.WishListClearedEvent;
import de.rweber.wishlist.command_side.events.WishListCreatedEvent;
import de.rweber.wishlist.command_side.events.WishListDeletedEvent;
import de.rweber.wishlist.logging.ComplexLogMessages;
import de.rweber.wishlist.query_side.dto.WishListDto;
import de.rweber.wishlist.utils.PropertyConverter;
import de.rweber.wishlist.utils.Triple;

public class WishListsView implements IEventHandler {
	
	private static final Logger logger = Logger.getLogger( "WebPageLogger" );
	
	public void handle(WishListCreatedEvent event)
	{
		logger.log(Level.INFO, ComplexLogMessages.eventHandlerMessage(event.getClass().getSimpleName(), "Id", event.Id.toString(), "Name", event.Name, "Version", "" + event.Version));
    	
    	Key aggregateKey = KeyFactory.createKey("WishListsView", "WishListsView");
    	
    	Blob wishListDtoBlob = PropertyConverter.convertObjectToBlob(new WishListDto(event.Id, event.Name, new ArrayList<Triple<UUID, String, Double>>(), 0));
    	
    	Entity entity = new Entity("WishListDto", aggregateKey);
    	entity.setProperty("Id", event.Id.toString());
    	entity.setProperty("WishListDto", wishListDtoBlob);

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	datastore.put(entity);
	}
	
	public void handle(NameOfWishListChangedEvent event)
	{
		logger.log(Level.INFO, ComplexLogMessages.eventHandlerMessage(event.getClass().getSimpleName(), "Id", event.Id.toString(), "NewName", event.NewName, "Version", "" + event.Version));
    	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListsView", "WishListsView");
    	
    	Query query = new Query("WishListDto", aggregateKey);
        List<Entity> storedEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        for(Entity e : storedEntities)
        {
        	if(UUID.fromString((String)e.getProperty("Id")).equals(event.Id))
        	{
        		WishListDto wishListDto = PropertyConverter.convertBlobToObject(WishListDto.class, (Blob)e.getProperty("WishListDto"));
        		wishListDto.setName(event.NewName);
        		wishListDto.setVersion(event.Version);
        		
        		e.setProperty("WishListDto", PropertyConverter.convertObjectToBlob(wishListDto));
        		datastore.put(e);
        		break;
        	}
        }
	}
	
	public void handle(WishListClearedEvent event)
	{
		logger.log(Level.INFO, ComplexLogMessages.eventHandlerMessage(event.getClass().getSimpleName(), "Id", event.Id.toString(), "Version", "" + event.Version));
    	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListsView", "WishListsView");
    	
    	Query query = new Query("WishListDto", aggregateKey);
        List<Entity> storedEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        for(Entity e : storedEntities)
        {
        	if(UUID.fromString((String)e.getProperty("Id")).equals(event.Id))
        	{
        		WishListDto wishListDto = PropertyConverter.convertBlobToObject(WishListDto.class, (Blob)e.getProperty("WishListDto"));
        		wishListDto.getItems().clear();
        		wishListDto.setVersion(event.Version);
        		
        		e.setProperty("WishListDto", PropertyConverter.convertObjectToBlob(wishListDto));
        		datastore.put(e);
        		break;
        	}
        }
	}
	
	public void handle(WishListDeletedEvent event)
	{
		logger.log(Level.INFO, ComplexLogMessages.eventHandlerMessage(event.getClass().getSimpleName(), "Id", event.Id.toString(), "Version", "" + event.Version));
    	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListsView", "WishListsView");
    	
    	Query query = new Query("WishListDto", aggregateKey);
        List<Entity> storedEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        for(Entity e : storedEntities)
        {
        	if(UUID.fromString((String)e.getProperty("Id")).equals(event.Id))
        	{
        		datastore.delete(e.getKey());
        		break;
        	}
        }
	}
	
	public void handle(ItemAddedToWishListEvent event)
	{
		logger.log(Level.INFO, ComplexLogMessages.eventHandlerMessage(event.getClass().getSimpleName(), "Id", event.Id.toString(), "ItemId", 
				event.ItemId.toString(), "ItemName", event.ItemName, "ItemPrice", "" + event.ItemPrice, "Version", "" + event.Version));
    	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListsView", "WishListsView");
    	
    	Query query = new Query("WishListDto", aggregateKey);
        List<Entity> storedEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        for(Entity e : storedEntities)
        {
        	if(UUID.fromString((String)e.getProperty("Id")).equals(event.Id))
        	{
        		WishListDto wishListDto = PropertyConverter.convertBlobToObject(WishListDto.class, (Blob)e.getProperty("WishListDto"));
        		wishListDto.getItems().add(new Triple<UUID, String, Double>(event.ItemId, event.ItemName, event.ItemPrice));
        		wishListDto.setVersion(event.Version);
        		
        		e.setProperty("WishListDto", PropertyConverter.convertObjectToBlob(wishListDto));
        		datastore.put(e);
        		break;
        	}
        }
	}
	
	public void handle(ItemRemovedFromWishListEvent event)
	{
		logger.log(Level.INFO, ComplexLogMessages.eventHandlerMessage(event.getClass().getSimpleName(), "Id", event.Id.toString(), "ItemId", event.ItemId.toString(), "Version", "" + event.Version));
    	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListsView", "WishListsView");
    	
    	Query query = new Query("WishListDto", aggregateKey);
        List<Entity> storedEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        for(Entity e : storedEntities)
        {
        	if(UUID.fromString((String)e.getProperty("Id")).equals(event.Id))
        	{
        		WishListDto wishListDto = PropertyConverter.convertBlobToObject(WishListDto.class, (Blob)e.getProperty("WishListDto"));
        		wishListDto.setVersion(event.Version);
        		for(int i = 0; i < wishListDto.getItems().size(); i++ )
        		{
        			Triple<UUID, String, Double> p = wishListDto.getItems().get(i);
        			if(p.getElement1().equals(event.ItemId))
        			{
        				wishListDto.getItems().remove(i);
        				break;
        			}
        		}
        		
        		e.setProperty("WishListDto", PropertyConverter.convertObjectToBlob(wishListDto));
        		datastore.put(e);
        		break;
        	}
        }
	}
}