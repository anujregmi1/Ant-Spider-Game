package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Ant extends Moveable implements ISteerable{
	
	private int size;
	private int maxSpeed;
	private int foodConsumptionRate;
	private int foodLevel;
	private int healthLevel;
	
	Ant(FloatPoint p, int c, int h, int s){
		super(p, c, h, s);
		
		size = 10;
		maxSpeed = 65;
		foodLevel = 10;
		foodConsumptionRate = 1;
		healthLevel = 10;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public int getHealthLevel() {
		return healthLevel;
	}
	
	public int getFoodLevel() {
		return foodLevel;
	}
	
	//feed the ant in the foodStation
	public void addFood(int moreFood) {
		foodLevel += moreFood;
	}
	
	//decrease the healthlevel after collison
	public void collisonWithSpider() {
		if(getHealthLevel() > 0) {
			healthLevel -= 1;
		}
		
		if(getHealthLevel() < 0) {
			healthLevel = 0;
		}
		
		this.setColor(ColorUtil.red(getColor()) - 5, ColorUtil.green(getColor()), ColorUtil.blue(getColor()));
		checkSpeed();
	}
	
	//check and adjust the speed
	private void checkSpeed() {
		if(getSpeed() > ((healthLevel * maxSpeed)/10)) {
			setSpeed(((healthLevel * maxSpeed)/10));
		}
		
		if(getFoodLevel() == 0 || getHealthLevel() == 0) {
			setSpeed(0);
		}
	}
	
	//move
	public void move() {
		
		useFood(foodConsumptionRate);
		
		super.move();
		
		checkSpeed();
	}
	
	public void useFood(int rate) {
		if(getFoodLevel() > 0) {
			foodLevel -= rate;
		}

		if(getFoodLevel() < 0) {
			foodLevel = 0;
		}
		
		checkSpeed();
	}
	
	//accelerate 
	public void accelerate() {
		if(getSpeed() < ((healthLevel * maxSpeed)/10)) {
			setSpeed(getSpeed() + 3);
		}
		
		checkSpeed();
	}
	
	//brake
	public void brake() {
		if(getSpeed() < 0) {
			setSpeed(0);
		}
		
		if(getSpeed() > 0) {
			setSpeed(getSpeed() - 3);
		}
	}
	
	public String toString(){
		
		// initialize the string
		String antDetail = "Ant: ";
		
		// location
		antDetail += "loc=" + this.getLocation().getX()+ "," + this.getLocation().getY() + " ";
		
		// color RGB
		antDetail += "color=[" + ColorUtil.red(this.getColor()) + "," +  ColorUtil.green(this.getColor()) +  "," + ColorUtil.blue(this.getColor()) + "] ";
		
		// heading
		antDetail += "heading=" + getHeading() + " ";
		
		// speed
		antDetail += "speed=" + getSpeed() + " ";
		
		// width
		antDetail += "size=" + this.getSize() + " ";		
		
		// maxSpeed
		antDetail += ("maxSpeed=" + this.getMaxSpeed() + " ");
		
		// fuelLevel
		antDetail += "foodConsumptionRate=" + foodConsumptionRate + " ";
		
		// return the car details
		return antDetail;
	}

	public void turnLeft() {
		
		setHeading(getHeading() - 5);
	
	}

	public void turnRight() {
			
		setHeading(getHeading() + 5);

	}
}
