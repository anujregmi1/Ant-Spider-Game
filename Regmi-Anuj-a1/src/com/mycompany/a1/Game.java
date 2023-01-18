package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.lang.String;

public class Game extends Form{
	
	private GameWorld gw;
	private int xFlag = 0;
	
	//instantiate the GameWorld, call init() and play 
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	//accepts and executes the player commands
	private void play() {
		
		//create a label
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);  //add the label
		
		//create the textField
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);  //add the textField
		this.show();
		
		//add a actionListener
		myTextField.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent evt) {
				
				//get the command
				String sCommand = myTextField.getText().toString();
				
				//clear the textField
				myTextField.clear();
				
				if(sCommand.length() != 0) {
					if(xFlag == 0) {
						switch(sCommand.charAt(0)) {
							case 'a':
								gw.accelerateAnt();
								break;
							case 'b':
								gw.brakeAnt();
								break;
							case 'l':
								gw.turnAntleft();
								break;	
							case '1':
								gw.playerReachFlag(1);
								break;
							case '2':
								gw.playerReachFlag(2);
								break;
							case '3':
								gw.playerReachFlag(3);
								break;
							case '4':
								gw.playerReachFlag(4);
								break;
							case '5':
								gw.playerReachFlag(5);
								break;
							case '6':
								gw.playerReachFlag(6);
								break;
							case '7':
								gw.playerReachFlag(7);
								break;
							case '8':
								gw.playerReachFlag(8);
								break;
							case '9':
								gw.playerReachFlag(9);
								break;
							case 'r':
								gw.turnAntRight();
								break;	
							case 'g':
								gw.collision();
								break;
							case 'f':	
								gw.antGetsToFoodStation();
								break;
							case 't':
								gw.clock();
								break;
							case 'd':
								String gameDetails = gw.getGameState();
								System.out.println(gameDetails);
								break;
							case 'm':
								gw.printTextMap();
								break;
							case 'x':
								xFlag = 1;
								System.out.println("Are you sure, you want to quit?(y/n)");
								break;
							default:
								System.err.println("Wrong command");
								break;
						} 
					} else if(xFlag == 1) {
						switch(sCommand.charAt(0)) {
							case 'y':
								System.exit(0);
								break;
							case 'n':
								xFlag = 0;
								break;
							default:
								System.out.println("Invalid Command. Do you want to quit?(y/n)");
								break;
						}
					}
				} 
			}
		});
	}
}
