package com.blackjack.client.sounds;

import java.util.HashMap;

import com.google.gwt.media.client.Audio;

/**
 * The SoundManager is used to create, and play sounds for the application.
 * @author Dave
 *
 */
public class SoundManager {

	/**
	 * An enumerator of all sounds created.
	 */
	public enum SoundName {FAN1, CHIP_BET, PLACE1, PLACE2, PLACE3, PLACE4, STAND, BUST, WIN}
	
	/**
	 * A dictionary that stores all enums with their sound value.
	 */
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
		
		Audio chipBet = Audio.createIfSupported();
		chipBet.setSrc("sounds/chipsHandle6.wav");
		sounds.put(SoundName.CHIP_BET, chipBet);
		
		Audio cardPlace1 = Audio.createIfSupported();
		cardPlace1.setSrc("sounds/cardPlace1.wav");
		sounds.put(SoundName.PLACE1, cardPlace1);
		
		Audio cardPlace2 = Audio.createIfSupported();
		cardPlace2.setSrc("sounds/cardPlace2.wav");
		sounds.put(SoundName.PLACE2, cardPlace2);
		
		Audio cardPlace3 = Audio.createIfSupported();
		cardPlace3.setSrc("sounds/cardPlace3.wav");
		sounds.put(SoundName.PLACE3, cardPlace3);
		
		Audio cardPlace4 = Audio.createIfSupported();
		cardPlace4.setSrc("sounds/cardPlace4.wav");
		sounds.put(SoundName.PLACE4, cardPlace4);
		
		Audio stand = Audio.createIfSupported();
		stand.setSrc("sounds/knock.mp3");
		sounds.put(SoundName.STAND, stand);
		
		Audio bust = Audio.createIfSupported();
		bust.setSrc("sounds/bust.wav");
		sounds.put(SoundName.BUST, bust);
		
		Audio win = Audio.createIfSupported();
		win.setSrc("sounds/win.wav");
		sounds.put(SoundName.WIN, win);
	}
	
}
