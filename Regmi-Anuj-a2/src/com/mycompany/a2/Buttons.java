package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Border;

public class Buttons extends Button{
	public Buttons() {
		
	}
	
	public Buttons(String text) {
		super(text);
	}
	
	public Buttons(Command cmd) {
		super(cmd);
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3,ColorUtil.BLACK));
		this.getAllStyles().setPadding(Component.TOP, 5);
		this.getAllStyles().setPadding(Component.BOTTOM, 5);
	}

	public Buttons(Image icon) {
		super(icon);
		// TODO Auto-generated constructor stub
	}

	public Buttons(String text, Image icon) {
		super(text, icon);
		// TODO Auto-generated constructor stub
	}
}
