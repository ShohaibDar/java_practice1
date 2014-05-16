package com.gardensnake.cyberiq.puzzles;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.framework.Game;
import com.gardensnake.framework.Input.TouchEvent;

public class OrderScreen extends PuzzleScreen {
	OrderField field;

	public OrderScreen(Game game, int gameType, int instanceNumber, int difficulty) {
		this.graphics = game.getGraphics();
		this.difficulty = difficulty;
		field = new OrderField(graphics, difficulty);
	}

	@Override
	public void update(List<TouchEvent> touchEvents, float deltaTime) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {// FOR
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				instanceOver = true;
			}
		}
	}

	@Override
	public void present() {
		graphics.drawBackground(false, Color.BLACK);
		for (int i = 0; i < field.numOfBlocks; i++) {
			field.blocks[i].drawBlock();
		}
	}

}
