package com.blackjack.client.sounds;

import java.util.HashMap;

import com.google.gwt.media.client.Audio;


public class SoundManager {

	public enum SoundName {FAN1}
	
	private static HashMap<SoundName, Audio> sounds = new HashMap<SoundName, Audio>();
	
	static {
		loadResources();
	}
	
	public static void play(SoundName sound) {
		sounds.get(sound).play();
	}
	
	private static void loadResources() {
		Audio cardFan1 = Audio.createIfSupported();
		cardFan1.setSrc("sounds/cardFan1.wav");
		sounds.put(SoundName.FAN1, cardFan1);
	}
	
}
