package de.rweber.wishlist.command_side.events;

import java.util.UUID;

public class ItemRemovedFromWishListEvent extends Event {

	public final UUID ItemId;
	
	public ItemRemovedFromWishListEvent(UUID id, UUID itemId) 
	{
		super(id);
    	ItemId = itemId;
	}

}
