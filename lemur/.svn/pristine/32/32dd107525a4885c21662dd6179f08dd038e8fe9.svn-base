package com.gardensnake.cyberiq;

import java.util.List;
import java.util.Random;

import android.graphics.Color;

import com.gardensnake.framework.Game;
import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Screen;
import com.gardensnake.framework.Input.TouchEvent;

public class FreePlayResult extends Screen {
	Graphics graphics;
	int puzzleNumber, modeNumber;
	int score;
	Button menu, retry;
	boolean newRecord;
	Random rand = new Random();

	public FreePlayResult(Game game, int puzzleNumber, int modeNumber, int score) {
		super(game);
		graphics = game.getGraphics();
		this.puzzleNumber = puzzleNumber;
		this.modeNumber = modeNumber;
		this.score = score;
		if (score > Settings.freeplayScores[puzzleNumber][0]) {
			newRecord = true;
			Assets.successlightning.play(.75f);
		}
		Settings.addFreePlayScore(puzzleNumber, score); // updates high scores
		Settings.save(game.getFileIO()); // saves

		menu = new Button(graphics, 10, 410, 80, 60, 5, "MENU", 2, 20, 50, 455, Color.GRAY, Color.DKGRAY,
				Color.WHITE, 2);
		retry = new Button(graphics, 230, 410, 80, 60, 5, "RETRY", 2, 20, 270, 455, Color.GRAY, Color.DKGRAY,
				Color.WHITE, 2);

	}

	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {// PAUSE & RESTART
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (menu.click(event.x, event.y)) {
					game.setScreen(new MainMenu(game));
					return;
				}
				if (retry.click(event.x, event.y)) {
					game.setScreen(new FreePlayScreen(game, puzzleNumber, modeNumber, false));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		graphics.drawBackground(true, Color.BLACK);
		graphics.drawText("" + score, 160, 220, 1, 2, 50, Color.RED);
		menu.draw();
		retry.draw();
		if (newRecord) {
			graphics.drawText("NEW RECORD", 160, 290, 1, 2, 20,
					Color.argb(255, rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		}
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
