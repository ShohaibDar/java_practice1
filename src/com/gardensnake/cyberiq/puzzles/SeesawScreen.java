package com.gardensnake.cyberiq.puzzles;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.cyberiq.Assets;
import com.gardensnake.cyberiq.Button;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Input.TouchEvent;

public class SeesawScreen extends PuzzleScreen {
	SeesawBoard board;
	Button blocks;
	int selected;// 0:left, 1:right
	float totalCounterTime, counterTime;
	int counter;

	public SeesawScreen(Game game, int gameType, int instanceNumber, int difficulty) {
		graphics = game.getGraphics();
		this.difficulty = difficulty;
		board = new SeesawBoard(difficulty);
		state = PuzzleState.A;
		resultTimerLimit = 2.2f;
	}

	@Override
	public void update(List<TouchEvent> touchEvents, float deltaTime) {

		if (state == PuzzleState.A) {// A
			int len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP) {
					if (event.x > 20 && event.x < 80 && event.y > 350 && event.y < 410) {
						state = PuzzleState.B;
						selected = 0;
						return;
					}
					if (event.x > 240 && event.x < 300 && event.y > 350 && event.y < 410) {
						state = PuzzleState.B;
						selected = 1;
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
				if (counter < 20) {
					Assets.numberscroll.play(.6f);
				}
			}
			if (counter < 20) {
				if (selected == board.correctSide) {
					success = true;
					Assets.successlevelup.play(.6f);
				} else {
					Assets.failjeopardy.play(1);
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

		graphics.drawPixmap(Assets.puzzleitems, 20, 190, 170, 320, 280, 60);// board

		graphics.drawText("0: " + board.boardPositionTotalValue[0] + " 1: "
				+ board.boardPositionTotalValue[1] + " 2: " + board.boardPositionTotalValue[2] + " 3: "
				+ board.boardPositionTotalValue[3] + " 4: " + board.boardPositionTotalValue[4] + " 5: "
				+ board.boardPositionTotalValue[5], 160, 420, 1, 1, 12, Color.BLACK);

		for (int i = 0; i < board.blocks.length; i++) {
			graphics.fillRect(board.blocks[i].xPos, board.blocks[i].yPos, 30, 30, board.blocks[i].color);
			graphics.traceRect(board.blocks[i].xPos, board.blocks[i].yPos, 30, 30, 3, Color.BLACK);
			graphics.drawText("" + board.blocks[i].blockWeight, board.blocks[i].xDigitPos,
					board.blocks[i].yDigitPos, 1, 2, 22, Color.BLACK);

		}

		if (state == PuzzleState.A) {// A
			graphics.drawText("Which side is heavier", 160, 325, 1, 2, 20, Color.BLUE);
			graphics.drawPixmap(Assets.puzzleitems, 20, 350, 240, 380, 60, 60);// left
			graphics.drawPixmap(Assets.puzzleitems, 240, 350, 300, 380, 60, 60);// right
		}// A

		if (state == PuzzleState.B || state == PuzzleState.C) {// B
			for (int i = 0; i < 2; i++) {
				if (counter < 3) {
					graphics.drawText("" + counter, 80 + (i * 160), 300, 1, 2, 64, Color.BLUE);
				} else {
					graphics.drawText("" + 3, 80 + (i * 160), 300, 1, 2, 64, Color.BLUE);
				}
			}
		}// B

		if (state == PuzzleState.C) {// C
			if (success)
				graphics.drawText("gOoD!", 160, 340, 1, 2, 50, Color.GREEN);
			else
				graphics.drawText("nO GoOd", 160, 340, 1, 2, 50, Color.RED);
		}// C

	}

}
