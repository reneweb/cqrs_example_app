package de.rweber.wishlist;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import de.rweber.wishlist.command_side.CommandHandler;
import de.rweber.wishlist.command_side.WishListCommandHandlers;
import de.rweber.wishlist.command_side.domain_model.IRepository;
import de.rweber.wishlist.command_side.domain_model.Repository;
import de.rweber.wishlist.command_side.domain_model.WishList;
import de.rweber.wishlist.event_store.EventStore;
import de.rweber.wishlist.event_store.IEventStore;
import de.rweber.wishlist.query_side.query_model.IEventHandler;
import de.rweber.wishlist.query_side.query_model.WishListsView;

public class InitServlet implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		IEventStore storage = new EventStore();
		IRepository<WishList> rep = Repository.getRepository(WishList.class, storage);
		WishListCommandHandlers wishListCommandHandlers = new WishListCommandHandlers(rep);
		WishListsView wishListsView = new WishListsView();
		
		List<CommandHandler> commandHandlers = new ArrayList<CommandHandler>();
		commandHandlers.add(wishListCommandHandlers);
		
		List<IEventHandler> eventHandlers = new ArrayList<IEventHandler>();
		eventHandlers.add(wishListsView);
        
		ServiceBus.createServiceBus(commandHandlers, eventHandlers);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) 
	{
		// Nothing to do here
	}
}
