package com.mycompany.a2;

public abstract class Moveable extends GameObject{
	private int speed;
	private int heading;
	
	Moveable() {
		super();
		speed = rand.nextInt(((15 - 5) + 1) + 5);
		heading = rand.nextInt(359);
	}
	
	Moveable(FloatPoint p, int c, int h, int s){
		super(p, c);
		heading = h;
		speed = s;
	}
	
	public void move() {
		Double angle = ((90 - this.getHeading()) / 180) * Math.PI;
		Double deltaX = Math.cos(angle) * speed;
		
		Double deltaY = Math.sin(angle) * speed;

		
		super.move(deltaX, deltaY);
	}
	
	//speed
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int s) {
		speed = s;
	}
	
	//direction
	public int getHeading() {
		return heading;
	}
	
	public void setHeading(int h) {
		heading = h;
	}
	
}
