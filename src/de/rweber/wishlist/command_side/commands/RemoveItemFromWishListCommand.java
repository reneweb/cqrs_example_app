package de.rweber.wishlist.command_side.commands;

import java.util.UUID;

public class RemoveItemFromWishListCommand {

	public final UUID Id;
	public final UUID ItemId;
	public final int OriginalVersion;
    
    public RemoveItemFromWishListCommand(UUID id, UUID itemId, int originalVersion)
    {
    	Id = id;
    	ItemId = itemId;
    	OriginalVersion = originalVersion;
    }
}
