package de.rweber.wishlist.ui_interface;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.com.google.common.base.Throwables;

import de.rweber.wishlist.ServiceBus;
import de.rweber.wishlist.command_side.commands.AddItemToWishListCommand;
import de.rweber.wishlist.command_side.commands.ChangeNameOfWishListCommand;
import de.rweber.wishlist.command_side.commands.ClearWishListCommand;
import de.rweber.wishlist.command_side.commands.CreateWishListCommand;
import de.rweber.wishlist.command_side.commands.DeleteWishListCommand;
import de.rweber.wishlist.command_side.commands.RemoveItemFromWishListCommand;
import de.rweber.wishlist.event_store.EventStore;
import de.rweber.wishlist.logging.WebPageHandler;
import de.rweber.wishlist.query_side.IQueryFacade;
import de.rweber.wishlist.query_side.QueryFacade;
import de.rweber.wishlist.query_side.dto.WishListDto;

@SuppressWarnings("serial")
public class WishlistServlet extends HttpServlet {
	
	//Get wishlist
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		prepareAndSendRedirect(req, resp, null);
	}
	
	//Create wish list, change wish list (change name, add item to wish list, clear wish list, remove item from wish list),
	//delete wish list
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{		
		try
		{
			processPost(req, resp);
		}
		catch(Exception e)
		{
			prepareAndSendRedirect(req, resp, Throwables.getRootCause(e).getMessage());
		}
	}
	
	private void processPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		ServiceBus bus = ServiceBus.getServiceBus();
		
		if(((String)req.getParameter("_method")).equals("post"))
		{
			CreateWishListCommand createWishListCommand = new CreateWishListCommand(UUID.randomUUID(), "Wish list");
			bus.Send(createWishListCommand);
		}
		else if(((String)req.getParameter("_method")).equals("put"))
		{
			UUID id = UUID.fromString(req.getParameter("wishlist-id"));
			
			@SuppressWarnings("unchecked")
			int version = getCurrentVersionOfAggregate((List<WishListDto>)req.getSession().getAttribute("wishLists"), id);
			
			switch(Integer.parseInt((String)req.getParameter("change-action")))
			{
				case 0: //Change name
					ChangeNameOfWishListCommand changeNameOfWishListCommand = new ChangeNameOfWishListCommand(id, req.getParameter("new-name"), version);
					bus.Send(changeNameOfWishListCommand);
					break;
				case 1: //Clear
					ClearWishListCommand clearWishListCommand = new ClearWishListCommand(id, version);
					bus.Send(clearWishListCommand);
					break;
				case 2: //Add item
					AddItemToWishListCommand addItemToWishListCommand = new AddItemToWishListCommand(id, UUID.randomUUID(), req.getParameter("item-name"), req.getParameter("item-price"), version);
					bus.Send(addItemToWishListCommand);
					break;
				case 3: //Delete item
					RemoveItemFromWishListCommand removeItemFromWishListCommand = new RemoveItemFromWishListCommand(id, UUID.fromString(req.getParameter("item-id")), version);
					bus.Send(removeItemFromWishListCommand);
					break;
			}
		}
		else
		{
			UUID id = UUID.fromString(req.getParameter("wishlist-id"));
			
			@SuppressWarnings("unchecked")
			int version = getCurrentVersionOfAggregate((List<WishListDto>)req.getSession().getAttribute("wishLists"), id);
			
			DeleteWishListCommand deleteWishListCommand = new DeleteWishListCommand(id, version);
			bus.Send(deleteWishListCommand);
		}
		
		prepareAndSendRedirect(req, resp, null);
	}

	private void prepareAndSendRedirect(HttpServletRequest req, HttpServletResponse resp, String errMsg) throws ServletException, IOException
	{
		IQueryFacade queryFacade = new QueryFacade();
		List<WishListDto> wishLists = queryFacade.GetWishLists();
		
		req.getSession().setAttribute("wishLists", wishLists);
		req.setAttribute("status", errMsg);
		req.setAttribute("webLog", WebPageHandler.getWebPageLog());
		req.setAttribute("eventStoreLog", EventStore.GetEvents());
		
		req.getRequestDispatcher("/index.jsp").forward(req, resp); 
	}
	
	private int getCurrentVersionOfAggregate(List<WishListDto> wishLists, UUID id) 
	{
		int version = 0;
		
		for(WishListDto wld : wishLists)
		{
			if(wld.getId().equals(id))
			{
				version = wld.getVersion();
			}
		}
		
		return version;
	}
}
