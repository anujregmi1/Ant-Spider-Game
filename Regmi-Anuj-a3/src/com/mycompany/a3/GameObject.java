package com.mycompany.a3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject implements ICollider{
	
	//all objects have size, color and location
	private Point location;
	private int color;
	private int size;
	private GameWorld gw;
	
	private int maxPossibleX = 1500;
	private int maxPossibleY = 1000;
	private int minPossibleX = 100;
	private int minPossibleY = 100;
	
	//array list of collisions
	private Vector<GameObject> collisions;
	
	GameObject(int color, GameWorld gw) {
		collisions = new Vector<GameObject>();
		this.color = color;
		Random rand = new Random();
		float x = (float)rand.nextInt(maxPossibleX - minPossibleX) + minPossibleX;
		float y = (float)rand.nextInt(maxPossibleY - minPossibleY) + minPossibleY;
		this.gw = gw;
		this.location = new Point(x,y);
	}
	
	//create new objects with size and color
	GameObject(int color, int size, GameWorld gw){
		collisions = new Vector<GameObject>();
		this.color = color;
		Random rand = new Random();
		float x = (float)rand.nextInt(maxPossibleX - minPossibleX) + minPossibleX;
		float y = (float)rand.nextInt(maxPossibleY - minPossibleY) + minPossibleY;
		this.gw = gw;
		this.location = new Point(x,y);
		
		this.size = size;
	}
	
	//set the x coordinate
	public void setX(float x) {
		location.setX(x);
	}
	
	//get the x coordinate
	public float getX() {
		return location.getX();
	}
	
	//set the y coordinate
	public void setY(float y) {
		location.setY(y);
	}
	
	//get the y coordinate
	public float getY() {
		return location.getY();
	}
	
	//set the color
	public void setColor(int color) {
		this.color = color;
	}
	
	//get the color
	public int getColor() {
		return color;
	}
	
	//get the size
	public int getSize() {
		return size;
	}
	
	//set the size
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setLocation(float x, float y){
		this.location = new Point(x,y);
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public String getColortoString(){
		return "["+ ColorUtil.red(this.color) + "," + ColorUtil.green(this.color) + "," + ColorUtil.blue(this.color)+"]";
	}
	
	
	//override the functions to determine the collison
	public boolean collidesWith(GameObject otherObject) {
		boolean answer = false;
		
    	//get the points of the other object
		double otherCenterX = otherObject.getX();
		double otherCenterY = otherObject.getY();
		
		//get the points of the current object and also factor in the size of the other object
		int thisCenterX = (int)this.getX();
		int thisCenterY = (int)this.getY();
		
		//difference in them
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		
		//radius calculation
		int thisRadius = this.getSize() / 2;
		int otherRadius = otherObject.getSize() / 2;
		int radiiSqr = (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if(distBetweenCentersSqr <= radiiSqr) {
			answer = true;
		}
		
		return answer;
		
	}
	
	
	public GameWorld getGW() {
		return gw;
	}
	
	public void setVector(GameObject collidedObject) {
		collisions.add(collidedObject);
	}
	
	public Vector<GameObject> getCollidedObjects(){
		return collisions;
	}
}
