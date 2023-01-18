package com.mycompany.a3;

import java.util.ArrayList;

public class GameWorldCollection {
	
	//create a array list
	private ArrayList<GameObject> gameObjects;
	
	public GameWorldCollection() {
		gameObjects = new ArrayList<GameObject>();
	}
	
	//override the add function
	public void add(GameObject newObject) {
		gameObjects.add(newObject);
	}
	
	//override the getIterator
	public IIterator getIterator() {
		return new GameWorldIterator();
	}
	
	private class GameWorldIterator implements IIterator{
		private int index;
		public GameWorldIterator() {
			index = -1;
		}
		
		//override the hasNext
		public boolean hasNext() {
			if(gameObjects.size() <= 0)
				return false;
			if(index == gameObjects.size() - 1)
				return false;
			
			return true;
		}

		@Override
		public GameObject getNext() {
			index++;
			return gameObjects.get(index);
		}

		@Override
		public GameObject getCurrent() {
			return gameObjects.get(index);
		}

		@Override
		public void remoeve(GameObject gameobjects) {
			gameObjects.remove(gameobjects);
			
		}
	}
}
