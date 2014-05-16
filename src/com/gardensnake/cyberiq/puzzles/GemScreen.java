package com.gardensnake.cyberiq.puzzles;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.cyberiq.Assets;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Input.TouchEvent;

public class GemScreen extends PuzzleScreen {
	GemField field;
	int selected;
	float totalCounterTime, counterTime;
	int counter;

	public GemScreen(Game game, int gameType, int instanceNumber, int difficulty) {
		graphics = game.getGraphics();
		field = new GemField(difficulty);
		state = PuzzleState.A;
		resultTimerLimit = 2.2f;
	}

	@Override
	public void update(List<TouchEvent> touchEvents, float deltaTime) {

		if (state == PuzzleState.A) {// A
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {// FOR
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP) {
					if (event.x > 5 && event.x < 155 && event.y > 38 && event.y < 256) {
						selected = 0;
						state = PuzzleState.B;
						return;
					}
					if (event.x > 165 && event.x < 315 && event.y > 38 && event.y < 256) {
						selected = 1;
						state = PuzzleState.B;
						return;
					}
					if (event.x > 5 && event.x < 155 && event.y > 260 && event.y < 478) {
						selected = 2;
						state = PuzzleState.B;
						return;
					}
					if (event.x > 165 && event.x < 315 && event.y > 260 && event.y < 478) {
						selected = 3;
						state = PuzzleState.B;
						return;
					}

				}
			}
		}// A

		if (state == PuzzleState.B) {// B
			totalCounterTime += deltaTime;
			if (totalCounterTime > counterTime) {
				counterTime += .11f;
				counter++;
				if(counter<=field.jars[field.winnerJar].numberOfGems){
					Assets.numberscroll.play(.6f);
				}
			}
			if (counter > (field.jars[field.winnerJar].numberOfGems + 3)) {
				if (field.ifCorrect(selected)) {
					success = true;
					Assets.successpoint.play(.7f);
				} else {
					Assets.failwindows.play(1);
				}
				state = PuzzleState.C;
			}

		}// B

		if (state == PuzzleState.C) {// C
			resultTimer += deltaTime;
			if (resultTimer > resultTimerLimit)
				instanceOver = true;
		}// C
	}

	@Override
	public void present() {
		graphics.drawBackground(false, Color.WHITE);
		graphics.drawPixmap(Assets.puzzleitems, 5, 38, 20, 320, 150, 218);
		graphics.drawPixmap(Assets.puzzleitems, 165, 38, 20, 320, 150, 218);
		graphics.drawPixmap(Assets.puzzleitems, 5, 260, 20, 320, 150, 218);
		graphics.drawPixmap(Assets.puzzleitems, 165, 260, 20, 320, 150, 218);

		if (state == PuzzleState.A) {// A

			for (int jarNum = 0; jarNum < 4; jarNum++) {// GEM PLACEMENT
				for (int i = 0; i < 12; i++) {
					for (int j = 0; j < 7; j++) {
						if (field.jars[jarNum].gemPosition[j + (i * 7)] == true)
							graphics.drawPixmap(Assets.puzzleitems, 13 + j * 19 + (160 * (jarNum % 2)), 57
									+ (i * 16) + (222 * (jarNum / 2)), 390, 380, 20, 16);
					}
				}
			}// GEM PLACEMENT
		}// A

		if (state == PuzzleState.B || state == PuzzleState.C) {// B
			for (int i = 0; i < 4; i++) {
				if (counter < field.jars[i].numberOfGems) {
					graphics.drawText("" + counter, 75 + ((i % 2) * 160), 150 + (222 * (i / 2)), 1, 2, 46,
							Color.BLUE);
				} else {
					graphics.drawText("" + field.jars[i].numberOfGems, 75 + ((i % 2) * 160),
							150 + (222 * (i / 2)), 1, 2, 46, Color.BLUE);
				}

			}
		}// B

		if (state == PuzzleState.C) {
			if (success)
				graphics.drawText("RIGHT!", 160, 240, 1, 2, 70, Color.GREEN);
			else
				graphics.drawText("WRONG", 160, 240, 1, 2, 70, Color.RED);
		}

	}

}
