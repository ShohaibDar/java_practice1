package com.gardensnake.cyberiq.puzzles;

import java.util.Random;

public class GemField {

	public final int totalGemLocations = 12 * 7;
	public final int totalJars = 4;
	public Jar[] jars = new Jar[totalJars];
	int totalGemsWinnerJar;
	int minRange;
	int maxRange;
	int winnerJar;
	Random rand = new Random();

	public GemField(int difficulty) {
		setDifficulty(difficulty);
		setJars();
	}

	private void setDifficulty(int difficulty) {
		// 15,19,22,26,29,33,36,40,43,47 + {0,1,2,3};
		totalGemsWinnerJar = (int) (difficulty * 3.5) + 12 + rand.nextInt(4);
		maxRange = 7 - ((difficulty + 1) / 2);// 6,6,5,5,4,4,3,3,2,2
		minRange = 4 - ((difficulty + 1) / 3);// 4,3,3,3,2,2,2,1,1,1

	}

	private void setJars() {
		winnerJar = rand.nextInt(totalJars);

		for (int i = 0; i < totalJars; i++) { // SET totals for each Jar
			jars[i] = new Jar(totalGemLocations);
			if (i == winnerJar) {
				jars[i].numberOfGems = totalGemsWinnerJar;
			} else {
				jars[i].numberOfGems = totalGemsWinnerJar
						- (rand.nextInt(maxRange - minRange + 1) + minRange);
			}
		}// SET totals for each Jar

		int index;

		for (int i = 0; i < 4; i++) {// PLACE GEMS IN JAR
			while (true) {
				index = rand.nextInt(totalGemLocations);
				if (jars[i].gemPosition[index] == false) {
					jars[i].gemPosition[index] = true;
					jars[i].gemsPlaced++;
					if (jars[i].gemsPlaced == jars[i].numberOfGems)
						break;
				}

			}
		}// PLACE GEMS IN JAR
	}

	public boolean ifCorrect(int chosenJar) {
		if (winnerJar == chosenJar)
			return true;
		else
			return false;
	}

}
