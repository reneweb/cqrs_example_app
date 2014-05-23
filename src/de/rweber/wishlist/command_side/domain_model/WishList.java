package de.rweber.wishlist.command_side.domain_model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.rweber.wishlist.command_side.events.Event;
import de.rweber.wishlist.command_side.events.ItemAddedToWishListEvent;
import de.rweber.wishlist.command_side.events.ItemRemovedFromWishListEvent;
import de.rweber.wishlist.command_side.events.NameOfWishListChangedEvent;
import de.rweber.wishlist.command_side.events.WishListClearedEvent;
import de.rweber.wishlist.command_side.events.WishListCreatedEvent;
import de.rweber.wishlist.command_side.events.WishListDeletedEvent;
import de.rweber.wishlist.logging.ComplexLogMessages;

public class WishList extends AggregateRoot {
	
	private static final Logger logger = Logger.getLogger( "WebPageLogger" );
	
	private UUID id;
	private List<UUID> items;
	@SuppressWarnings("unused")
	private boolean deleted = false;
	
	WishList()
	{
		
	}

	public WishList(UUID id, String name) 
	{
		logger.log(Level.INFO, ComplexLogMessages.domainModelMessage("Create wish list"));
    	
		applyChange(new WishListCreatedEvent(id, name));
	}

	public void ChangeName(String newName) 
	{
		if (newName == null || newName.isEmpty()) 
		{
			logger.log(Level.WARNING, ComplexLogMessages.exceptionMessage("IllegalArgumentException", "The new name is null or empty"));
			throw new IllegalArgumentException("The name you specified for the wish list is not valid. It must be non-empty.");
		}
		
		logger.log(Level.INFO, ComplexLogMessages.domainModelMessage("Change Name"));
    	
        applyChange(new NameOfWishListChangedEvent(id, newName));
	}

	public void Clear() 
	{
		logger.log(Level.INFO, ComplexLogMessages.domainModelMessage("Clear wish list"));
    	
		applyChange(new WishListClearedEvent(id));
	}

	public void Delete() 
	{
		logger.log(Level.INFO, ComplexLogMessages.domainModelMessage("Delete wish list"));
    	
		applyChange(new WishListDeletedEvent(id));
		
	}

	public void AddItem(UUID itemId, String itemName, String itemPrice) 
	{
		if (itemName == null || itemName.isEmpty()) 
		{
			logger.log(Level.WARNING, ComplexLogMessages.exceptionMessage("IllegalArgumentException", "The the item name is null or empty"));
			throw new IllegalArgumentException("The name you specified for the item is not valid. It must be non-empty.");
		}
		
		try
		{
			double itemPriceDouble = Double.parseDouble(itemPrice);
			
			if (itemPriceDouble < 0) 
			{
				logger.log(Level.WARNING, ComplexLogMessages.exceptionMessage("IllegalArgumentException", "The price is invalid (negative)"));
				throw new IllegalArgumentException("The price you specified for the item is not valid. It must be a positive floating-point number.");	
			}
				
			logger.log(Level.INFO, ComplexLogMessages.domainModelMessage("Add item to wish list"));
	    	
			applyChange(new ItemAddedToWishListEvent(id, itemId, itemName, itemPriceDouble));
		}
		catch(NumberFormatException nfe)
		{
			logger.log(Level.WARNING, ComplexLogMessages.exceptionMessage("IllegalArgumentException", "The price is not a floating-point number"));
			throw new IllegalArgumentException("The price you specified for the item is not valid. It must be a positive floating-point number.");
		}
		
	}

	public void RemoveItem(UUID itemId) 
	{
		if(!items.contains(itemId))
		{
			logger.log(Level.WARNING, ComplexLogMessages.exceptionMessage("IllegalArgumentException", "The wish list does not contain the specified item."));
			throw new IllegalArgumentException("The wish list does not contain the specified item.");
		}
		
		logger.log(Level.INFO, ComplexLogMessages.domainModelMessage("Remove item from wish list"));
    	
		applyChange(new ItemRemovedFromWishListEvent(id, itemId));
	}

	@Override
	public void loadsFromHistory(List<Event> history) 
	{
		for (Event e : history) 
        	applyChange(e, false);
	}
	
	protected void applyChange(Event event) 
	{
		applyChange(event, true);
	}

	@Override
	protected void applyChange(Event event, boolean isNew) 
	{
		try 
		{
			Method method = this.getClass().getDeclaredMethod("apply", event.getClass());
			method.invoke(this, event);
		} 
		catch(NoSuchMethodException nsme)
		{
			//If method is not found, we assume that the event doesn't need to be applied to the domain model.
		}
		catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		if(isNew)
			super.applyChange(event, isNew);
	}

	@SuppressWarnings("unused")
	private void apply(WishListCreatedEvent createdEvent)
	{
		this.id = createdEvent.Id;
	}
	
	@SuppressWarnings("unused")
	private void apply(WishListClearedEvent clearedEvent)
	{
		if(items != null)
			items.clear();
	}
	
	@SuppressWarnings("unused")
	private void apply(WishListDeletedEvent deletedEvent)
	{
		deleted = true;
	}
	
	@SuppressWarnings("unused")
	private void apply(ItemAddedToWishListEvent itemAddedEvent)
	{
		if(items == null)
			items = new ArrayList<>();
		
		items.add(itemAddedEvent.ItemId);
	}
	
	@SuppressWarnings("unused")
	private void apply(ItemRemovedFromWishListEvent itemRemovedEvent)
	{
		if(items != null)
			items.remove(itemRemovedEvent.ItemId);
	}
	
	@Override
	public UUID getId() 
	{
		return id;
	}
}
