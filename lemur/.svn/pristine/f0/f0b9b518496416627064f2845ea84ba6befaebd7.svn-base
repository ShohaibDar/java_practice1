package com.gardensnake.cyberiq.puzzles;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.cyberiq.Assets;
import com.gardensnake.cyberiq.Button;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Input.TouchEvent;

public class GoalieScreen extends PuzzleScreen {
	GoalieField field;
	Button ready;
	boolean touch; // true when block is held;

	public GoalieScreen(Game game, int gameType, int instanceNumber, int difficulty) {
		graphics = game.getGraphics();
		this.difficulty = difficulty;
		state = PuzzleState.A;
		field = new GoalieField(difficulty);
		ready = new Button(graphics, 180, 300, 60, 40, 3, "READY", 2, 17, 210, 328, Color.GRAY, Color.DKGRAY,
				Color.WHITE, 0);
		resultTimerLimit = 2;
	}

	@Override
	public void update(List<TouchEvent> touchEvents, float deltaTime) {
		if (state == PuzzleState.A)// READY
			updateA(touchEvents);
		if (state == PuzzleState.B)// SET1
			updateB(touchEvents);
		if (state == PuzzleState.C)
			updateC(deltaTime);
		if (state == PuzzleState.D)
			updateD(deltaTime);

	}

	private void updateA(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {// FOR
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED) {
				if (event.x > field.block.x && event.x < field.block.x2 && event.y > field.block.y
						&& event.y < field.block.y2) {
					field.block.set(event.x, event.y);
					if (touch == false) {
						touch = true;
						Assets.tapmouse.play(1);
					}
				}

			}
			if (event.type == TouchEvent.TOUCH_UP) {// B
				touch=false;
				if (event.x > field.block.x && event.x < field.block.x2 && event.y > field.block.y
						&& event.y < field.block.y2) {
					Assets.tapmouse.play(1);
				}
				if (field.block.y > 400) {
					state = PuzzleState.B;
				}
			}// B
		}

	}

	private void updateB(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {// FOR
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_DOWN || event.type == TouchEvent.TOUCH_DRAGGED) {
				if (event.x > field.block.x && event.x < field.block.x2 && event.y > field.block.y
						&& event.y < field.block.y2) {
					field.block.set(event.x, event.y);
					if (touch == false) {
						touch = true;
						Assets.tapmouse.play(1);
					}
				}

			}// DOWN && DRAGGED

			if (event.type == TouchEvent.TOUCH_UP) {// UP
				touch = false;
				if (event.x > field.block.x && event.x < field.block.x2 && event.y > field.block.y
						&& event.y < field.block.y2) {
					Assets.tapmouse.play(1);
				}
				if (field.block.y < 400) {
					state = PuzzleState.A;
					return;
				}
				if (ready.click(event.x, event.y)) {
					state = PuzzleState.C;
					return;
				}
			}// UP

		}// FOR
	}

	private void updateC(float deltaTime) {
		field.puck.update(deltaTime);
		if (field.checkCollision()) {
			success = true;
			state = PuzzleState.D;
			return;
		}
		if (field.checkGoal()) {
			Assets.failclassicdull.play(1);
			state = PuzzleState.D;
			return;
		}
	}

	private void updateD(float deltaTime) {
		resultTimer += deltaTime;
		if (resultTimer > resultTimerLimit)
			instanceOver = true;
	}

	@Override
	public void present() {
		graphics.drawBackground(false, Color.WHITE);
		graphics.drawLine(0, 31, 320, 31, 2, Color.BLACK);
		graphics.drawLine(0, 400, 320, 400, 2, Color.BLACK);

		graphics.fillRect(field.block.x, field.block.y, field.block.width, field.block.height, Color.BLUE);
		graphics.traceRect(field.block.x, field.block.y, field.block.width, field.block.height, 3,
				Color.BLACK);
		graphics.drawPixmap(Assets.puzzleitems, (int) field.puck.x, (int) field.puck.y, 360, 380, 30, 30);
		if (state == PuzzleState.A)// READY
			presentA();
		if (state == PuzzleState.B)// SET1
			presentB();
		if (state == PuzzleState.C)
			presentC();
		if (state == PuzzleState.D)
			presentD();
		/*
		 * graphics.drawText("D: " + difficulty + " W: " + field.width + " P:" +
		 * field.slope, 160, 100, 1, 1, 12, Color.RED);// TEST
		 * graphics.drawText("X: " + field.puckXPosition + " <> Y: " +
		 * field.puckYPosition, 160, 114, 1, 1, 12, Color.RED);// TEST2
		 * graphics.drawText("dx: " + field.dx + " dy: " + field.dy, 160, 128,
		 * 1, 1, 12, Color.RED);// TEST3
		 */}

	private void presentA() {
		graphics.drawTrajectory(field.puck.x + 15, field.puck.y + 15, (field.puck.x + field.puck.dirX + 15),
				(field.puck.y + field.puck.dirY + 15), Color.RED);
		// graphics.drawText("Grab Block", 320, 280, 1, 1, 14, Color.CYAN);
	}

	private void presentB() {
		ready.draw();
	}

	private void presentC() {
	}

	private void presentD() {
		if (success)
			graphics.drawText("SUCCESS", 160, 200, 1, 2, 40, Color.GREEN);
		else
			graphics.drawText("FAIL", 160, 200, 1, 2, 40, Color.RED);
	}

}
