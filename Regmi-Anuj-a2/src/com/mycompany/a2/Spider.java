package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class Spider extends Moveable{

	private int size;
	
	Spider(){
		super();
		size = rand.nextInt((10 - 5) + 1) + 5;
		setColor(0,0,0);
	}
	
	Spider(FloatPoint p, int c, int h, int s){
		super(p,c,h,s);
		size = rand.nextInt((50 - 10) + 1) + 10;
	}
	
	
	//getsize
	public int getSize() {
		return size;
	}
	
	//spiders cannot change their color once created
	public void setColor(int c) {
		
	}
	
	//cannot change color
	public void setRandomColor() {
		
	}
	
	//cannot change the speed
	public void setSpeed(int s) {
		
	}
	
	//change the spider heading by a random number
	public void move() {
		setHeading(getHeading() + rand.nextInt(10));
		super.move();
	}
	
	public String toString() {
		
		String spiderDetail = "Spider: ";
		
		// location
		spiderDetail += "loc=" + this.getLocation().getX() + "," + this.getLocation().getY() + " ";
		
		// color
		spiderDetail += "color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "] ";
		
		// heading
		spiderDetail += "heading=" + this.getHeading() + " ";
		
		// speed
		spiderDetail += "speed=" + getSpeed() + " ";
		
		// size
		spiderDetail += "size=" + this.getSize();
		
		//return all the details
		return spiderDetail;
	}
}
