package com.mycompany.a1;

public class FloatPoint{
	
	// x and y values for 2d location
	private Double x;
	private Double y;
	
	// construcct a point
	FloatPoint(Double xVal, Double yVal){
		x = xVal;
		y = yVal;
	}
	
	// get the x value
	public Double getX(){
		return x;
	}
	
	// get the y value
	public Double getY(){
		return y;
	}
	
	// set the x value
	private void setX(Double newX){
		x = newX;
	}
	
	// set the y value
	private void setY(Double newY){
		y = newY;
	}
	
	// set both x and y
	public void setLocation(Double newX, Double newY){
		setX(newX);
		setY(newY);
	}	
}
