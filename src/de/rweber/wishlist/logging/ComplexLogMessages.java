package de.rweber.wishlist.logging;

import java.util.List;

import de.rweber.wishlist.command_side.events.Event;


public class ComplexLogMessages {

	public static String commandHandlerMessage(String handlerName, String... params)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>Command handler received command \"" + handlerName + "\"</p>");
		sb.append("<p style='text-decoration:underline;'>Parameters:</p>");
		
		sb = addParamsToSb(sb, params);
		
		return sb.toString();
	}
	
	public static String domainModelMessage(String message)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>Domain model received call from command handler</p>");
		sb.append("<p>" + message + "</p>");
		
		return sb.toString();
	}
	
	public static String eventStoreWriteMessage(List<Event> events, String... params)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>Saving events into Event Store</p>");
		sb.append("<p style='text-decoration:underline;'>Parameters:</p>");
		
		sb = addParamsToSb(sb, params);
		
		sb.append("<p style='text-decoration:underline;'>Events:</p>");
		
		for(Event e: events)
		{
			sb.append("<p>" + e.getClass().getSimpleName() + "</p>");
		}
		
		return sb.toString();
	}
	
	public static String eventStoreConcurrencyMessage(String... params)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>ConcurrencyException</p>");
		sb.append("<p>The given version is older than the current one.</p>");
		sb.append("<p style='text-decoration:underline;'>Parameters:</p>");
		
		sb = addParamsToSb(sb, params);
		
		return sb.toString();
	}
	
	public static String eventStoreReadMessage(String... params)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>Read from Event Store</p>");
		sb.append("<p style='text-decoration:underline;'>Parameters:</p>");
		
		sb = addParamsToSb(sb, params);
		
		return sb.toString();
	}
	
	public static String eventPublisherMessage(String eventName)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>Publish event:</p>");
		sb.append("<p>" + eventName + "</p>");
		
		return sb.toString();
	}
	
	public static String eventHandlerMessage(String eventName, String... params)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>Query model received event: " + eventName + "</p>");
		sb.append("<p style='text-decoration:underline;'>Parameters:</p>");
		
		sb = addParamsToSb(sb, params);
		
		return sb.toString();
	}
	
	public static String queryFacadeMessage(String calledMethod)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>Query facade received query</p>");
		sb.append("<p>" + calledMethod + "</p>");
		
		return sb.toString();
	}
	
	public static String exceptionMessage(String exType, String exMessage)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<p style='font-weight: bold;'>" + exType + "</p>");
		sb.append("<p>" + exMessage + "</p>");
		
		return sb.toString();
	}
	
	private static StringBuilder addParamsToSb(StringBuilder sb, String... params)
	{
		for(int i=0; i<params.length; i++)
		{
			if(i % 2 == 0)
				sb.append("<p>" + params[i] + ": "); //Identifier
			else
				sb.append(params[i] + "</p>"); //Value
		}
		
		return sb;
	}
}
