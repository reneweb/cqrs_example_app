package de.rweber.wishlist.utils;

import java.io.Serializable;

public class Triple<K, T, E> implements Serializable {

	private static final long serialVersionUID = -6759411693449214925L;
	
	private final K element1;
	private final T element2;
	private final E element3;

	public Triple(K element1, T element2, E element3) 
	{
		this.element1 = element1;
		this.element2 = element2;
		this.element3 = element3;
	}

	public K getElement1() {
		return element1;
	}

	public T getElement2() {
		return element2;
	}

	public E getElement3() {
		return element3;
	}
}
