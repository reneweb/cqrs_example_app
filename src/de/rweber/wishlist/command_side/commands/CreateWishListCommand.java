package de.rweber.wishlist.command_side.commands;

import java.util.UUID;

public class CreateWishListCommand extends Command {

	public final UUID Id;
	public final String Name;

    public CreateWishListCommand(UUID id, String name)
    {
    	Id = id;
    	Name = name;
    }
}
