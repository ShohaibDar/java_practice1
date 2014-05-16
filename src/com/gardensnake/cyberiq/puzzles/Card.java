package com.gardensnake.cyberiq.puzzles;

public class Card {
	public int value; // 0-9
	public boolean flipped; // true: card is shown
	public boolean matched; // true=both cards are flipped
	public int x, y;
	public final int width = 50;
	public final int height = 70;

	public Card(int i) {
		value = i;
	}

}
