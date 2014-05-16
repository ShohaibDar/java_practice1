package com.gardensnake.cyberiq;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.gardensnake.cyberiq.puzzles.PuzzleData;
import com.gardensnake.framework.FileIO;

public class Settings {
	public static boolean soundEnabled = true;
	public static String name = "Human";
	public static int age = 0;
	public static boolean isMale = true;
	public static int levelReached = 1;
	public static int cyberScore;
	public static int iqRating;
	public static int globalRank = 0;
	public static int[][] freeplayScores = new int[9][5];
	public static int[] versesScores = new int[8];

	public static void load(FileIO files) {// LOAD
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(files.readFile(".cyberiq")));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			name = in.readLine();
			age = Integer.parseInt(in.readLine());
			isMale = Boolean.parseBoolean(in.readLine());

			levelReached = Integer.parseInt(in.readLine());
			cyberScore = Integer.parseInt(in.readLine());
			iqRating = Integer.parseInt(in.readLine());
			globalRank = Integer.parseInt(in.readLine());

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 5; j++) {
					freeplayScores[i][j] = Integer.parseInt(in.readLine());
				}
			}

			for (int i = 0; i < 8; i++) {
				versesScores[i] = Integer.parseInt(in.readLine());
			}

		} catch (IOException e) {

		} catch (NumberFormatException e) {

		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {

			}
		}
	}// LOAD

	public static void save(FileIO files) {// SAVE
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".cyberiq")));
			out.write(Boolean.toString(soundEnabled));
			out.write("\n");
			out.write(name);
			out.write("\n");
			out.write(Integer.toString(age));
			out.write("\n");
			out.write(Boolean.toString(isMale));
			out.write("\n");
			out.write(Integer.toString(levelReached));
			out.write("\n");

			out.write(Integer.toString(cyberScore));
			out.write("\n");
			out.write(Integer.toString(iqRating));
			out.write("\n");
			out.write(Integer.toString(globalRank));
			out.write("\n");

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 5; j++) {
					out.write(Integer.toString(freeplayScores[i][j]));
					out.write("\n");
				}
			}

			for (int i = 0; i < 8; i++) {
				out.write(Integer.toString(versesScores[i]));
				out.write("\n");
			}

		} catch (IOException e) {
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {

			}

		}
	}// SAVE

	public static void addFreePlayScore(int gameNumber, int score) {
		for (int i = 0; i < 5; i++) {
			if (freeplayScores[gameNumber][i] < score) {
				for (int j = 4; j > i; j--)
					freeplayScores[gameNumber][j] = freeplayScores[gameNumber][j - 1];
				freeplayScores[gameNumber][i] = score;
				break;
			}
		}

		updateFreePlayScores();
		updateVersesScores();
	}

	public static void addVersesScore(int roundNumber, int score) {
		if (versesScores[roundNumber] < score) {
			versesScores[roundNumber] = score;
		}

		updateFreePlayScores();
		updateVersesScores();
	}

	public static void updateFreePlayScores() {

	}

	public static void updateVersesScores() {

	}

}
