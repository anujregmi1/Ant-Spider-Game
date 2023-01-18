package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{
	public HelpCommand() {
		super("Help");
	}
	
	public void actionPerformed(ActionEvent e) {
		String helpInfo = "a: Accelerate\n" + "b: Brake\n" + "l: Turn left\n" + "r: Turn right\n";
		Dialog.show("Help", helpInfo, "OK", null);
	}
}
