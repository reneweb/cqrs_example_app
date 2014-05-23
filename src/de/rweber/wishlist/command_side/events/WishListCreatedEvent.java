package de.rweber.wishlist.command_side.events;

import java.util.UUID;

public class WishListCreatedEvent extends Event {

	public final String Name;

    public WishListCreatedEvent(UUID id, String name)
    {
    	super(id);
    	Name = name;
    }
}
