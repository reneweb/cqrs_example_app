package de.rweber.wishlist.command_side.commands;

import java.util.UUID;

public class AddItemToWishListCommand extends Command {

	public final UUID Id;
	public final UUID ItemId;
	public final String ItemName;
	public final String ItemPrice;
    public final int OriginalVersion;

    public AddItemToWishListCommand(UUID id, UUID itemId, String itemName, 
    		String itemPrice, int originalVersion)
    {
    	Id = id;
    	ItemId = itemId;
    	ItemName = itemName;
    	ItemPrice = itemPrice;
        OriginalVersion = originalVersion;
    }
}
