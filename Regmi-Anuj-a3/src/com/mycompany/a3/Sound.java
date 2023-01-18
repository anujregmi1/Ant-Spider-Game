package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	private Media media;
	public Sound(String fName) {
		
		try {
			InputStream input = Display.getInstance().getResourceAsStream(getClass(), "/" + fName);
			media = MediaManager.createMedia(input,"audio/wav");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		media.setTime(0);
		media.play();
	}
}
