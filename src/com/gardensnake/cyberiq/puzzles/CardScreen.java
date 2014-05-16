package com.gardensnake.cyberiq.puzzles;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.cyberiq.Assets;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Input.TouchEvent;

public class CardScreen extends PuzzleScreen {
	CardField deck;

	public CardScreen(Game game, int gameType, int instanceNumber, int difficulty) {
		this.graphics = game.getGraphics();
		this.difficulty = difficulty;
		state = PuzzleState.Start;
		deck = new CardField(difficulty);
		resultTimerLimit = 2;
	}

	@Override
	public void update(List<TouchEvent> touchEvents, float deltaTime) {
		if (state == PuzzleState.Start)
			updateStart(touchEvents);
		if (state == PuzzleState.A)
			updateA(deltaTime);
		if (state == PuzzleState.B)
			updateB(deltaTime);
	}

	private void updateStart(List<TouchEvent> touchEvents) {// PLAY
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {// FLIP
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (deck.totalFlipped < 2)
					deck.flip(event.x, event.y);
			}
		}// FLIP

		if (deck.totalFlipped > 1) {// ***2ND CARD FLIPPED, CHECK MATCH
			deck.totalTurns--;
			if (deck.isMatch()) {// GOOD MATCH
				if (isGameOver()) {
					state = PuzzleState.B;
				} else {
					deck.reset();
				}
			}// GOOD MATCH

			else {// BAD MATCH

				if (isGameOver()) {
					state = PuzzleState.B;
				} else {
					// Assets.fail0.play(1);
					state = PuzzleState.A;
				}

			}// BAD MATCH

			deck.totalFlipped = 0;

		}// ****2ND CARD FLIPPED, CHECK MATCH

	}

	private void updateA(float deltaTime) {// BAD MATCH
		resultTimer += deltaTime;
		if (resultTimer > .75) {
			resultTimer = 0;
			deck.reset();
			state = PuzzleState.Start;
		}
	}

	private void updateB(float deltaTime) {// GAME OVER
		resultTimer += deltaTime;
		if (resultTimer > resultTimerLimit) {
			instanceOver = true;
		}

	}

	@Override
	public void present() {

		graphics.drawBackground(false, Color.BLACK);

		for (int i = 0; i < deck.numberOfCards; i++) {
			if (deck.cards[i].flipped || deck.cards[i].matched) {// FACE UP
				graphics.drawPixmap(Assets.puzzleitems, deck.cards[i].x, deck.cards[i].y,
						20 + (deck.cards[i].value * 50), 0, 50, 70);

			} else {// FACE DOWN
				graphics.fillRect(deck.cards[i].x, deck.cards[i].y, deck.cards[i].width,
						deck.cards[i].height, Color.GRAY);
				graphics.traceRect(deck.cards[i].x, deck.cards[i].y, deck.cards[i].width,
						deck.cards[i].height, 3, Color.DKGRAY);
			}
		}

		if (state != PuzzleState.B) {
			graphics.drawText("Turns left: " + deck.totalTurns, 160, 66, 1, 1, 24, Color.WHITE);
		} else {
			if (success) {
				graphics.drawText("Complete!", 160, 56, 1, 1, 24, Color.GREEN);
			} else {
				graphics.drawText("out of turns", 160, 66, 1, 1, 30, Color.RED);
			}
		}
	}

	private boolean isGameOver() {

		if (deck.isComplete()) {// SUCCESS!
			instanceBonus += deck.totalTurns;
			success = true;
			Assets.successclassic.play(.5f);
			return true;
		}// SUCCESS!

		if (deck.totalTurns < 1) {// OUT OF TURNS
			Assets.failclassic.play(1);
			return true;
		}// OUT OF TURNS

		return false;
	}

}
