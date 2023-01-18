package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Spider extends Moveable implements IDrawable{

	Random rand = new Random();
	
	public Spider(GameWorld gw){
		super(ColorUtil.BLACK, gw);
		final int MIN_SIZE = 30;
		final int MAX_SIZE = 100;
		
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE);
		super.setHeading(rand.nextInt(360));
		super.setSpeed(rand.nextInt(200-60)+60);
	}
	
	
	//size is fixed once created
	public void setSize(int size) {
		
	}
	
	//color is also fixed
	public void setColor(int color) {
		
	}
	
	//move will change the heading if the spider
	public void moveSpider() {
		this.setHeading(super.getHeading() + rand.nextInt(5));
	}
	
	//check for boundry
	public void boundrySpider() {
		if((this.getSize() + this.getX()) > 1000 || (this.getX() + this.getSize() > 0)) {
			this.setHeading(rand.nextInt(180));
		}
		
		if((this.getSize() + this.getY()) > 1000 || (this.getY() + this.getSize() < 0)) {
			this.setHeading(rand.nextInt(180));
		}
	}
	
	public String toString() {
		
		String spiderDetail = "Spider: ";
		
		// location
		spiderDetail += "loc=" + Math.round(this.getX() * 10.0) / 10.0 + "," + Math.round(this.getY() * 10.0) / 10.0;
		
		// color
		spiderDetail += " color=" + this.getColortoString();
		
		// heading
		spiderDetail += " heading=" + this.getHeading();
		
		// speed
		spiderDetail += " speed=" + getSpeed(); 
		
		// size
		spiderDetail += " size=" + this.getSize();
		
		//return all the details
		return spiderDetail;
	}
	
	//draw
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int xPt = (int)(this.getLocation().getX() + pCmpRelPrnt.getX());
		int yPt = (int)(this.getLocation().getY() + pCmpRelPrnt.getY());
		
		int xPt2 = xPt + this.getSize();
		int yPt2 = yPt;
		
		int xPt3 = xPt + (this.getSize() / 2);
		int yPt3 = yPt + this.getSize();
		
		int[] xPoints = {xPt,xPt2,xPt3};
		int[] yPoints = {yPt,yPt2,yPt3};
		
		g.setColor(super.getColor());
		g.drawPolygon(xPoints, yPoints, 3);
		  
	}
	
	//handle the collision
	
	public void handleCollision(GameObject object) {
		
			if (this instanceof Spider && object instanceof Ant) {
				if (((Ant) object).zeroHealthLevelChecker() == true) {
					System.out.println("Your Ant is dead.");
					
					if (((Ant) object).getLife() > 1){
						super.getGW().exit(3);
						super.getGW().lostLifeSound();
						int tempLastFlag = ((Ant) object).getLastFlagReached();
						IIterator iterator = super.getGW().getCollection().getIterator();
						
						while(iterator.hasNext()){
							GameObject object2 = iterator.getNext();
							if(object2 instanceof Flag) {
								if(tempLastFlag == ((Flag) object2).getSequenceNumber()) {
									float base_x = ((Flag) object2).getX();
									float base_y = ((Flag) object2).getY();
									((Ant) object).resetAnt(base_x,base_y);
								}
							}
						}
					}
					else {
						super.getGW().exit(2);
					}	
				}
				else {
					((Ant) object).decreaseHealthLevel();
					((Ant) object).checkHealthLevel();
					((Ant) object).setGreen(((Ant) object).getGreen() + 25);
					((Ant) object).setBlue(((Ant) object).getBlue() + 25);
					((Ant) object).setColor(ColorUtil.rgb(255,((Ant) object).getGreen(),((Ant) object).getBlue()));
					
				}
			}
		
	}
}
