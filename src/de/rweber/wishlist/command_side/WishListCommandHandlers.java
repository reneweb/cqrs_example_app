package de.rweber.wishlist.command_side;

import java.util.logging.Level;
import java.util.logging.Logger;

import de.rweber.wishlist.command_side.commands.AddItemToWishListCommand;
import de.rweber.wishlist.command_side.commands.ChangeNameOfWishListCommand;
import de.rweber.wishlist.command_side.commands.ClearWishListCommand;
import de.rweber.wishlist.command_side.commands.CreateWishListCommand;
import de.rweber.wishlist.command_side.commands.DeleteWishListCommand;
import de.rweber.wishlist.command_side.commands.RemoveItemFromWishListCommand;
import de.rweber.wishlist.command_side.domain_model.IRepository;
import de.rweber.wishlist.command_side.domain_model.WishList;
import de.rweber.wishlist.logging.ComplexLogMessages;

public class WishListCommandHandlers extends CommandHandler {

	private static final Logger logger = Logger.getLogger( "WebPageLogger" );
	private final IRepository<WishList> repository;
	
    public WishListCommandHandlers(IRepository<WishList> repository)
    {
        this.repository = repository;
    }
    
    public void handle(CreateWishListCommand command)
    {
    	logger.log(Level.INFO, ComplexLogMessages.commandHandlerMessage(command.getClass().getSimpleName(), "Id", command.Id.toString(), "Name", command.Name));
    	
    	WishList item = new WishList(command.Id, command.Name);
        repository.Save(item, -1);
    }
    
    public void handle(ChangeNameOfWishListCommand command)
    {
    	logger.log(Level.INFO, ComplexLogMessages.commandHandlerMessage(command.getClass().getSimpleName(), "Id", command.Id.toString(), "NewName", command.NewName));
	
    	WishList item = repository.GetById(command.Id);
        item.ChangeName(command.NewName);
        repository.Save(item, command.OriginalVersion);        
    }
    
    public void handle(ClearWishListCommand command)
    {
    	logger.log(Level.INFO, ComplexLogMessages.commandHandlerMessage(command.getClass().getSimpleName(), "Id", command.Id.toString()));
    	
    	WishList item = repository.GetById(command.Id);
        item.Clear();
        repository.Save(item, command.OriginalVersion);        
    }
    
    public void handle(DeleteWishListCommand command)
    {
    	logger.log(Level.INFO, ComplexLogMessages.commandHandlerMessage(command.getClass().getSimpleName(), "Id", command.Id.toString()));
    	
    	WishList item = repository.GetById(command.Id);
        item.Delete();
        repository.Save(item, command.OriginalVersion);        
    }
    
    public void handle(AddItemToWishListCommand command)
    {
    	logger.log(Level.INFO, ComplexLogMessages.commandHandlerMessage(command.getClass().getSimpleName(), "Id", command.Id.toString(), 
    			"ItemId", command.ItemId.toString(), "ItemName", command.ItemName, "ItemPrice", "" + command.ItemPrice));
    	
    	WishList item = repository.GetById(command.Id);
        item.AddItem(command.ItemId, command.ItemName, command.ItemPrice);
        repository.Save(item, command.OriginalVersion);        
    }
    
    public void handle(RemoveItemFromWishListCommand command)
    {
    	logger.log(Level.INFO, ComplexLogMessages.commandHandlerMessage(command.getClass().getSimpleName(), "Id", command.Id.toString(), 
    			"ItemId", command.ItemId.toString()));
    	
    	WishList item = repository.GetById(command.Id);
        item.RemoveItem(command.ItemId);
        repository.Save(item, command.OriginalVersion);        
    }
}
