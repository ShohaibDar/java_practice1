package com.gardensnake.cyberiq.puzzles;

import java.util.ArrayList;
import java.util.Random;

public class MathField {
	public ArrayList<Operand> playerSelection = new ArrayList<Operand>();
	int[] correctSelection; // pick
	int TOTALOPERANDS;
	int maxOperand, minOperand;
	static final int ADD = 0, MULT = 1, SUB = 2;
	int minTotal, maxTotal;
	int playerAnswer, correctAnswer;
	int difficulty;
	public Operand[] choice = new Operand[8];
	Operator operator1, operator2;
	public String expression;
	public String correctingMistake;
	Random rand = new Random();

	public MathField(int difficulty) {
		this.difficulty = difficulty;
		setDifficulty();
		selectProblem();
	}

	private void setDifficulty() {
		TOTALOPERANDS = 2 + (difficulty / 6);// 2,2,2,2,2,3,3,3,3,3
		minOperand = 1; // choice>=1
		maxOperand = 10; // choice<=10
		minTotal = 1; // answer>=1
		maxTotal = 999; // answer<=999

		switch (difficulty) {
		case 1:
			operator1 = new Operator(ADD);
			maxOperand = 7;
			break;
		case 2:
			operator1 = new Operator(ADD);
			minTotal = 7;
			minOperand = 2;
			maxOperand = 12;
			break;
		case 3:
			operator1 = new Operator(SUB);
			minTotal = 3;
			minOperand = 3;
			maxOperand = 15;
			break;
		case 4:
			operator1 = new Operator(ADD);
			minTotal = 13;
			minOperand = 5;
			maxOperand = 22;
			break;
		case 5:
			operator1 = new Operator(MULT);
			maxTotal = 59;
			minOperand=2;
			maxOperand = 9;
			break;
		case 6:
			operator1 = new Operator(SUB);
			operator2 = new Operator(ADD);
			minOperand = 5;
			maxOperand = 22;
			break;
		case 7:
			operator1 = new Operator(MULT);
			operator2 = new Operator(ADD);
			minTotal = 30;
			maxTotal = 120;
			minOperand = 2;
			maxOperand = 9;
			break;
		case 8:
			operator1 = new Operator(MULT);
			operator2 = new Operator(ADD);
			minTotal = 50;
			maxTotal = 200;
			minOperand = 3;
			maxOperand = 15;

			break;
		case 9:
			operator1 = new Operator(MULT);
			operator2 = new Operator(MULT);
			minTotal = 50;
			maxTotal = 200;
			minOperand = 2;
			maxOperand = 12;

			break;
		case 10:
			operator1 = new Operator(MULT);
			operator2 = new Operator(MULT);
			minTotal = 101;
			minOperand = 5;
			maxOperand = 17;
			break;
		}

	}

	private void selectProblem() {// SELECT PROBLEM

		do {

			for (int i = 0; i < choice.length; i++) {
				choice[i] = new Operand(rand.nextInt(maxOperand - minOperand + 1) + minOperand);
			}

			correctSelection = new int[TOTALOPERANDS];
			correctSelection[0] = rand.nextInt(8);
			do {
				correctSelection[1] = rand.nextInt(8);
			} while (correctSelection[0] == correctSelection[1]);
			if (TOTALOPERANDS == 3) {
				do {
					correctSelection[2] = rand.nextInt(8);
				} while (correctSelection[0] == correctSelection[2]
						|| correctSelection[1] == correctSelection[2]);
			}

			int sum = 0;

			switch (operator1.value) {// OPERATOR1
			case ADD:
				sum = choice[correctSelection[0]].value + choice[correctSelection[1]].value;
				break;
			case MULT:
				sum = choice[correctSelection[0]].value * choice[correctSelection[1]].value;
				break;
			case SUB:
				sum = choice[correctSelection[0]].value - choice[correctSelection[1]].value;
				break;
			}// OPERATOR1

			if (TOTALOPERANDS == 3) {

				switch (operator2.value) {// OPERATOR2
				case ADD:
					correctAnswer = sum + choice[correctSelection[2]].value;
					break;
				case MULT:
					correctAnswer = sum * choice[correctSelection[2]].value;
					break;
				case SUB:
					correctAnswer = sum - choice[correctSelection[2]].value;
					break;
				}// OPERATOR2
				correctingMistake = choice[correctSelection[0]].value + " " + operator1.chr + " "
						+ choice[correctSelection[1]].value + " " + operator2.chr + " "
						+ choice[correctSelection[2]].value + " = " + correctAnswer;
			} else {// NO 2nd operator
				correctAnswer = sum;
				correctingMistake = choice[correctSelection[0]].value + " " + operator1.chr + " "
						+ choice[correctSelection[1]].value + " = " + correctAnswer;
			}// No 2nd operator

		} while (correctAnswer < minTotal || correctAnswer > maxTotal);

	}// SELECT PROBLEM

	public void selected(int boxNum) { // PICK BOX
		if (playerSelection.size() == TOTALOPERANDS)
			return;

		if (choice[boxNum].on == false) {
			choice[boxNum].on = true;
			playerSelection.add(choice[boxNum]);
		} else {
			choice[boxNum].on = false;
			playerSelection.remove(choice[boxNum]);
		}

	}// PICK BOX

	/**
	 * @return true: player is right
	 */
	public boolean checkAnswer() {

		int sum = 0;

		switch (operator1.value) {
		case ADD:
			sum = playerSelection.get(0).value + playerSelection.get(1).value;
			break;
		case MULT:
			sum = playerSelection.get(0).value * playerSelection.get(1).value;
			break;
		case SUB:
			sum = playerSelection.get(0).value - playerSelection.get(1).value;
			break;
		}
		if (TOTALOPERANDS == 3) {
			switch (operator2.value) {
			case ADD:
				playerAnswer = sum + playerSelection.get(2).value;
				break;
			case MULT:
				playerAnswer = sum * playerSelection.get(2).value;
				break;
			case SUB:
				playerAnswer = sum - playerSelection.get(2).value;
				break;
			}
		} else {
			playerAnswer = sum;
		}

		if (correctAnswer == playerAnswer) {
			return true;
		} else {
			return false;
		}
	}

	public String expression() {
		expression = "";

		if (playerSelection.size() > 0)
			expression += playerSelection.get(0).value;
		else {
			expression += "?";
		}

		expression += " " + operator1.chr + " ";

		if (playerSelection.size() > 1)
			expression += playerSelection.get(1).value;
		else {
			expression += "?";
		}

		if (TOTALOPERANDS == 3) {
			expression += " " + operator2.chr + " ";

			if (playerSelection.size() > 2)
				expression += playerSelection.get(2).value;
			else {
				expression += "?";
			}
		}

		expression += " = " + correctAnswer;

		return expression;

	}

}
