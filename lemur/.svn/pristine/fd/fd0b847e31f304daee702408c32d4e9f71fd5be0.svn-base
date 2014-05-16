package com.gardensnake.cyberiq.puzzles;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.cyberiq.Assets;
import com.gardensnake.cyberiq.Button;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Input.TouchEvent;

public class MathScreen extends PuzzleScreen {
	MathField math;
	Button[] boxes = new Button[8];

	public MathScreen(Game game, int gameType, int instanceNumber, int difficulty) {
		this.graphics = game.getGraphics();
		this.difficulty = difficulty;
		math = new MathField(difficulty);
		for (int i = 0; i < 8; i++) {
			boxes[i] = new Button(graphics, 0 + (64 * ((i % 4) + ((i % 4) / 2))), 352 + (64 * (i / 4)), 64,
					64, 5, "" + math.choice[i].value, 2, 32, 32 + (64 * ((i % 4) + ((i % 4) / 2))),
					396 + (64 * (i / 4)), Color.TRANSPARENT, Color.BLACK, Color.BLACK, 5);
		}
		state = PuzzleState.Start;
		resultTimerLimit = 2.4f;
	}

	@Override
	public void update(List<TouchEvent> touchEvents, float deltaTime) {

		if (state == PuzzleState.Start && math.playerSelection.size() == math.TOTALOPERANDS) {
			if (success = math.checkAnswer()) {
				Assets.successclassic.play(.5f);
			} else {
				Assets.failclassic.play(1);
			}
			state = PuzzleState.Finish;
		}
		if (state == PuzzleState.Start) {// START
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {// FOR
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP && event.y > 31) {
					for (int j = 0; j < 8; j++) {
						if (boxes[j].click(event.x, event.y)) {// BOX HIT
							math.selected(j);
							if (boxes[j].backgroundColor == Color.TRANSPARENT) {
								boxes[j].backgroundColor = Color.GREEN;
							} else if (boxes[j].backgroundColor == Color.GREEN) {
								boxes[j].backgroundColor = Color.TRANSPARENT;
							}
						}// BOX HIT
					}
				}
			}
		}// START
		if (state == PuzzleState.Finish) {
			resultTimer += deltaTime;
			if (resultTimer > resultTimerLimit) {
				instanceOver = true;
			}
		}
	}

	@Override
	public void present() {
		graphics.fillRect(0, 31, 320, 450, Color.WHITE);
		graphics.drawText("" + math.expression(), 160, 240, 1, 1, 32, Color.BLACK);
		for (int i = 0; i < 8; i++)
			boxes[i].draw();

		if (state == PuzzleState.Finish) {// FINISH
			if (success)
				graphics.drawText("That's correct!", 160, 130, 1, 2, 34, Color.GREEN);
			else {
				graphics.drawText("incorrect", 160, 130, 1, 2, 36, Color.RED);
				graphics.drawText(math.correctingMistake, 160, 160, 1, 1, 16, Color.GREEN);
			}

		}// FINISH

	}

}
