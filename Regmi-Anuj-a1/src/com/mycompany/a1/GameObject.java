package com.mycompany.a1;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	
	//location, color and size for objects
	private FloatPoint location;
	private int color;
	private int size;
	
	public Random rand = new Random();
	
	//initialize all the game objects
	GameObject() {
		
		double x = Math.round((1025.0 * rand.nextDouble() * 10.0)) / 10.0;
		double y = Math.round((1025.0 * rand.nextDouble() * 10.0)) / 10.0;
		
		FloatPoint p = new FloatPoint(x,y);
		int c = ColorUtil.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		
		location = p;
		color = c;
		
//		size = rand.nextInt(41) + 10;
	}
	
	//create new objects with points and color
	GameObject(FloatPoint p, int c){
		location = p;
		color = c;
	}
	
	public abstract String toString();
	
	//current location of the object
	public FloatPoint getLocation() {
		return location;
	}
	
	//set new location with given points
	public void setLocation(FloatPoint newLoc) {
		location = newLoc;
	}
	
	//move the object the given amount in x and t direction
	public void move(Double deltaX, Double deltaY) {
		
		Double newX = location.getX() + deltaX;
		Double newY = location.getY() + deltaY;
		
		location.setLocation(newX, newY);
		
	}
	
	//get the color
	public int getColor() {
		return color;
	}
	
	//setColor
	public void setColor(int r, int g, int b) {
		color = ColorUtil.rgb(r, g, b);
	}
	
	//set new random color
	public void setRandomColor() {
		
		int c = ColorUtil.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
		setColor(ColorUtil.red(c),ColorUtil.green(c),ColorUtil.blue(c));
	}
	
	//random integer for init
	public int randInt(int min, int max) {
		int theNum;
		Random numGen = new Random();
		theNum = numGen.nextInt(max - min + 1);
		theNum += min;
		return theNum;
		
	}
}
