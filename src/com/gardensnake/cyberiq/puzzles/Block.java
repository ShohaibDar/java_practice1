package com.gardensnake.cyberiq.puzzles;

public class Block {

	public int x;
	public int y;
	public int x2;
	public int y2;
	public int width;
	public int height;

	public Block(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		x2 = x + w;
		y2 = y + h;
	}

	public void set(int midX, int midY) {
		x = midX - (width / 2);
		y = midY - (height / 2);

		if (x < 0) {
			x = 0;
		}
		if (y < 31) {
			y = 31;
		}
		if (x + width > 320) {
			x = 320 - width;
		}
		if (y + height > 480) {
			y = 480 - height;
		}

		x2 = x + width;
		y2 = y + height;
	}

}
