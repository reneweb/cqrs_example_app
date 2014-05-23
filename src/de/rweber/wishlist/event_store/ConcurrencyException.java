package de.rweber.wishlist.event_store;

public class ConcurrencyException extends RuntimeException {

	private static final long serialVersionUID = -1732806269481626796L;

	public ConcurrencyException(String message) 
	{
		super(message);
	}
}
