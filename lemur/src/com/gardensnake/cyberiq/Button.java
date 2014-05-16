package com.gardensnake.cyberiq;

import com.gardensnake.framework.Graphics;

public class Button {
	public Graphics graphics;
	public int x, y;
	public int width, height;
	public int srcX, srcY;
	int fontStyle;
	String text;
	int textSize;
	int strokeWidth;
	int fontXPosition, fontYPosition;
	public int backgroundColor, borderColor, textColor;
	boolean isPixmap;
	int soundNumber; // -1:no sound, 0:clink, 1:rubber pop 2: alternate button 3:main
						// button 4:rubber,5:keyboard

	/**
	 * This constructor is for pixmaps found in Assets folder
	 * 
	 * @param graphics
	 * @param buttonNumber
	 */
	public Button(Graphics graphics, int buttonNumber) {
		isPixmap = true;
		this.graphics = graphics;

		if (buttonNumber == 0) {// soundOn
			this.x = 0;
			this.y = 440;
			this.width = 40;
			this.height = 40;
			this.srcX = 170;
			this.srcY = 0;
			this.soundNumber = 0;
		}

		if (buttonNumber == 1) {// soundOff
			this.x = 0;
			this.y = 440;
			this.width = 40;
			this.height = 40;
			this.srcX = 210;
			this.srcY = 0;
			this.soundNumber = 0;
		}

		if (buttonNumber == 2) {// back
			this.x = 0;
			this.y = 430;
			this.width = 60;
			this.height = 50;
			this.srcX = 0;
			this.srcY = 0;
			this.soundNumber = 2;
		}
		if (buttonNumber == 3) {// forward
			this.x = 260;
			this.y = 430;
			this.width = 60;
			this.height = 50;
			this.srcX = 60;
			this.srcY = 0;
			this.soundNumber = 2;
		}
		if (buttonNumber == 4) {// home
			this.x = 135;
			this.y = 430;
			this.width = 50;
			this.height = 50;
			this.srcX = 120;
			this.srcY = 0;
			this.soundNumber = 2;
		}

		if (buttonNumber == 5) { // pause
			this.x = 0;
			this.y = 0;
			this.width = 30;
			this.height = 30;
			this.srcX = 0;
			this.srcY = 50;
			this.soundNumber = 2;
		}

		if (buttonNumber == 6) {// restart
			this.x = 290;
			this.y = 0;
			this.width = 30;
			this.height = 30;
			this.srcX = 30;
			this.srcY = 50;
			this.soundNumber = 2;
		}

	}

	/**
	 * Manually Created Buttons
	 */
	public Button(Graphics graphics, int x, int y, int width, int height, int strokeWidth, String text,
			int fontStyle, int textSize, int fontXPosition, int fontYPosition, int backgroundColor,
			int borderColor, int textColor, int soundNumber) {
		isPixmap = false;

		this.graphics = graphics;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.strokeWidth = strokeWidth;
		this.text = text;
		this.fontStyle = fontStyle;
		this.textSize = textSize;
		this.fontYPosition = fontYPosition;
		this.fontXPosition = fontXPosition;

		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;
		this.textColor = textColor;

		this.soundNumber = soundNumber;

	}

	public void setPosition(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fontYPosition = (int) (y + height * .77) - strokeWidth;
		this.fontXPosition = x + (width / 2);

	}

	public void draw() {
		if (isPixmap) {
			graphics.drawPixmap(Assets.buttons, x, y, srcX, srcY, width, height);
		} else {
			graphics.fillRect(x, y, width, height, backgroundColor);
			graphics.traceRect(x, y, width, height, strokeWidth, borderColor);
			graphics.drawText(text, fontXPosition, fontYPosition, 1, fontStyle, textSize, textColor);
		}
	}

	public boolean click(int xPos, int yPos) {
		if (xPos > x && xPos < x + width && yPos > y && yPos < y + height) {
			if (soundNumber == 0)
				Assets.button0.play(1);
			if (soundNumber == 1)
				Assets.button1.play(1);
			if (soundNumber == 2)
				Assets.button2.play(1);
			if (soundNumber == 3)
				Assets.button3.play(1);
			if (soundNumber == 4)
				Assets.button4.play(1);
			if(soundNumber==5)
				Assets.keyboard.play(.4f);
			return true;
		}
		return false;
	}

}
