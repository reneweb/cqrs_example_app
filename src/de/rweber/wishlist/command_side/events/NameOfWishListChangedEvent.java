package de.rweber.wishlist.command_side.events;

import java.util.UUID;

public class NameOfWishListChangedEvent extends Event {

	public final String NewName;

    public NameOfWishListChangedEvent(UUID id, String newName)
    {
    	super(id);
    	NewName = newName;
    }
}
