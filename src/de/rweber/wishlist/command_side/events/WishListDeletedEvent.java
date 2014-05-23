package de.rweber.wishlist.command_side.events;

import java.util.UUID;

public class WishListDeletedEvent extends Event {

    public WishListDeletedEvent(UUID id)
    {
    	super(id);
    }
}
