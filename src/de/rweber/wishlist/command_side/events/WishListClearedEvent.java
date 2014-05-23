package de.rweber.wishlist.command_side.events;

import java.util.UUID;

public class WishListClearedEvent extends Event {

    public WishListClearedEvent(UUID id)
    {
    	super(id);
    }
}
