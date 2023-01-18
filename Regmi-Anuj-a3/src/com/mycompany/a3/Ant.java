package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Moveable implements IDrawable{
	
	private int steeringDirection;
	private int maxSpeed;
	private int foodLevel;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private int life;
	
	//ant green and blue color changes
	private int green;
	private int blue;
	
	private static Ant player;
	
	private Ant(GameWorld gw){
		super(ColorUtil.rgb(255, 0, 0),gw);
		this.setSpeed(50); //initial speed of ant
		this.setSize(50);
		this.setHeading(0); //initial heading
		this.setMaxSpeed(500);
		this.setFoodLevel(5000);
		this.setFoodConsumptionRate(1);
		this.setHealthLevel(10);
		this.setLastFlagReached(1);
		this.life = 3;
		
		this.green = 0;
		this.blue = 0;
	}
	
	//singleton pattern
	public static Ant getAnt(GameWorld gw) {
		if(player == null)
			player = new Ant(gw);
		return player;
	}
	
	//accelerate 
	public void accelerate() {
		int currentSpeed = getSpeed();
		if(!maximumSpeedChecker()) {
			this.setSpeed(currentSpeed + 5);
		}
	}
	
	//brake
	public void brake() {
		int currentSpeed = getSpeed();
		if(currentSpeed > 0)
			this.setSpeed(currentSpeed - 5);
	}
	
	//check the speed and make sure it does not cross the max
	public boolean maximumSpeedChecker() {
		if(this.getSpeed() >= maxSpeed) {
			System.out.println("Maximum speed reached!");
			return true;
		}  else 
			return false;
	}
	
	//check if the health level is zero
	public boolean zeroHealthLevelChecker() {
		if(this.getHealthLevel() == 0) 
			return true;
		else 
			return false;
	}
	
	//decrease the health level when collided
	public void decreaseHealthLevel() {
		this.healthLevel--;
	}
	
	//check health level
	public void checkHealthLevel() {
		if(healthLevel > 0 && healthLevel <= 10) {
			if(this.getSpeed() < this.getMaxSpeed()) {
				//maxSpeed = health * 10 % of maxSpeed
				float newMaxSpeed = (float)this.getHealthLevel() / 10 * (float)this.getMaxSpeed();
				this.setMaxSpeed((int)newMaxSpeed);
			} else {
				this.setSpeed(this.getMaxSpeed());
			}
		} else if(healthLevel == 0) {
			this.setSpeed(0);
		}
	}
	
	//decrease the food level according to the consumption rate
	public void decreaseFoodLevel() {
		this.setFoodLevel(this.getFoodLevel() - this.getFoodConsumptionRate());
	}
	
	//reset the ant after a life is lost
	public void resetAnt(float x, float y) {
		this.setX(x);
		this.setY(y);
		this.setHeading(0);
		this.setSpeed(50);
		this.setMaxSpeed(5000);
		this.setFoodConsumptionRate(1);
		this.setFoodLevel(500);
		this.setHealthLevel(10);
		this.setColor(ColorUtil.rgb(255, 0, 0));
		//this.setLastFlagReached(flag.getSequenceNumber());
		//life is lost
		this.life--;
	}
	
	public String toString(){
		
		// initialize the string
		String antDetail = "Ant: ";
		
		// location
		antDetail += "loc=" + Math.round(this.getX() * 10.0) / 10.0+ "," + Math.round(this.getY() * 10.0) / 10.0;
		
		// color
		antDetail += " color=" + this.getColortoString();
		
		// heading
		antDetail += " heading=" + this.getHeading();
		
		// speed
		antDetail += " speed=" + this.getSpeed();
		
		// width
		antDetail += " size=" + this.getSize();		
		
		// maxSpeed
		antDetail += " maxSpeed=" + this.getMaxSpeed();
		
		// fuelLevel
		antDetail += " foodConsumptionRate=" + this.getFoodConsumptionRate();
		
		// return the ant details
		return antDetail;
	}
	
	//override the turnLeft and turnRight methods according to the heading
	public void turnAntLeft() {
		
		this.setHeading(this.getHeading() - 5 - 360);
	}

	public void turnAntRight() {
		
		super.setHeading(this.getHeading() + 5);

	}
	
	/////// all the getter and setter functions for the ant
	
	//life
	public void setLife(int life)
	{
		this.life = life;
	}
	
	public int getLife() {
		return life;
	}
    
    //maxSpeed
    public int getMaxSpeed() {
		return maxSpeed;
	}
    
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;	
	}
	
	//foodLevel
	public int getFoodLevel() {
		return foodLevel;
	}
	
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	//foodConsumptionRate
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}
	
	//healthLevel
	public int getHealthLevel() {
		return healthLevel;
	}
	
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
	
	//flagReached
	public int getLastFlagReached() {
		return lastFlagReached;
	}
	
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	//ant size cannot change once created
	public void setSize(int size) {
		
	}
	
	//green and blue colors
	public void setGreen(int green) {
		this.green = green;
	}
	
	public int getGreen() {
		return green;
	}
	
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getColor());
		
		int xLoc = (int)this.getLocation().getX() + pCmpRelPrnt.getX() - (getSize()/2);
		int yLoc = (int)this.getLocation().getY() + pCmpRelPrnt.getY() - (getSize()/2);
		
		g.drawArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.fillArc(xLoc, yLoc, 40, 40, 0, 360);
		
	}
	
	public void handleCollision(GameObject object) {
		//no need to handle anything 
	}
}
