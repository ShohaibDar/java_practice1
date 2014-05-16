package com.gardensnake.cyberiq.puzzles;

import java.util.Random;

import com.gardensnake.cyberiq.Assets;

public class GoalieField {
	Random rand = new Random();
	int difficulty;
	public Block block;
	public Puck puck;
	boolean deflected = false;
	public double slope;
	public int width;
	public int dx, dy;
	public float puckXPosition, puckYPosition;

	public GoalieField(int difficulty) {
		this.difficulty = difficulty;
		setField();
	}

	private void setField() {
		slope = 0;

		do {// UNIVERSALRESTRAINTS

			while (true) {// LEVEL RESTRAINTS

				dx = -250 + rand.nextInt(501);
				dy = -250 + rand.nextInt(470);
				slope = (double) dy / (double) dx;

				if (difficulty < 3 && Math.abs(slope) > 1.7 && Math.abs(slope) < 2.2 && dy > 70) {// 1-2
					width = (int) Math.round((((3.2 + (.15 * (1 - difficulty))) - Math.abs(slope)) * 40));
					break;
				}// 1:40-60 ... 2:34-54

				if (difficulty > 2 && difficulty < 7 && Math.abs(slope) > 1 && Math.abs(slope) < 1.67
						&& dy > 0) {
					width = (int) Math.round((((2.67 + (.10 * (4 - difficulty))) - Math.abs(slope)) * 30));
					break;
				}// 3: 33-53 ... 6:24-44

				if (difficulty > 6 && Math.abs(slope) > .75 && Math.abs(slope) < 1 && dy > 0) {
					width = (int) Math
							.round((((3.5 + (.10 * (8 - difficulty))) - (2.5 * Math.abs(slope))) * 30));
					break;
				}// 7:33-52 ... 10:24-43

				if (difficulty > 6 && Math.abs(slope) > 1.4 && Math.abs(slope) < 3.4 && dy < 0) {
					width = (int) Math.round((((2.67 + (.10 * (8 - difficulty))) - Math.abs(((.33 * Math
							.abs(slope)) + .5))) * 33));
					break;
				}// 7:38-60 ... 10:28-50

			}// LEVEL RESTRAINTS

		} while (Math.abs(dy) < 30 || (Math.abs(dx) + Math.abs(dy) < 100) || width < 24);// UNIVERSALRESTRAINTS

		puckXPosition = 120.0f + (rand.nextFloat() * 51);// 120-170
		if ((puckXPosition < 130 || puckXPosition > 160) && dy > 0)
			width++;// too hard, make block thicker
		puckYPosition = 65.0f + (rand.nextFloat() * 46);// 65-110
		if (puckYPosition < 80 && dy > 0)
			width++;// too hard, make block thicker
		if (dy > 100)
			width--;
		if (dy > 150)
			width--;
		if (dy > 175)
			width--;

		block = new Block(190, 340, width, 30);
		puck = new Puck(puckXPosition, puckYPosition, dx, dy);

	}

	public boolean checkCollision() {

		if (puck.y2 > block.y && puck.y2 < block.y2 && (puck.y + 20) < block.y) {// BLOCKED

			if (puck.x > block.x && puck.x < block.x2) {
				puck.dirY = -puck.dirY; // bounce back R->L
				Assets.taphardthud.play(1);
				return true;
			}
			if (puck.x2 > block.x && puck.x2 < block.x2) {
				puck.dirY = -puck.dirY;// bounce back L->R
				Assets.taphardthud.play(1);
				return true;
			}

		}// BLOCKED// front part of puck overlapping block

		if (puck.y2 > block.y && puck.y2 < block.y2 || puck.y > block.y && puck.y < block.y2) {

			if (puck.x < block.x2 && puck.x > block.x) {
				puck.dirX = -puck.dirX;// bounce RIGHT;
				deflected = true;
				Assets.tapsoftthud.play(1);
				return false;

			}// if puck overlaps right side of block

			if (puck.x2 < block.x2 && puck.x2 > block.x) {
				puck.dirX = -puck.dirX;// bounce LEFT;
				deflected = true;
				Assets.tapsoftthud.play(1);
				return false;

			}// if puck overlaps left side of block

		}// BOUNCED if part of puck overlapping block in Ypos

		return false;
	}

	public boolean checkGoal() {
		if (puck.y > 451) {
			return true;
		}
		return false;
	}
}
