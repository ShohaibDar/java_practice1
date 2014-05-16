package com.gardensnake.cyberiq;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.cyberiq.puzzles.PuzzleData;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Input.TouchEvent;
import com.gardensnake.framework.Screen;

public class FreePlayMode extends Screen {
	Graphics graphics;
	PuzzleData data = new PuzzleData();
	int puzzleNumber;
	int topScore;
	Button[] modeBanner = new Button[3];
	Button back;

	public FreePlayMode(Game game, int puzzleNumber) {
		super(game);
		graphics = game.getGraphics();
		this.puzzleNumber = puzzleNumber;
		topScore = Settings.freeplayScores[puzzleNumber][0];
		modeBanner[0] = new Button(graphics, 40, 80, 240, 70, 5, "EASY", 2, 44, 160, 135, Color.TRANSPARENT,
				Color.WHITE, Color.BLUE, 3);
		modeBanner[1] = new Button(graphics, 40, 160, 240, 70, 5, "NORMAL", 2, 44, 160, 215,
				Color.TRANSPARENT, Color.WHITE, Color.GREEN, 3);
		modeBanner[2] = new Button(graphics, 40, 240, 240, 70, 5, "HARD", 2, 44, 160, 295, Color.TRANSPARENT,
				Color.WHITE, Color.RED, 3);
		back = new Button(graphics, 2);

	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				for (int j = 0; j < 3; j++) {

					if (modeBanner[j].click(event.x, event.y))
						game.setScreen(new FreePlayScreen(game, puzzleNumber, j,true));
				}

				if (back.click(event.x, event.y))
					game.setScreen(new FreePlayMenu(game));
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		graphics.drawBackground(true, Color.BLACK);

		graphics.drawText(data.titles[puzzleNumber], 160, 50, 1, 2, 30, Color.WHITE);

		for (int i = 0; i < 3; i++)
			modeBanner[i].draw();

		graphics.drawText("Top Score: " + topScore, 160, 460, 1, 2, 16, Color.GRAY);

		back.draw();

	}

	@Override
	public void pause() {
		Assets.background0.pause();
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
