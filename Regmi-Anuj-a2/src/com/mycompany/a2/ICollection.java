package com.mycompany.a2;

public interface ICollection {
	
	// add an object to the collection
	public boolean add(Object newObj);
	
	// get an iterator to move through the collection
	public IIterator getIterator();
	
	// remove the given object from the collection
	public boolean remove(Object o);
	
}
