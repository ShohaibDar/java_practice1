package com.gardensnake.cyberiq.puzzles;

import java.util.Random;

import com.gardensnake.cyberiq.Assets;

public class CardField {
	public int totalTurns;
	public int numberOfCards;
	public int totalFlipped;
	public Card[] cards;
	int difficulty;
	Random rand = new Random();

	public CardField(int difficulty) {
		setDifficulty(difficulty);
		setDeck();
		setLocation();
	}

	private void setDifficulty(int difficulty) {
		numberOfCards = 8 + ((difficulty / 3) * 4); // 8,8,12,12,12,16,16,16,20,20
		totalTurns = ((numberOfCards / 4) * 3) + ((4 - (difficulty % 3))); // 9,8,13,12,11,16,15,14,19,18

		if (difficulty == 10)
			totalTurns = 17; // Extra Hard

	}

	private void setDeck() {

		cards = new Card[numberOfCards];

		for (int i = 0; i < cards.length; i++) {
			cards[i] = new Card(i / 2);
		}// 00,11,...,NN

		int holder1, holder2, holder3;

		for (int i = 0; i < cards.length * 10; i++) { // shuffle
			holder1 = rand.nextInt(cards.length);
			holder2 = rand.nextInt(cards.length);

			holder3 = cards[holder1].value;
			cards[holder1].value = cards[holder2].value;
			cards[holder2].value = holder3;

		}// shuffle

	}

	private void setLocation() {

		int numberOfRows = numberOfCards / 4;

		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < 4; j++) {
				cards[j + (i * 4)].x = 45 + (j * 60);
				cards[j + (i * 4)].y = 80 + (i * 78);
			}
		}

	}

	public void reset() {
		for (int i = 0; i < numberOfCards; i++) {
			cards[i].flipped = false;
		}
	}

	public void flip(int x, int y) {

		for (int i = 0; i < numberOfCards; i++)
			if (x > cards[i].x && x < (cards[i].x + 50) && y > cards[i].y && y < (cards[i].y + 70)) {
				if (cards[i].flipped == false && cards[i].matched == false) {
					cards[i].flipped = true;
					totalFlipped++;
					Assets.tapmouse.play(1);
					return;
				}
			}
	}

	public boolean isMatch() {
		int holder1 = 0;
		int holder2 = 0;
		boolean flag = false;

		for (int i = 0; i < numberOfCards; i++) {
			if (cards[i].flipped) {
				if (flag == false) {
					holder1 = i;
					flag = true;
				} else {
					holder2 = i;
				}
			}
		}

		if (cards[holder1].value == cards[holder2].value) {
			cards[holder1].matched = true;
			cards[holder2].matched = true;
			return true;
		}// Match found, return true;

		return false;

	}

	public boolean isComplete() { // returns true if entire deck matched
		int temp = 0;
		for (int i = 0; i < numberOfCards; i++) {
			if (cards[i].matched == true) {
				temp++;
			}
		}
		if (temp == numberOfCards) {
			return true;
		}
		return false;
	}

}
