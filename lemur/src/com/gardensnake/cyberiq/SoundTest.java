package com.gardensnake.cyberiq;

import java.util.List;

import android.graphics.Color;

import com.gardensnake.framework.Game;
import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Screen;
import com.gardensnake.framework.Input.TouchEvent;

public class SoundTest extends Screen {
	Graphics graphics;

	public String[][] names = { { "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7" },
			{ "clsic", "boop", "coin", "mouse", "swipe", "glass", "sthud", "hthud" },
			{ "sclsc", "scmplt", "slevup", "slght", "spnt", "spnt2", "b6", "b7" },
			{ "fclsc", "fcldl", "fjep", "fwdw", "b4", "b5", "b6", "b7" },
			{ "type", "kbrd", "nscll", "phase", "b4", "b5", "b6", "b7" },
			{ "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7" },
			{ "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7" },
			{ "b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7" } };
	public String[] scores = new String[8];

	public SoundTest(Game game) {
		super(game);
		graphics = game.getGraphics();
		load();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				playSound((event.x / 40), (event.y / 40));
			}
		}

	}

	@Override
	public void present(float deltaTime) {
		graphics.drawBackground(true, Color.BLACK);

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				graphics.drawText(names[col][row], 20 + (row * 40), 20 + (col * 40), 1, 0, 11, Color.WHITE);
			}
			graphics.drawLine(0, 40 + (40 * row), 320, 40 + (40 * row), 2, Color.WHITE);
			graphics.drawLine(40 + (40 * row), 0, 40 + (40 * row), 320, 2, Color.WHITE);
		}

		for (int i = 0; i < 8; i++) {
			graphics.drawText(scores[i], 10, 340 + (15 * i), 0, 1, 12, Color.WHITE);
		}

	}

	public void load() {
		for (int i = 0; i < 8; i++) {
			scores[i] = "Puzzle" + (i + 1) + ": ";
			for (int j = 0; j < 5; j++) {
				scores[i] += Settings.freeplayScores[i][j] + " ";
			}

		}
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

	public void playSound(int row, int col) {
		int index = row + (col * 8);
		if (index == 0)
			Assets.button0.play(1);
		if (index == 1)
			Assets.button1.play(1);
		if (index == 2)
			Assets.button2.play(1);
		if (index == 3)
			Assets.button3.play(1);
		if (index == 4)
			Assets.button4.play(1);
		// if(index==5)
		// Assets.button5.play(1);
		// if(index==6)
		// Assets.button0.play(1);
		// if(index==7)
		// Assets.button0.play(1);

		if (index == 8)
			Assets.tapclassic.play(1);
		if (index == 9)
			Assets.tapboop.play(1);
		if (index == 10)
			Assets.tapcoin.play(1);
		if (index == 11)
			Assets.tapmouse.play(1);
		if (index == 12)
			Assets.tapswipe.play(1);
		if (index == 13)
			Assets.tapglass.play(1);
		if (index == 14)
			Assets.tapsoftthud.play(1);
		if (index == 15)
			Assets.taphardthud.play(1);

		if (index == 16)
			Assets.successclassic.play(1);
		if (index == 17)
			Assets.successcomplete.play(1);
		if (index == 18)
			Assets.successlevelup.play(1);
		if (index == 19)
			Assets.successlightning.play(1);
		if (index == 20)
			Assets.successpoint.play(1);
		if (index == 21)
			Assets.successpoint2.play(1);
		// if (index == 14)
		// Assets.tapsoftthud.play(1);
		// if (index == 15)
		// Assets.taphardthud.play(1);

		if (index == 24)
			Assets.failclassic.play(1);
		if (index == 25)
			Assets.failclassicdull.play(1);
		if (index == 26)
			Assets.failjeopardy.play(1);
		if (index == 27)
			Assets.failwindows.play(1);
		/*
		 * if (index == 12) Assets.tapswipe.play(1); if (index == 13)
		 * Assets.tapglass.play(1); if (index == 14) Assets.tapsoftthud.play(1);
		 * if (index == 15) Assets.taphardthud.play(1);
		 */

		if (index == 32)
			Assets.type.play(1);
		if (index == 33)
			Assets.keyboard.play(1);
		if (index == 34)
			Assets.numberscroll.play(1);
		if (index == 35)
			Assets.phase.play(1);
		/*
		 * if (index == 36) Assets.tapswipe.play(1); if (index == 37)
		 * Assets.tapglass.play(1); if (index == 38) Assets.tapsoftthud.play(1);
		 * if (index == 39) Assets.taphardthud.play(1);
		 */

		if (index > 85)
			game.setScreen(new MainMenu(game));

	}

}
