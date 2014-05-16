package com.gardensnake.cyberiq.puzzles;

import java.util.Random;

public class SeesawBoard {
	public SeesawBlock[] blocks;
	Random rand = new Random();
	int position, weight;
	int weightLimit; // maximum weight of block *10
	int totalBoardValue, leftBoardTotalValue, rightBoardTotalValue;
	public int[] boardPositionTotalValue = new int[6];
	public int correctSide;// 0:left, 1:right
	int maximumBoardValue;
	int[] boardPositionCounter = new int[6];
	boolean maxHeightReached;
	int maxHeightLimit = 5;

	public SeesawBoard(int difficulty) {
		setDifficulty(difficulty);
		setBlocks();
		setPosition();
		addTotal();

	}

	private void setDifficulty(int difficulty) {
		blocks = new SeesawBlock[8 + difficulty];// 9-18
		weightLimit = 1 + (9 * difficulty);// 10-91 >> 1-10
		maximumBoardValue = 6 - ((difficulty + 1) / 2);
	}

	private void setBlocks() {// setBlocks()
		do {
			// RESET Board counters
			totalBoardValue = 0;
			maxHeightReached = false;
			for (int i = 0; i < boardPositionCounter.length; i++)
				boardPositionCounter[i] = 0;

			// SET position & weight; get totalBoardValue
			for (int i = 0; i < blocks.length; i++) {
				position = rand.nextInt(6);
				weight = rand.nextInt(weightLimit);
				blocks[i] = new SeesawBlock(position, weight);
				totalBoardValue += blocks[i].value;
			}

			// CHECK height is under limit
			for (int i = 0; i < blocks.length; i++) {
				boardPositionCounter[blocks[i].boardXOrder]++;
				if (boardPositionCounter[blocks[i].boardXOrder] > maxHeightLimit)
					maxHeightReached = true;
			}

		} while (totalBoardValue == 0 || (Math.abs(totalBoardValue) > maximumBoardValue) || maxHeightReached);

	}// setBlocks()

	private void setPosition() {// setPosition()

		// RESET
		for (int i = 0; i < boardPositionCounter.length; i++)
			boardPositionCounter[i] = 0;

		// SET yCoordinate
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].setHeight(boardPositionCounter[blocks[i].boardXOrder] * -30);
			boardPositionCounter[blocks[i].boardXOrder]++;
		}
	}// setPosition()

	private void addTotal() {// addTotal()
		// COUNT total value on each side
		for (int i = 0; i < blocks.length; i++) {

			if (blocks[i].boardXOrder < 3) {
				leftBoardTotalValue += (blocks[i].value * -1);
				boardPositionTotalValue[blocks[i].boardXOrder] += (blocks[i].value * -1);
			} else {
				rightBoardTotalValue += (blocks[i].value);
				boardPositionTotalValue[blocks[i].boardXOrder] += (blocks[i].value);
			}
		}
		correctSide = (leftBoardTotalValue > rightBoardTotalValue) ? 0 : 1;

	}// addTotal()

	public boolean isCorrect() {
		return false;
	}

}
