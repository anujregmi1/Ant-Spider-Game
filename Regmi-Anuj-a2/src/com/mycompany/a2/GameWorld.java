package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import java.util.Observable;
import java.util.Vector;

public class GameWorld extends Observable{
	
	GameObjectCollection myObjs = new GameObjectCollection();
	
	private static double x = 0, y = 0;
	private int clockTime = 0;
	private int livesLeft = 3;
	private int lastFlagReached;
	
	//player ant
	private Ant player;
	
	//sound
	private boolean sound = false;
	
	public void init() {
		
		lastFlagReached = 1;
		
		//create points for ant and flags
		FloatPoint startingPoint = new FloatPoint(200.0, 200.0);
		
		FloatPoint flagPoint1 = new FloatPoint(200.0, 200.0);
		FloatPoint flagPoint2 = new FloatPoint(200.0, 800.0);
		FloatPoint flagPoint3 = new FloatPoint(700.0, 800.0);
		FloatPoint flagPoint4 = new FloatPoint(900.0, 400.0);
		
		//create single unchangeable color for flag
		final int FLAG_COLOR = ColorUtil.rgb(0, 0, 255);
		
		//create ant and flags and add to myObjs
		player = new Ant(startingPoint, ColorUtil.rgb(255,0,0), 0, 10);
		myObjs.add(player);
		
		Flag f1 = new Flag(flagPoint1, FLAG_COLOR);
		myObjs.add(f1);
		
		Flag f2 = new Flag(flagPoint2, FLAG_COLOR);
		myObjs.add(f2);
		
		Flag f3 = new Flag(flagPoint3, FLAG_COLOR);
		myObjs.add(f3);
		
		Flag f4 = new Flag(flagPoint4, FLAG_COLOR);
		myObjs.add(f4);
		
		//create 3 random spiders
		Spider s1 = new Spider();
		Spider s2 = new Spider();
		Spider s3 = new Spider();
		
		//add spiders to the game
		myObjs.add(s1);
		myObjs.add(s2);
		myObjs.add(s3);
		
		//create 3 random food station
		FoodStation food1 = new FoodStation();
		FoodStation food2 = new FoodStation();
		FoodStation food3 = new FoodStation();
		
		//add food station to the game
		myObjs.add(food1);
		myObjs.add(food2);
		myObjs.add(food3);
	}
	
	// return array of strings formatted
		public String getGameState(){
			
			// create a string to pass back
			String varsAndVals;
			
			// add current lives left
			varsAndVals = "Lives left: ";
			varsAndVals += ( String.valueOf(livesLeft) + "\n");
			
			// add current clock time
			varsAndVals += "Current clock time: ";
			varsAndVals += ( String.valueOf(clockTime) + "\n");
			
			// add highest flag reached
			varsAndVals += "Highest flag reached: ";
			varsAndVals += ( String.valueOf(lastFlagReached) + "\n");
			
			// add current player fuel
			varsAndVals += "Ant's food level: ";
			varsAndVals += ( String.valueOf( player.getFoodLevel() ) + "\n");
			
			// add current player damage
			varsAndVals += "Ant's health level: ";
			varsAndVals += ( String.valueOf( player.getHealthLevel() ) + "\n");
			
			// add the damage cap
//			varsAndVals += "Player Max Damage: ";
//			varsAndVals += ( String.valueOf( player.getMaxDamage() ) + "\n");
//			
			// return the string
			return varsAndVals;
			
		}
		
		public void printTextMap(){
			
			// get an iterator for the collection
			IIterator anIterator = myObjs.getIterator();
			
			// setup a placeholder object
			Object currentObj = new Object();
			
			// iterate through, and print each one
			while( anIterator.hasNext() ){
				currentObj = anIterator.next();
				System.out.println( currentObj.toString() );
			}
		}
		
		public void changeObjColors(){
			IIterator anIterator = myObjs.getIterator();
			
			Object currentObj = new Object();
			
			while( anIterator.hasNext() ){
				currentObj = anIterator.next();
				((GameObject) currentObj).setRandomColor();
			}
		}
		
		//turn left
		public void turnAntleft() {
			player.turnLeft();
			setChanged();
			notifyObservers();
		}
		
		//turn right()}
		public void turnAntRight() {
			player.turnRight();
			setChanged();
			notifyObservers();
		}
		
		//accelerate
		public void accelerateAnt() {
			player.accelerate();
			setChanged();
			notifyObservers();
		}
		
		//brake
		public void brakeAnt() {
			player.brake();
			setChanged();
			notifyObservers();
		}
		
		//ant collides with spider
		public void collision() {
			player.collisonWithSpider();
			
			//if speed is zero life is lost
			if(player.getSpeed() == 0) {
				System.out.println("Your health is null. You lost 1 life. Restarting the game...");
				livesLeft--;
				setChanged();
				notifyObservers();
				init();
			}
			
			//if all lives are lost, game over
			if(livesLeft == 0) {
				System.out.println("GameOver, you failed!");
				System.exit(0);
			}
			
			setChanged();
			notifyObservers();
		}
		
		//ant reach the foodStation
		
		public boolean antGetsToFoodStation() {
			IIterator anIterator = myObjs.getIterator();
			Object currentObj = new Object();
			
			while(anIterator.hasNext()) {
				currentObj = anIterator.next();
				
				if(currentObj instanceof FoodStation) {
					
					player.addFood(((FoodStation)currentObj).getCapacity());
					
					//myObjs.remove(currentObj);
					
//					//fade the color
//					((FoodStation) currentObj).setColor(139, 229, 139);
//					
//					//capacity = 0
//					((FoodStation) currentObj).setCapacity(0);
					
					myObjs.remove(currentObj);
					
					//create new foodstation
					FoodStation newStation = new FoodStation();
					myObjs.add(newStation);
					
					setChanged();
					notifyObservers();
					return true;
				}
			}
			setChanged();
			notifyObservers();
			return false;
		}
		
		//player reach flags
		public boolean playerReachFlag(int p) {
			if(p == (lastFlagReached + 1)) {
				lastFlagReached = p;
				
				//check if the player reached the last flag
				if(lastFlagReached == 4) {
					System.out.println("GameOver, you won!");
					System.exit(0);
					setChanged();
					notifyObservers();
				}
				setChanged();
				notifyObservers();
				return true;
			}
			setChanged();
			notifyObservers();
			return false;
		}
		
		//clock
		public void clock() {
			
			//if speed is 0, life is lost
			if(player.getSpeed() == 0) {
				System.out.println("You are too hungry. You lost 1 life. Restarting the game...");
				livesLeft--;
				init();
				setChanged();
				notifyObservers();
				return;
			}
			
			//if all live is lost, game over
			if(livesLeft == 0) {
				System.out.println("GameOver, you failed!");
				System.exit(0);
			}
			
			clockTime++;
			
			
			IIterator anIterator = myObjs.getIterator();
			
			Object currentObj = new Object();
			
			while(anIterator.hasNext()) {
				currentObj = anIterator.next();
				if(currentObj instanceof Moveable) {
					((Moveable) currentObj).move();
				}
					
			}
			
			setChanged();
			notifyObservers();
		}
		
		//exit code
		public void exit() {
			
			Command cOk = new Command("Ok");
			Command cCancel = new Command("Cancel");
			
			Command[] cmds = new Command[] {cOk, cCancel};
			TextField myTF = new TextField();
			Command c = Dialog.show("Are you sure you want to exit?(y/n)", myTF, cmds);
			if(c == cOk) {
				if((myTF.getText()).compareTo("y") == 0) {
					System.out.println("Games exited!!");
					System.exit(0);
				}
			}	
			else if(myTF.getText() == "n")
					c = cCancel;
			
				
		}
		
		//about code
		public void about() {
			Command cCancel = new Command("Cancel");
			
			Command[] cmds = new Command[] {cCancel};
			
			Dialog.show("About", "Anuj Regmi \n Course: CSC 133 \n Version: 1.2.0", cmds);
		}
		
		//Help code
		public void help() {
			Command cCancel = new Command("Cancel");
			Command[] cmds = new Command[] {cCancel};
			
			Dialog.show("Help", "a : Accelerate \n b : Break \n l : Turn Left \n r : Turn Right \n f : Get to the food Station \n g : Collide with Spider \n t : Tick the time", cmds);
		}
		
		//get number of lives left
		public int getLivesLeft() {
			return livesLeft;
		}
		
		//get the time
		public int getTime() {
			return clockTime;
		}
		
		//get Highest flag reached
		public int getHighestFlag() {
			return lastFlagReached;
		}
		
		//get food level
		public int getFoodLevel() {
			return player.getFoodLevel();
		}
		
		//get health level
		public int getHealthLevel() {
			return player.getHealthLevel();
		}
		
		public void toggleSound() {
			sound = !sound;
			//mark as value changed
			setChanged();
			notifyObservers();
		}
		
		public boolean getSound() {
			return sound;
		}
		
		public void setX(double width) {
			x = width;
		}
		
		public void setY(double height) {
			y = height;
		}
		
		public static double getSizex(){
			return x;
		}
		
		public static double getSizey() {
			return y;
		}
}
