package com.gardensnake.cyberiq.puzzles;

import java.util.Random;

import com.gardensnake.framework.Graphics;

public class OrderField {
	Random rand = new Random();
	public int numOfBlocks;
	public OrderBlock[] blocks;
	Graphics graphics;
	int number, firstFrac, secondFrac;

	public OrderField(Graphics graphics, int difficulty) {

		this.graphics = graphics;

		setDifficulty();
		setBlocks();
	}

	private void setDifficulty() {
		numOfBlocks = 9;
	}

	private void setBlocks() {
		blocks = new OrderBlock[numOfBlocks];

		for (int i = 0; i < numOfBlocks; i++) {
			number = rand.nextInt(3) + 1;
			if (rand.nextBoolean()) {
				blocks[i] = new OrderBlock(graphics, 30 + (95 * (i % 3)), 100 + (70 * (i / 3)), number);
			} else {

				firstFrac = rand.nextInt(8) + 1;
				do {
					secondFrac = rand.nextInt(8) + 2;
				} while (firstFrac >= secondFrac);

				blocks[i] = new OrderBlock(graphics, 30 + (95 * (i % 3)), 100 + (70 * (i / 3)), number,
						firstFrac, secondFrac);

			}
		}
	}

}
