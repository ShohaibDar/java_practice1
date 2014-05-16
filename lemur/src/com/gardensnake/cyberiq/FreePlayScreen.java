package com.gardensnake.cyberiq;

import java.util.List;
import java.util.Random;

import android.graphics.Color;

import com.gardensnake.cyberiq.puzzles.CardScreen;
import com.gardensnake.cyberiq.puzzles.GemScreen;
import com.gardensnake.cyberiq.puzzles.GoalieScreen;
import com.gardensnake.cyberiq.puzzles.MathScreen;
import com.gardensnake.cyberiq.puzzles.OrderScreen;
import com.gardensnake.cyberiq.puzzles.PuzzleData;
import com.gardensnake.cyberiq.puzzles.PuzzleScreen;
import com.gardensnake.cyberiq.puzzles.SeesawScreen;
import com.gardensnake.framework.Game;
import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Input.TouchEvent;
import com.gardensnake.framework.Screen;

public class FreePlayScreen extends Screen {
	enum GameState {
		Instructions, Running, Paused, GameOver
	}

	static final int FREEPLAYTIMELIMIT = 105;
	Graphics graphics;
	PuzzleData data = new PuzzleData();
	PuzzleScreen puzzle;
	GameState state;
	float elapsedTime = 0;
	Button ready;
	int totalScore, turnScore;
	Random rand = new Random();
	int puzzleNumber;
	int len;
	int difficulty;
	int modeNumber; // 0=Easy, 1=Normal, 2=Hard
	boolean justScored;
	float justScoredTimer;
	boolean wait;
	float waitTimer;
	final int[][] modeDifficulty = {
			{ 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5 },
			{ 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 8, 8,
					8, 8, 9, 9, }, { 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 10, 10 } };
	// hard: 6,6,7,7,7,8,8,8,8,9,9,9,10,10

	Button pause, restart;

	public FreePlayScreen(Game game, int puzzleNumber, int modeNumber, boolean instructionsOn) {
		super(game);
		graphics = game.getGraphics();
		this.puzzleNumber = puzzleNumber;
		this.modeNumber = modeNumber;
		ready = new Button(graphics, 100, 320, 120, 60, 3, "READY", 1, 34, 160, 365, Color.TRANSPARENT,
				Color.GRAY, Color.GRAY, 3);
		pause = new Button(graphics, 5);
		restart = new Button(graphics, 6);
		if (instructionsOn)
			state = GameState.Instructions;
		else {
			launchPuzzle();
			state = GameState.Running;
		}
	}

	@Override
	public void update(float deltaTime) {

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();

		if (state == GameState.Instructions)
			updateInstructions(touchEvents, deltaTime);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);

	}

	private void updateInstructions(List<TouchEvent> touchEvents, float deltaTime) {
		if (wait) {
			waitTimer += deltaTime;
			if (waitTimer > .1) {// .1 sec to start puzzle
				launchPuzzle();
				state = GameState.Running;
			}
		} else {
			len = touchEvents.size();
			for (int i = 0; i < len; i++) {
				TouchEvent event = touchEvents.get(i);
				if (event.type == TouchEvent.TOUCH_UP) {
					if (ready.click(event.x, event.y)) {
						wait = true;
					}
				}
			}
		}

	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		elapsedTime += deltaTime;

		if (justScored) {
			justScoredTimer += deltaTime;
			if (justScoredTimer > 2) {
				justScored = false;
				justScoredTimer = 0;
			}
		}

		if (elapsedTime > FREEPLAYTIMELIMIT) {
			if (puzzle.success)
				addTurnScore();// LAST second protection
			game.setScreen(new FreePlayResult(game, puzzleNumber, modeNumber, totalScore));
		}

		if (puzzle.instanceOver) {
			if (puzzle.success)
				addTurnScore();
			launchPuzzle();
		}

		len = touchEvents.size();
		for (int i = 0; i < len; i++) {// PAUSE & RESTART
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (pause.click(event.x, event.y)) {
					state = GameState.Paused;
					return;
				}
				if (restart.click(event.x, event.y)) {// RESTART
					game.setScreen(new FreePlayScreen(game, puzzleNumber, modeNumber, true));
					return;
				}// RESTART
			}
		}// PAUSE & RESTART

		puzzle.update(touchEvents, deltaTime);

	}

	private void updatePaused(List<TouchEvent> touchEvents) {

		len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (event.y > 110 && event.y < 185) {
					Assets.button0.play(1);
					state = GameState.Running;
					return;
				}
				if (event.x > 70 && event.x < 245 && event.y > 230 && event.y < 305) {
					Assets.button0.play(1);
					game.setScreen(new MainMenu(game));
					return;
				}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		if (state == GameState.Instructions)
			presentInstructions();
		if (state == GameState.Running)
			presentRunning();
		if (state == GameState.Paused)
			presentPaused();

	}

	private void presentInstructions() {// NEEDS WORK!
		graphics.drawBackground(true, Color.BLACK);
		graphics.drawText(data.titles[puzzleNumber], 160, 110, 1, 1, 34, Color.WHITE);
		graphics.drawLine(80, 120, 240, 120, 5, Color.WHITE);
		graphics.drawText(data.line1[puzzleNumber], 160, 160, 1, 1, 18, data.colors[puzzleNumber]);
		graphics.drawText(data.line2[puzzleNumber], 160, 190, 1, 1, 18, data.colors[puzzleNumber]);
		graphics.drawText(data.line3[puzzleNumber], 160, 220, 1, 1, 18, data.colors[puzzleNumber]);
		ready.draw();
	}

	private void presentPaused() {
		graphics.drawBackground(true, Color.BLACK);
		graphics.drawText("RESUME", 160, 180, 1, 2, 80, Color.GREEN);
		graphics.drawText("QUIT", 160, 300, 1, 2, 80, Color.RED);
	}

	private void presentRunning() {
		graphics.fillRect(0, 0, 320, 30, Color.BLACK);
		graphics.drawText(data.titles[puzzleNumber], 160, 24, 1, 1, 24, data.colors[puzzleNumber]);
		graphics.drawText("" + (totalScore), 38, 22, 0, 1, 18, Color.WHITE);
		if (justScored)
			graphics.drawText("+" + (turnScore), 75, 22, 0, 1, 18, Color.YELLOW);
		graphics.drawText("" + (int) (FREEPLAYTIMELIMIT - elapsedTime), 280, 22, 2, 1, 18, Color.WHITE);
		pause.draw();
		restart.draw();
		graphics.drawLine(0, 30, 320, 30, 1, Color.WHITE);
		puzzle.present();
	}

	private void launchPuzzle() {
		difficulty = modeDifficulty[modeNumber][rand.nextInt(modeDifficulty[modeNumber].length)];

		if (puzzleNumber == 0)
			puzzle = new MathScreen(game, 1, 0, difficulty);
		if (puzzleNumber == 1)
			puzzle = new CardScreen(game, 1, 0, difficulty);
		if (puzzleNumber == 2)
			puzzle = new GoalieScreen(game, 1, 0, difficulty);
		if (puzzleNumber == 5)
			puzzle = new GemScreen(game, 1, 0, difficulty);
		if (puzzleNumber == 8)
			puzzle = new OrderScreen(game, 1, 0, difficulty);
	}

	private void addTurnScore() {
		turnScore = ((difficulty + data.pointFactor[puzzleNumber][0]) * data.pointFactor[puzzleNumber][1])
				+ data.pointFactor[puzzleNumber][2] + puzzle.instanceBonus;
		totalScore += turnScore;
		justScored = true;

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
