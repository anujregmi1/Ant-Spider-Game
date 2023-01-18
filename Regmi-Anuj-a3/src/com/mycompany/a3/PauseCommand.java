package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command{
	private Game game;
	private GameWorld gw;
	public PauseCommand(GameWorld gw, Game game, Button button) {
		super("Pause");
		this.gw = gw;
		this.game = game;
	}
	
	public void actionPerformed(ActionEvent e) {
		game.pausePressed();
	}

}
