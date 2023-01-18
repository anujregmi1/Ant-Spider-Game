package com.mycompany.a3;

import java.util.Random;

import com.codename1.ui.geom.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed implements IDrawable{
	
	Random random = new Random();
	
	private int capacity;
	private GameWorld gw;
	public FoodStation(GameWorld gw) {
		super(ColorUtil.GREEN,gw);
		
		final int MIN_SIZE  = 50;
		final int MAX_SIZE = 100;
		
		super.setSize(new Random().nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE);
		this.capacity = this.getSize();
		this.gw = gw;
	}
	
	//set capacity
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	//return the capacity
	public int getCapacity() {
		return capacity;
	}
	
	//fixed size 
	public void setSize(int size) {
		
	}
	
	public void setColor(int color) {
		super.setColor(color);
	}
	
	//check the condition f the food station
	public boolean checkFoodStation() {
		if(capacity == 0)
			return false;
		else
			return true;
	}
	
	public String toString() {
		
		String foodStationDetail = "FoodStation: ";
		
		// add details to the string for...
		
		// location
		foodStationDetail += "loc=" + Math.round(this.getX() * 10.0) / 10.0 + "," + Math.round(this.getY() * 10.0) / 10.0;
		
		// color
		foodStationDetail += " color=" + this.getColortoString();
		
		// size
		foodStationDetail += " size=" + this.getSize();
		
		//capacity
		foodStationDetail += " capacity=" + this.getCapacity();
		
		// return the fuel can details
		return foodStationDetail;
	}
	
	//draw foodstations
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		int x = pCmpRelPrnt.getX();
		int y = pCmpRelPrnt.getY();
		
		String capacity = String.valueOf(this.getCapacity());
		
		g.setColor(getColor());
		if(this.isSelected()) {
			g.drawRect((x + (int)this.getLocation().getX()), (y + (int)this.getLocation().getY()), this.getSize(), this.getSize());
			g.setColor(ColorUtil.BLACK);
			g.drawString(capacity, (x + (int)this.getLocation().getX()), (y + (int)this.getLocation().getY()));
		} else {
			g.fillRect((x + (int)this.getLocation().getX()), (y + (int)this.getLocation().getY()), this.getSize(), this.getSize());
			g.setColor(ColorUtil.BLACK);
			g.drawString(capacity, (x + (int)this.getLocation().getX()), (y + (int)this.getLocation().getY()));
		}
	}
	
	//handle the collision of food station with ant
	public void handleCollision(GameObject object) {
		if (this instanceof FoodStation && object instanceof Ant)  {
			if(this.getCapacity() !=0) {
				int tempo = this.getCapacity();
				((Ant) object).setFoodLevel(tempo+((Ant) object).getFoodLevel());
				this.setCapacity(0);
				this.setColor(ColorUtil.rgb(255,240,240));
				super.getGW().addNewFoodStation();
			}
		}
	}
}
