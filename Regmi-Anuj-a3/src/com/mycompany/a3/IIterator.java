package com.mycompany.a3;

public interface IIterator {
	
	//check if there are elements in the list
	public boolean hasNext();
	
	//return the next element of the list
	public GameObject getNext();
	
	//return the current element
	public GameObject getCurrent();
	
	//remove the current element
	public void remoeve(GameObject gameobjects);
}
