package com.mycompany.a3;

import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	
	private Media media;
	
	public BGSound(String fName) {
		try {
			
			InputStream input = Display.getInstance().getResourceAsStream(getClass(), "/" + fName);
			media = MediaManager.createMedia(input,"audio/wav", this);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Cannot play sound!");
		}
	}
	
	public void pause() {
		media.pause();
	}
	
	public void play() {
		media.play();
	}
	
	public void run() {
		media.setTime(0);
		media.play();
	}
}
