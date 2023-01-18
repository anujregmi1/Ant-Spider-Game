package com.mycompany.a3;

public abstract class Moveable extends GameObject{
	private int speed;
	private int heading;
	private boolean flag = false;
	public Moveable(int color,GameWorld gw) {
		super(color, gw);
	}
	
	Moveable(int color, int size, GameWorld gw){
		super(color, size, gw);
	}
	
	//set the speed
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	//get the speed
	public int getSpeed() {
		return speed;
	}
	
	//set the heading
	public void setHeading(int heading) {
		while(heading >= 360.0) {
			heading -= 360.0;
		}
		
		while(heading < 0.0) {
			heading += 360.0;
		}
		
		this.heading = heading;
	}
	
	//get the heading
	public int getHeading() {
		return heading;
	}
	
	public boolean getFlag() {
		return flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	//moveable objects can move, so set the location once it moves with new coordinates according to the speed and heading
	public void move(float time) {
		
		float newX = this.getX() + (float)Math.cos( Math.toRadians(90-this.heading)  ) * (speed * (time / 1000));
 		float newY = this.getY() + (float)Math.sin( Math.toRadians(90-this.heading)  ) * (speed * (time / 1000));	
 		int offset = 0;
 		
 		if(this instanceof Spider) {
 			offset = 50;
 		} else if(this instanceof Ant) {
 			offset = 50;
 		}
		
 		int orginalX = (int)MapView.getMapOrigin().getX();
 		int orginalY = (int)MapView.getMapOrigin().getY();
 		
 		// Right wall 
		if((orginalX + newX + offset >= MapView.getMapWidth() + orginalX) && (heading != 0 || heading != 180)) {
			setHeading(360-heading);
		}
			
		// Left wall 
		if (this instanceof Spider) {
			if(orginalX + newX  <= orginalX + 20 && (heading != 0 || heading != 180)  ) {
				setHeading(360-heading);
			}
		} else {
				
			if(orginalX + newX  <= orginalX && (heading != 0 || heading != 180)  ) {
				setHeading(360-heading);
			}
		}
			
		// Bottom wall
		if(orginalY + newY + offset >= orginalY + GameWorld.getGameHeight() ) {
			setHeading((360-heading+180)%360);
		}
		
		if (this instanceof Spider) {
			if(orginalY + newY  <= orginalY + 25) {
				setHeading((360-heading+180)%360);
			}
		}
			
		// Top wall
		else {
			if(orginalY + newY  <= orginalY ) {
				setHeading((360-heading+180)%360);
			}
		}
		this.setLocation(newX, newY);
	}
}
