package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	public AboutCommand() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent e) {
		String aboutInfo = "ThePath Game\n" + "Developer: Anuj Regmi\n" + "Client: Dr. Pınar Muyan-Özçelik (CSUS)\n" + "Version: 1.1";
		Dialog.show("About", aboutInfo, "OK",null);
	}
}
