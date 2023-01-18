package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class ScoreView extends Container implements Observer{
	private GameWorld gw;
	private Label lifeValueLabel;
	private Label timeValueLabel;
	private Label flagValueLabel;
	private Label foodValueLabel;
	private Label healthValueLabel;
	private Label soundValueLabel;
	
	public ScoreView() {
		//set the Layout
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		setTimeLabel();
		setLifeLabel();
		setFlagLabel();
		setFoodLabel();
		setHealthLabel();
		setSoundLabel();
	}
	
	//time label
	public void setTimeLabel() {
		Label timeLabel = new Label("Time:");
		timeLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		timeLabel.getAllStyles().setMarginLeft(300);
		timeLabel.getAllStyles().setMarginRight(20);
		timeValueLabel = new Label("0");
		timeValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		timeValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(timeLabel);
		this.add(timeValueLabel);
	}
	
	//life label
	public void setLifeLabel() {
		Label lifeLabel = new Label("Lives Left:");
		lifeLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lifeLabel.getAllStyles().setPadding(1,1,1,1);
		lifeValueLabel = new Label("0");
		lifeValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		lifeValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(lifeLabel);
		this.add(lifeValueLabel);
	}
	
	//flag label
	public void setFlagLabel() {
		Label flagLabel = new Label("Last flag reached:");
		flagLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		flagLabel.getAllStyles().setPadding(1,1,1,1);
		flagValueLabel = new Label("0");
		flagValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		flagValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(flagLabel);
		this.add(flagValueLabel);
	}
	
	//foodLabel
	public void setFoodLabel() {
		Label foodLabel = new Label("FoodLevel:");
		foodLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		foodLabel.getAllStyles().setPadding(1,1,1,1);
		foodValueLabel = new Label("0");
		foodValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		foodValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(foodLabel);
		this.add(foodValueLabel);
	}
	
	//healthLabel
	public void setHealthLabel() {
		Label healthLabel = new Label("HealthLevel:");
		healthLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		healthLabel.getAllStyles().setPadding(1,1,1,1);
		healthValueLabel = new Label("10");
		healthValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		healthValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(healthLabel);
		this.add(healthValueLabel);
	}
	
	//soundLabel
	public void setSoundLabel() {
		Label soundLabel = new Label("Sound:");
		soundLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundLabel.getAllStyles().setPadding(1,1,1,1);
		soundValueLabel = new Label("OFF");
		soundValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundValueLabel.getAllStyles().setPadding(RIGHT, 2);
		this.add(soundLabel);
		this.add(soundValueLabel);
	}
	@Override
	public void update(Observable observable, Object data) {
		gw = (GameWorld) data;
		this.lifeValueLabel.setText(" "+gw.getLife());
		this.timeValueLabel.setText(" "+Integer.toString(gw.getTime()/100));
		this.flagValueLabel.setText("" +gw.getLastFlagReached());
		this.foodValueLabel.setText(" "+gw.getFoodLevel());
		this.healthValueLabel.setText(" "+gw.getHealthLevel());
		if(gw.getSound()) {
			this.soundValueLabel.setText("ON");
		} else {
			this.soundValueLabel.setText("OFF");
		}
		
		this.repaint();
	}
	
	
}
