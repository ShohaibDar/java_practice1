package com.gardensnake.cyberiq;

import com.gardensnake.framework.Screen;
import com.gardensnake.framework.impl.AndroidGame;

public class CyberIQ extends AndroidGame{

	public Screen getStartScreen() {
		return new Loading(this);
	}

}
