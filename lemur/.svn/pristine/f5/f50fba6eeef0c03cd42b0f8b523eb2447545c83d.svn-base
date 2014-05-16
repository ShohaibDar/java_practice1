package com.gardensnake.cyberiq.puzzles;

import android.graphics.Color;

public class SeesawBlock {
	public int xPos, yPos;
	public int xDigitPos, yDigitPos;
	public int blockWeight; // number shown to user: 1,2,3...9
	public int boardPosition;// -3,-2,-1...1,2,3
	public int value;// blockWeight*boardPosition
	public int boardXOrder, boardYOrder; // 0-5, 0-4
	public int color;

	public SeesawBlock(int position, int weight) {
		boardXOrder = position;
		blockWeight = 1 + (weight / 10); // if (w==50) then 1-6

		if (position < 3)
			boardPosition = position - 3;
		else
			boardPosition = position - 2;

		xPos = 145 + (boardPosition * 40);
		xDigitPos = xPos + 15;

		value = blockWeight * boardPosition;

		color = Color.YELLOW;
	}

	public void setHeight(int yPosition) {
		yPos = 160 + yPosition;
		yDigitPos = (int) ((yPos) + (30 * .77));
	}

}
