package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	public ExitCommand() {
		super("Exit");
	}
	
	public void actionPerformed(ActionEvent e) {
		Command cOk = new Command("Yes");
		Command cCancel = new Command("No");
		Command[]cmds = new Command[] {cOk,cCancel};
		Command c = Dialog.show("Exit", "Do you want to quit the game?",cmds);
		if(c == cOk) {
			System.exit(0);
		}
	}
}
