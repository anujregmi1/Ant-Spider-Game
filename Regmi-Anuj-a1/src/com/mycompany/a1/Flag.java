package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed{

	private int size;
	private int sequenceNumber;
	
	private static int count = 0;
	
	Flag(FloatPoint p, int c){
		super(p, c);
		size = 10;
		
		increment();
		
		setSequenceNumber();
	}
	
	//return size and sequenceNumber of the flag
	
	public int getSize() {
		return size;
	}
	
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	private void setSequenceNumber() {
		sequenceNumber = count;
	}
	
	private void increment() {
		count++;
	}
	
	//get the count
	public int getCount() {
		return count;
	}
	
	//flag cannot change color
	public void setColor(int c) {
		
	}
	
	public void setRandomColor() {
		
	}
	
	public String toString() {
		
		String flagDetail = "Flag: ";
		
		//location
		flagDetail += "loc=" + this.getLocation().getX() + "," + this.getLocation().getY() + " "; 
		
		//color
		flagDetail += "color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "] ";
		
		//size
		flagDetail += "size=" + this.getSize() + " ";
		
		//sequence number
		flagDetail += "sequenceNumber=" + this.getSequenceNumber();
		
		return flagDetail;
	}
}
