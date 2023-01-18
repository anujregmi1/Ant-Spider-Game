package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed implements IDrawable{

	private int sequenceNumber;  //flag number
	private GameWorld gw;
	public Flag(int sequenceNumber, GameWorld gw){
		super(ColorUtil.LTGRAY,100,gw);
		this.sequenceNumber = sequenceNumber;
		this.gw = gw;
	}
	
	//set the color
	public void setColor(int color) {
		//color cannot be chnaged
	}
	
	//return the sequence number 
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	
	//set the sequence number
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	//set size
	public void setSize(int size) {
		//size cannot be changed
	}
	
	//create a map value for flag
	public String toString() {
		
		String flagDetail = "Flag: ";
		
		//location
		flagDetail += "loc=" + Math.round(this.getX() * 10.0) / 10.0 + "," + Math.round(this.getY() * 10.0) / 10.0; 
		
		//color
		flagDetail += " color=" + this.getColortoString();
		
		//size
		flagDetail += " size=" + this.getSize();
		
		//sequence number
		flagDetail += " seqNum=" + this.getSequenceNumber();
		
		return flagDetail;
	}
	
	//draw the flags
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
		if(this.isSelected()) {
			
			g.drawPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawStringBaseline(String.valueOf(this.sequenceNumber), xPt3-2, yPt3-(int)(this.getSize()/2));
			
		} else {
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawStringBaseline(String.valueOf(this.sequenceNumber), xPt3-2, yPt3-(int)(this.getSize()/2));
		}
		
		
		
	}
	
	//handle the collison of ant with flag
	public void handleCollision(GameObject object) {
		if(this instanceof Flag && object instanceof Ant) {
			if(((Ant) object).getLastFlagReached() + 1 == this.getSequenceNumber() && this.getSequenceNumber() != 5) {
				System.out.println("Ant reached the next flag");
				((Ant) object).setLastFlagReached(this.getSequenceNumber());
			} else if(this.getSequenceNumber() == 5 && object instanceof Ant) {
				if(((Ant) object).getLastFlagReached() + 1 == 5) {
					System.out.println("Last Flag reached! You Won in time: " + super.getGW().getTime() / 60+ "sec");
					gw.exit(0);
				}
			}
		}
	}
}
