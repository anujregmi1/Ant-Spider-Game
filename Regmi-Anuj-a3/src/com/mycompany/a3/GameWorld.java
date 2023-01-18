package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.ui.geom.Point;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;


public class GameWorld extends Observable{
	
	private GameWorldCollection gameObjects;
	private Game game;
	private int timer;
	Random random = new Random();
	private boolean position;
	
	private static int gameHeight = 1000;
	private static int gameWidth = 1000;
	
	private boolean pause;
	private boolean soundON;
	private Sound fsSound;
	private Sound flagSound;
	private Sound spiderSound;
	private Sound lifeSound;
	private BGSound bgSound;
	Command cOk = new Command("Ok");
	
	public void init() {
		
		this.timer = 0;
		pause = false;
		this.soundON = false;
		gameObjects = new GameWorldCollection();
		position = false;
		addGameObjects();
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	//add objects to the gameWorld
	public void addGameObjects() {
		
		float x = 0;
		float y = 0;
		
		gameObjects.add(Ant.getAnt(this));
		
		//add 5 flags
		gameObjects.add(new Flag(1,this));
		gameObjects.add(new Flag(2,this));
		gameObjects.add(new Flag(3,this));
		gameObjects.add(new Flag(4,this));
		gameObjects.add(new Flag(5,this));
		
		//add 3 foodStations
		gameObjects.add(new FoodStation(this));
		gameObjects.add(new FoodStation(this));
		gameObjects.add(new FoodStation(this));
		
		//add 6 spiders
		gameObjects.add(new Spider(this));
		gameObjects.add(new Spider(this));
		gameObjects.add(new Spider(this));
		gameObjects.add(new Spider(this));
		gameObjects.add(new Spider(this));
		gameObjects.add(new Spider(this));
		
		//x and y for flag
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			
			GameObject object = iterator.getNext();
			if(object instanceof Flag) {
				if(((Flag) object).getSequenceNumber() == 1) {
					x = ((Flag) object).getX();
					y = ((Flag) object).getY();
				}
			}
		}
		
		//assign x and y to the ant
		IIterator iterator2 = gameObjects.getIterator();
		while(iterator2.hasNext()) {
			
			GameObject object = iterator2.getNext();
			if(object instanceof Ant) {
				((Ant) object).setX(x);
				((Ant) object).setY(y);
			}
		}
		
	}
	
		
	//accelerate the ant, press "a"
	public void accelerateAnt() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			
			GameObject object = iterator.getNext();
			if(object instanceof Ant) {
				((Ant) object).accelerate();
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//brake the ant, press "b"
	public void brakeAnt() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			
			GameObject object = iterator.getNext();
			if(object instanceof Ant) {
				((Ant) object).brake();
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//turn ant left, press "l"
	public void turnAntLeft() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			
			GameObject object = iterator.getNext();
			if(object instanceof Ant) {
				((Ant) object).turnAntLeft();
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//turn ant right, press "r"
	public void turnAntRight() {
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			
			GameObject object = iterator.getNext();
			if(object instanceof Ant) {
				((Ant) object).turnAntRight();
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//ant reach the flag, press 1-9
//	public void antReachFlag(int f) {
//		IIterator iterator = gameObjects.getIterator();
//		
//		while(iterator.hasNext()) {
//			
//			GameObject object = iterator.getNext();
//			if(object instanceof Ant) {
//				
//				int temp = ((Ant) object).getLastFlagReached();
//				temp++;
//				if(temp == f && f != 5) {
//					System.out.println("Ant reached next flag");
//					((Ant) object).setLastFlagReached(f);
//				} else if(temp == 5){
//					System.out.println("Game Over, Congrats you won!. Total time: " + this.timer);
//					System.exit(0);
//				} else {
//					System.out.println("Passed the flag");
//				}
//				
//			}
//		}
		
//		this.setChanged();
//		this.notifyObservers(this);
//			
//	}
	
//	//ant collides with spiders, press "g"
//	public void collision() {
//		IIterator iterator = gameObjects.getIterator();
//		
//		while(iterator.hasNext()) {
//			
//			GameObject object = iterator.getNext();
//			
//			if(object instanceof Ant) {
//				
//				//if the health level is zero 
//				if(((Ant) object).zeroHealthLevelChecker() == true) {
//					System.out.println("Ant health is 0. Live lost!");
//					
//					//if the player still has life and else
//					if(((Ant) object).getLife() != 1) {
//						int tempLastFlag = ((Ant) object).getLastFlagReached();
//						
//						//create another iterator
//						IIterator iterator2 = gameObjects.getIterator();
//						
//						while(iterator2.hasNext()) {
//							
//							GameObject object2 = iterator2.getNext();
//							
//							if(object2 instanceof Flag) {
//								
//								if(tempLastFlag == (((Flag) object2).getSequenceNumber())) {
//									float flagX = (((Flag) object2).getX());
//									float flagY = (((Flag) object2).getY());
//									
//									//reset the location of the Ant
//									((Ant) object).resetAnt(flagX,flagY);
//								}
//							}
//						}
//					} else {
//						System.out.println("Game over, you lost all 3 lives!");
//						System.exit(0);
//					}
//					
//				} else {
//					//decrease the healthLevel and dim the color of the ant
//					((Ant) object).decreaseHealthLevel();
//					//check the health level
//					((Ant) object).checkHealthLevel();
//					((Ant) object).setColor(ColorUtil.rgb((int)(255*0.9)/((Ant) object).getHealthLevel(), 255, 0));
//					
//				}
//			}
//		}
//		
//		this.setChanged();
//		this.notifyObservers(this);
//	}
//	
	//ant reach a foodStation, press "f"
//	public void antReachFoodStation() {
//		IIterator iterator = gameObjects.getIterator();
//		
//		while(iterator.hasNext()) {
//			
//			GameObject object = iterator.getNext();
//			
//			if(object instanceof Ant) {
//				
//				IIterator iterator2 = gameObjects.getIterator();
//				
//				while(iterator2.hasNext()) {
//					
//					GameObject object2 = iterator2.getNext();
//					
//					if(object2 instanceof FoodStation) {
//						if(((FoodStation) object2).getCapacity() != 0){
//							int tempo = ((FoodStation) object2).getCapacity();
//							((Ant) object).setFoodLevel(tempo + ((Ant) object).getFoodLevel());
//							((FoodStation) object2).setCapacity(0);
//							((FoodStation) object2).setColor(ColorUtil.rgb(236, 255, 224));
//							gameObjects.add(new FoodStation());
//							break;
//						}
//					}
//				}
//			}
//		}
//		
//		this.setChanged();
//		this.notifyObservers(this);
//	}
	
	//time ticks, press "t"
	public void tick(float time) {
		timer++;
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			
			GameObject object = iterator.getNext();
			
			if(object instanceof Ant) {
				
				//if food level is not zero: move, setSteering, decreaseFoodLevel
				if(((Ant) object).getFoodLevel() > 0 && ((Ant) object).getHealthLevel() <= 10 && ((Ant) object).getHealthLevel() > 0) {
					
					((Ant) object).move(time);
					//((Ant) object).setSteeringDirection(0);
					((Ant) object).decreaseFoodLevel();
					
					IIterator iterator2 = gameObjects.getIterator();
					while(iterator2.hasNext()) {
						GameObject object2 = iterator2.getNext();
						
						if(object2 instanceof Spider) {
							//((Spider) object2).checkBoundary();
							//((Spider) object2).moveSpider();
							((Spider) object2).move(time);
						}
					}
					
				} else if(((Ant) object).getLife() > 1){
					
					System.out.println("You lost 1 life");
					//exit(3);
					int tempLastFlag = ((Ant) object).getLastFlagReached();
					
					IIterator iterator3 = gameObjects.getIterator();
					
					while(iterator3.hasNext()) {
						
						GameObject object3 = iterator3.getNext();
						if(object3 instanceof Flag) {
							
							if(tempLastFlag == (((Flag) object3).getSequenceNumber())) {
								float flagX = (((Flag) object3).getX());
								float flagY = (((Flag) object3).getY());
								//reset the ant
								((Ant) object).resetAnt(flagX,flagY);
								if(getSound())
									lifeSound.play();
							}
						}
					}
					
				} else {
					exit(2);
				}
			}
		}
		
		checkCollision();
		if(getSound()) {
			bgSound.play();
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//check the collision. merge the food station collision, flag collision and spider collision
	public void checkCollision() {
		
		IIterator iterator = gameObjects.getIterator();
		
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			
			IIterator iterator2 = gameObjects.getIterator();
			while(iterator2.hasNext()) {
				GameObject object2 = iterator2.getNext();
				if(object != object2) 
					if(object.collidesWith(object2)) {
						
						if(object instanceof Ant && object2 instanceof Spider) {
							if(getSound())
								spiderSound.play();
						} else if(object instanceof Ant && object2 instanceof Flag) {
							if(getSound())
								flagSound.play();
						} else if(object instanceof Ant && object2 instanceof FoodStation) {
							if(getSound())
								fsSound.play();
						}
						
						if(!object.getCollidedObjects().contains(object2)) {
							object.setVector(object2);
							object.handleCollision(object2);
						}
						
					} else {
						object.getCollidedObjects().remove(object2);
					} 
					
				}
			}
		}

	
	//get sound
	public boolean getSound() {
		return soundON;
	}
	
	//set sound
	public void setSound(boolean sound) {
		this.soundON = sound;
		this.setChanged();
		this.notifyObservers(this);
	}
	
//	//print map, press "m"
//	public void printTextMap() {
//		System.out.println("Game Map:");
//		
//		IIterator iterator = gameObjects.getIterator();
//		while(iterator.hasNext()) {
//			GameObject object = iterator.getNext();
//			System.out.println(object.toString());
//		}
	//}
	
	//print the status of the game
//	public void getGameStatus() {
//		System.out.println("Current game state:");
//		
//		IIterator iterator = gameObjects.getIterator();
//		while(iterator.hasNext()) {
//			GameObject object = iterator.getNext();
//			
//			if(object instanceof Ant) {
//				
//				int life = ((Ant) object).getLife();
//				int time = getTime();
//				int lastFlagReached = ((Ant) object).getLastFlagReached();
//				int foodLevel = ((Ant) object).getFoodLevel();
//				int healthLevel = ((Ant) object).getHealthLevel();
//				
//				//print everything
//				System.out.println("Lives left: " + life);
//				System.out.println("Time elapsed: " + time);
//				System.out.println("Last flag reached: " + lastFlagReached);
//				System.out.println("FoodLevel: " + foodLevel);
//				System.out.println("HealthLevel: " + healthLevel);
//			}
//		}
//	}
	
	//get all the data to display in the scoreView
	public GameWorldCollection getCollection() {
		return gameObjects;
	}
	
	//time
	public int getTime() {
		return this.timer;
	}
	
	//lastFlagReached
	public int getLastFlagReached() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			
			if(object instanceof Ant) {
				return ((Ant) object).getLastFlagReached();
			}
		}
		
		return 0;
	}
	
	//getFoodLevel
	public int getFoodLevel() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			
			if(object instanceof Ant) {
				return ((Ant) object).getFoodLevel();
			}
		}
		
		return 0;
	}
	
	//getHealthLevel
	public int getHealthLevel() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			
			if(object instanceof Ant) {
				return ((Ant) object).getHealthLevel();
			}
		}
		
		return 0;
	}
	
	//getLife
	public int getLife() {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			GameObject object = iterator.getNext();
			
			if(object instanceof Ant) {
				return ((Ant) object).getLife();
			}
		}
		
		return 0;
	}
	
	//fixed object click
	public void click(Point clickPoint, Point originalPoint) {
		IIterator iterator = gameObjects.getIterator();
		while(iterator.hasNext()) {
			if(iterator.getNext() instanceof Fixed) {
				Fixed obj = (Fixed)iterator.getCurrent();
				if(position && obj.isSelected()) {
					int newX = (int)(clickPoint.getX() - originalPoint.getX());
					int newY = (int)(clickPoint.getY() - originalPoint.getY());
					
					obj.setLocation(newX, newY);
					position = false;
					obj.setSelected(false);
				} else {
					if(obj.contains(clickPoint, originalPoint))
						obj.setSelected(true);
					else
						obj.setSelected(false);
				}
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	//function to  add a new food station once ant collides to it
	public void addNewFoodStation() {
		gameObjects.add(new FoodStation(this));
	}
	
	//game dimensions
	public static int getGameHeight() {
		return gameHeight;
	}
	
	public static int getGameWidth() {
		return gameWidth;
	}
	
	public static void setGameWidth(int width) {
		gameWidth = width;
	}
	
	public static void setGameHeight(int height) {
		gameHeight = height;
	}
	
	//pause functions
	public boolean getPause() {
		return pause;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}	
	
	public void positionOn() {
		if(position == true)
			position = false;
		else
			position = true;
	}
	
	//functions that handles different exit commands
	public void exit(int number) {
		Command cOk = new Command("Ok");
		Command cOkLifeLeft = new Command("Ok");
		Command response = new Command("");
		
		if(number == 0) {
			response = Dialog.show("Congrats!","You won the game in "+ this.getTime()/100 + " sec", cOk);
		} else if(number == 2) {
			response = Dialog.show("You loose!","All Ant lives are lost.", cOk);
		} else if(number == 3) {
			Dialog.show("You can still do it!","Lost 1 life", cOkLifeLeft);
		}
		
		if(response == cOk) {
			System.exit(0);
		}
	}
	
	//disable the position
	public void disablePosition() {
		position = false;
	}
	
	public void lostLifeSound() {
		if(getSound())
			lifeSound.play();
	}
	
	//sound
	public void createSound() {
		bgSound = new BGSound("background.wav");
		spiderSound = new Sound("spider.wav");
		flagSound = new Sound("flag.wav");
		fsSound = new Sound("food.wav");
		lifeSound = new Sound("life.wav");
	}
	
	//background sound turn off and on
	public void turnSoundOn() {
		if(getSound())
			bgSound.play();
	}
	
	public void turnSoundOff() {
		bgSound.pause();
	}
}
