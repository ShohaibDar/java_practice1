package com.gardensnake.cyberiq.puzzles;

import android.graphics.Color;

public class PuzzleData {
	public int numberOfPuzzles = 9;
	public String[] titles = { "MATH", "CARD", "GOALIE", "SCALE", "BOMB", "GEM", "SEESAW", "PATTERN","ORDER" };
	public int[] colors = { Color.BLUE, Color.YELLOW, Color.rgb(153, 50, 205), Color.GREEN,
			Color.rgb(255, 127, 0), Color.rgb(34, 139, 34), Color.rgb(255, 110, 199), Color.rgb(32, 178, 170),Color.rgb(111, 0, 50) };
	public int[][] pointFactor = { { 2, 3, 0 }, { 0, 5, 10 }, { 4, 1, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
			{ 4, 1, 0 }, { 0, 0, 0 }, { 0, 0, 0 } ,{0,0,0}};

	public String[] line1 = { "Select the best fitting numbers to", "find pairs of identical cards in the",
			"Measure the puck's trajectory and", "", "", "Select the jar with the most gems", "", "" ,"", "", "", "", ""};
	public String[] line2 = { "solve the problem", "least amount of turns possible",
			"place block behind the goal line to", "", "", "", "", "", "","", "", "", "", "" };
	public String[] line3 = { "", "", "defend against the incoming shot", "", "", "", "", "","" };
}
