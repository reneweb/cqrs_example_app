package de.rweber.wishlist.command_side.events;

import java.io.Serializable;
import java.util.UUID;

public class Event implements Serializable {

	public final UUID Id;
	public int Version;
	
	protected Event(UUID id) 
	{
		Id = id;
	}
}
