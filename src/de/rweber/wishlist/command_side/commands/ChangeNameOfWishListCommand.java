package de.rweber.wishlist.command_side.commands;

import java.util.UUID;

public class ChangeNameOfWishListCommand extends Command {

	public final UUID Id;
	public final String NewName;
    public final int OriginalVersion;

    public ChangeNameOfWishListCommand(UUID id, String newName, int originalVersion)
    {
    	Id = id;
    	NewName = newName;
        OriginalVersion = originalVersion;
    }
}
