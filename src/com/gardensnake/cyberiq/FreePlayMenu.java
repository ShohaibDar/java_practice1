package com.gardensnake.cyberiq;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.cyberiq.puzzles.PuzzleData;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Screen;
import com.gardensnake.framework.Input.TouchEvent;

public class FreePlayMenu extends Screen {
	enum Page {
		Page1, Page2
	}

	Graphics graphics;
	Page currentPage = Page.Page1;
	Button back, forward;
	PuzzleData data = new PuzzleData();
	Button[] puzzleBanner = new Button[data.numberOfPuzzles];

	public FreePlayMenu(Game game) {
		super(game);
		graphics = game.getGraphics();

		for (int i = 0; i < data.numberOfPuzzles; i++) {
			puzzleBanner[i] = new Button(graphics, 2, 5 + (80 * (i % 5)), 316, 70, 5, data.titles[i], 2, 40,
					200, (80 * (i % 5)) + 62, Color.TRANSPARENT, Color.RED, data.colors[i], 3);
		}

		back = new Button(graphics, 2);
		forward = new Button(graphics, 3);

	}

	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {

				if (currentPage == Page.Page1) {// PAGE 1

					for (int j = 0; j < 5; j++) {
						if (puzzleBanner[j].click(event.x, event.y))
							game.setScreen(new FreePlayMode(game, j));

					}

					if (back.click(event.x, event.y))
						game.setScreen(new MainMenu(game));

					if (forward.click(event.x, event.y))
						currentPage = Page.Page2;

				}// PAGE1

				if (currentPage == Page.Page2) {// PAGE2

					for (int j = 5; j < data.numberOfPuzzles; j++) {
						if (puzzleBanner[j].click(event.x, event.y))
							game.setScreen(new FreePlayMode(game, j));
					}

					if (back.click(event.x, event.y))
						currentPage = Page.Page1;

				}// PAGE2
			}
		}
	}

	@Override
	public void present(float deltaTime) {// PRESENT

		graphics.drawBackground(true, Color.BLACK);

		if (currentPage == Page.Page1) { // PAGE 1
			for (int i = 0; i < 5; i++) {
				graphics.drawLine(72, 5 + (80 * i), 72, 75 + (80 * i), 5, Color.RED);
				puzzleBanner[i].draw();
			}
			back.draw();
			forward.draw();
		}// PAGE 1

		if (currentPage == Page.Page2) {// PAGE 2
			for (int i = 5; i < data.numberOfPuzzles; i++) {
				graphics.drawLine(72, 5 + (80 * (i - 5)), 72, 75 + (80 * (i - 5)), 5, Color.RED);
				puzzleBanner[i].draw();
			}
			back.draw();
		}// PAGE 2

	}// PRESENT

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
