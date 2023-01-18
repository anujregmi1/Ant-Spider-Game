package com.mycompany.a2;

public interface IIterator {
	
	// does the collection have another one?
	public boolean hasNext();
	
	// return the next object in the collection
	public Object next();
	
}
