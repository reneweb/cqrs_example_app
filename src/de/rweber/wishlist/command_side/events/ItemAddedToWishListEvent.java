package de.rweber.wishlist.command_side.events;

import java.util.UUID;

public class ItemAddedToWishListEvent extends Event{

	public final UUID ItemId;
	public final String ItemName;
	public final double ItemPrice;

    public ItemAddedToWishListEvent(UUID id, UUID itemId, String itemName, double itemPrice)
    {
    	super(id);
    	ItemId = itemId;
    	ItemName = itemName;
    	ItemPrice = itemPrice;
    }
}
