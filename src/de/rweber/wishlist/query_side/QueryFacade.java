package de.rweber.wishlist.query_side;

import java.util.ArrayList;
import java.util.List;
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

import de.rweber.wishlist.logging.ComplexLogMessages;
import de.rweber.wishlist.query_side.dto.WishListDto;
import de.rweber.wishlist.utils.PropertyConverter;

public class QueryFacade implements IQueryFacade {

	private static final Logger logger = Logger.getLogger( "WebPageLogger" );
	
	@Override
	public List<WishListDto> GetWishLists() 
	{
		logger.log(Level.INFO, ComplexLogMessages.queryFacadeMessage("GetWishLists"));
    	
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	Key aggregateKey = KeyFactory.createKey("WishListsView", "WishListsView");
    	
    	Query query = new Query("WishListDto", aggregateKey);
        List<Entity> storedEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        List<WishListDto> wishLists = new ArrayList<WishListDto>();
        
        for(Entity e : storedEntities)
        {
        	wishLists.add(PropertyConverter.convertBlobToObject(WishListDto.class, (Blob)e.getProperty("WishListDto")));
        }
		return wishLists;
	}
}
