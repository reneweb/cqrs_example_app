package de.rweber.wishlist.event_store;

public class AggregateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8035302349326831639L;

	public AggregateNotFoundException(String message) 
	{
		super(message);
	}
}
