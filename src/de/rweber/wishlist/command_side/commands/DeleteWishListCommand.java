package de.rweber.wishlist.command_side.commands;

import java.util.UUID;


public class DeleteWishListCommand extends Command {
	
	public final UUID Id;
    public final int OriginalVersion;

    public DeleteWishListCommand(UUID id, int originalVersion)
    {
    	Id = id;
        OriginalVersion = originalVersion;
    }

}
