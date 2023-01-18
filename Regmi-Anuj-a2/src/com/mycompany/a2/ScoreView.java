package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

public class ScoreView extends Container implements Observer{
	
	private Label livesLeft, time, highestFlag, foodLevel, healthLevel, sound;
	
	ScoreView(){
		
		this.add(time = new Label("Time:   0   "));
		this.add(livesLeft = new Label("Lives Left:   3   "));
		this.add(highestFlag = new Label("Last Flag Reached:   1   "));
		this.add(foodLevel = new Label("Food Level:   10   "));
		this.add(healthLevel = new Label("Health Level:   10   "));
		this.add(sound = new Label("Sound:   OFF"));
		
	}
	
	public void update (Observable o, Object arg) {
		
		livesLeft.setText("   Lives Left:   " + ((GameWorld) o).getLivesLeft());
		time.setText("Time:   " + ((GameWorld) o).getTime());
		highestFlag.setText("   Last Flag Reached:   " + ((GameWorld) o).getHighestFlag());
		foodLevel.setText("   Food Level:   " + ((GameWorld) o).getFoodLevel());
		healthLevel.setText("   Health Level:   " + ((GameWorld) o).getHealthLevel());
		if(((GameWorld) o).getSound())
			sound.setText("   Sound:   ON");
		else sound.setText("   Sound:   OFF");
		
	}
}
