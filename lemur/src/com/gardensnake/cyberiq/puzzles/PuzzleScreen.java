package com.gardensnake.cyberiq.puzzles;

import java.util.List;

import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Input.TouchEvent;

public abstract class PuzzleScreen {
	public enum PuzzleState {
		Start, A, B, C,D, Finish
	}

	public PuzzleState state;
	public Graphics graphics;
	public int difficulty;
	public int instanceBonus; //extra points for each turn
	public boolean success;
	public boolean instanceOver;
	public int instanceNumber;
	public int gameType; // 0:Verses, 1:freeplay
	public float resultTimer;// how long should result be displayed
	public float resultTimerLimit; //display limit

	public abstract void update(List<TouchEvent> touchEvents, float deltaTime);

	public abstract void present();

}
