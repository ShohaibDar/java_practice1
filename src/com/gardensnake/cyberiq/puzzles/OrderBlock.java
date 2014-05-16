package com.gardensnake.cyberiq.puzzles;

import android.graphics.Color;

import com.gardensnake.framework.Graphics;

public class OrderBlock {
	public Graphics graphics;
	public double vaule;
	public int num1, num2, num3;
	public int x, y, fontX, fontY, width = 70, height = 50;
	public int backgroundColor = Color.GREEN, borderColor = Color.DKGRAY, textColor = Color.BLACK;
	boolean fraction;

	public OrderBlock(Graphics graphics, int x, int y, int num1) {
		this.graphics = graphics;
		this.x = x;
		this.y = y;
		this.num1 = num1;
		fraction = false;
		fontX = x + (width / 2);
		fontY = y + (int) (height * .77);

	}

	public OrderBlock(Graphics graphics, int x, int y, int num1, int num2, int num3) {
		this.graphics = graphics;
		this.x = x;
		this.y = y;
		this.num1 = num1;
		this.num2 = num2;
		this.num3 = num3;
		fraction = true;
		fontX = x + (width / 2);
		fontY = y + (int) (height * .7);

	}

	public boolean click(int xPos, int yPos) {
		if (xPos > x && xPos < x + width && yPos > y && yPos < y + height) {
			return true;
		}
		return false;
	}

	public void drawBlock() {
		graphics.fillRect(x, y, width, height, backgroundColor);
		graphics.traceRect(x, y, width, height, 3, borderColor);
		if (!fraction) {
			graphics.drawText(num1 + "", fontX, fontY, 1, 1, 25, textColor);
		} else {
			graphics.drawText(num1 + "", fontX - 12, fontY, 1, 1, 25, textColor);
			graphics.drawText(num2 + "", fontX + 10, fontY - 14, 1, 1, 14, textColor);
			graphics.drawText(num3 + "", fontX + 20, fontY + 6, 1, 1, 14, textColor);
			graphics.drawLine(x + (width / 2) + 3, y + height - 7, x + width - 7, y + 9, 2, Color.BLACK);
		}

	}
}
