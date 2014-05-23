package de.rweber.wishlist.command_side.commands;

import java.util.UUID;

public class ClearWishListCommand extends Command {

	public final UUID Id;
    public final int OriginalVersion;

    public ClearWishListCommand(UUID id, int originalVersion)
    {
    	Id = id;
        OriginalVersion = originalVersion;
    }
}
