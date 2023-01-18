package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideWithFSCommand extends Command{
	
	private GameWorld gw;
	
	public CollideWithFSCommand(String command, GameWorld world) {
		super(command);
		gw = world;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("CollideWithFS pressed");
		gw.antGetsToFoodStation();
	}
}
