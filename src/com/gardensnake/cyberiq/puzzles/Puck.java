package com.gardensnake.cyberiq.puzzles;

import com.gardensnake.cyberiq.Assets;

public class Puck {
	public float x, y;
	public int x2, y2;
	public int width = 30, height = 30;
	public float dirX, dirY;

	public Puck(float x, float y, float dirX, float dirY) {
		this.x = x;
		this.y = y;
		this.dirX = dirX;
		this.dirY = dirY;
	}

	public void update(float deltaTime) {
		x = x + dirX * deltaTime;
		y = y + dirY * deltaTime;

		if (x < 0) {
			dirX = -dirX;
			x = 0;
			Assets.tapglass.play(.4f);
		}

		if (x > 290) {
			dirX = -dirX;
			x = 290;
			Assets.tapglass.play(.4f);
		}

		if (y < 30) {
			dirY = -dirY;
			y = 30;
			Assets.tapglass.play(.4f);
		}

		if (y > 480) {
			dirY = -dirY;
			y = 480;
			Assets.tapglass.play(.4f);
		}

		x2 = (int) x + width;
		y2 = (int) y + height;

	}

}
