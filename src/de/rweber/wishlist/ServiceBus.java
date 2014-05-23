package de.rweber.wishlist;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.rweber.wishlist.command_side.CommandHandler;
import de.rweber.wishlist.logging.ComplexLogMessages;
import de.rweber.wishlist.query_side.query_model.IEventHandler;

public class ServiceBus {
	
	private static final Logger logger = Logger.getLogger( "WebPageLogger" );
	
	private static ServiceBus serviceBus;
	
	public static void createServiceBus(List<CommandHandler> commandHandlers, List<IEventHandler> eventHandlers) 
	{
		serviceBus = new ServiceBus(commandHandlers, eventHandlers);
	}
	
	public static ServiceBus getServiceBus()
	{
		if(serviceBus == null)
			throw new IllegalStateException("Service bus not initialized");
		return serviceBus;
	}
	
	private final List<CommandHandler> commandHandlers;
	private final List<IEventHandler> eventHandlers;
	
	private ServiceBus(List<CommandHandler> commandHandlers, List<IEventHandler> eventHandlers) 
	{
		this.commandHandlers = commandHandlers;
		this.eventHandlers = eventHandlers;
	}

    //Send command to appropriate command handler
    public <T>void Send(T command)
    {    	
    	for(CommandHandler handler : commandHandlers)
    	{
    		try 
    		{
    			Method method = handler.getClass().getDeclaredMethod("handle", command.getClass());
    			method.invoke(handler, command);
    		} 
    		catch(NoSuchMethodException nsme)
    		{
    			throw new IllegalStateException("No appropriate handler registered");
    		}
    		catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
    			
    			e.printStackTrace();
    			throw new RuntimeException(e);
    		}
    	}
    }

    //Publish events
    public <T>void Publish(T event)
    {
    	logger.log(Level.INFO, ComplexLogMessages.eventPublisherMessage(event.getClass().getSimpleName()));
    	    	
    	for(IEventHandler handler : eventHandlers)
    	{
    		try 
    		{
    			Method method = handler.getClass().getDeclaredMethod("handle", event.getClass());
    			method.invoke(handler, event);
    		} 
    		catch(NoSuchMethodException nsme)
    		{
    			throw new IllegalStateException("No appropriate handler registered");
    		}
    		catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
    			
    			e.printStackTrace();
    			throw new RuntimeException(e);
    		}
    	}
    }
}
