package com.mycompany.a3;

public interface ICollection {
	
	//add new objects
	public void add(GameObject object);
	
	//return iterator
	IIterator getIterator();
}
