package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CollideWithFlagCommand extends Command{
	private GameWorld gw;
	
	public CollideWithFlagCommand(String command, GameWorld world) {
		super(command);
		gw = world;
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("CollideWithFlag pressed");
		
		//create the dialog box
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		
		Command[] cmds = new Command[] {cOk, cCancel};
		TextField myTF = new TextField();
		Command c = Dialog.show("Enter the flag number:", myTF, cmds);
		if(c == cOk)
			gw.playerReachFlag(Integer.parseInt(myTF.getText()));
		
	}
}
