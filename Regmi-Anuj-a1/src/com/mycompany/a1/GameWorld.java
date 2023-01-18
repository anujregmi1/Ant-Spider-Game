package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	
	GameObjectCollection myObjs = new GameObjectCollection();
	
	private int clockTime = 0;
	private int livesLeft = 3;
	private int lastFlagReached;
	
	private Ant player;
	
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
		}
		
		//turn right()}
		public void turnAntRight() {
			player.turnRight();
		}
		
		//accelerate
		public void accelerateAnt() {
			player.accelerate();
		}
		
		//brake
		public void brakeAnt() {
			player.brake();
		}
		
		//ant collides with spider
		public void collision() {
			player.collisonWithSpider();
			
			//if speed is zero life is lost
			if(player.getSpeed() == 0) {
				System.out.println("Your health is null. You lost 1 life. Restarting the game...");
				livesLeft--;
				init();
			}
			
			//if all lives are lost, game over
			if(livesLeft == 0) {
				System.out.println("GameOver, you failed!");
				System.exit(0);
			}
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
					
					//fade the color
					((FoodStation) currentObj).setColor(139, 229, 139);
					
					//capacity = 0
					((FoodStation) currentObj).setCapacity(0);
					
					//create new foodstation
					FoodStation newStation = new FoodStation();
					myObjs.add(newStation);
					
					return true;
				}
			}
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
				}
				return true;
			}
			return false;
		}
		
		//clock
		public void clock() {
			
			//if speed is 0, life is lost
			if(player.getSpeed() == 0) {
				System.out.println("You are too hungry. You lost 1 life. Restarting the game...");
				livesLeft--;
				init();
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
		}
}
