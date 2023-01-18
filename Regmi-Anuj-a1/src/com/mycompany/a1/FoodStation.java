package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed{
	
	private int size;
	private int capacity;
	
	
	FoodStation() {
		super();
		
		//random size
		size = rand.nextInt((50 - 10) + 1) + 10;
		setSize(size);
		
		//initial capacity same as size of the foodStation
		setCapacity(size);
		
		//set the initial color
		setColor(0,255,0);
	}
	
	FoodStation(FloatPoint p, int c){
		super(p, c);
		
		capacity = rand.nextInt((50 - 10) + 1) + 10;
	}
	
	//getter methods for capacity and size
	
	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity(int c) {
		capacity = c;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int s) {
		size = s;
	}
	
	public String toString() {
		
		String foodStationDetail = "FoodStation: ";
		
		// add details to the string for...
		
		// location
		foodStationDetail += "loc=";
		foodStationDetail += this.getLocation().getX() + "," + this.getLocation().getY() + " ";
		
		// color
		foodStationDetail += "color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "] ";
		
		// size
		foodStationDetail += "size=" + this.getSize() + " ";
		
		//capacity
		foodStationDetail += "capacity=" + this.getCapacity();
		
		// return the fuel can details
		return foodStationDetail;
	}
}
