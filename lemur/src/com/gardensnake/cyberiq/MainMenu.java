package com.gardensnake.cyberiq;

import java.util.List;
import java.util.Random;

import android.graphics.Color;

import com.gardensnake.framework.Game;
import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Input.TouchEvent;
import com.gardensnake.framework.Screen;

public class MainMenu extends Screen {
	Graphics graphics;
	String[] matrix = new String[80];
	int numberYPosition;
	float totalTime;
	float backgroundSoundTimer = 0; // change to 0
	Button verses, freeplay;
	Button soundOn, soundOff;
	boolean tHighLight;

	public MainMenu(Game game) {
		super(game);
		graphics = game.getGraphics();
		floatingNumbers();
		verses = new Button(graphics, 110, 210, 100, 50, 5, "VERSES", 1, 18, 160, 242, Color.BLACK,
				Color.YELLOW, Color.YELLOW, 3);
		freeplay = new Button(graphics, 180, 270, 100, 50, 5, "FREEPLAY", 1, 18, 230, 302, Color.BLACK,
				Color.RED, Color.RED, 3);
		soundOn = new Button(graphics, 0);
		soundOff = new Button(graphics, 1);
	}

	@Override
	public void update(float deltaTime) {
		numberYPosition++;
		totalTime += deltaTime;

		if (totalTime > backgroundSoundTimer) {
			playSong(true);
		}

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();

		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_UP) {// UP
				if (freeplay.click(event.x, event.y)) {
					game.setScreen(new FreePlayMenu(game));
					return;
				}

				if (event.x < 40 && event.y > 440) {// sound buttons
					Settings.soundEnabled = !Settings.soundEnabled;
					playSong(Settings.soundEnabled);
					return;
				}// sound buttons

				if (event.y < 20) {// TEST
					game.setScreen(new SoundTest(game));
				}// TEST

			}// UP
		}

	}

	@Override
	public void present(float deltaTime) {
		graphics.drawBackground(true, Color.BLACK);

		for (int i = 0; i < 80; i++) {
			graphics.drawText(matrix[i], 160, ((numberYPosition + (i * 6)) % 480), 1, 2, 6, Color.GREEN);
		}

		graphics.drawText("CYBER", 158, 93, 1, 2, 88, Color.GRAY);
		graphics.drawText("IQ", 158, 172, 1, 2, 94, Color.GRAY);
		graphics.drawText("CYBER", 160, 95, 1, 2, 88, Color.GREEN);
		graphics.drawText("IQ", 160, 174, 1, 2, 94, Color.GREEN);

		verses.draw();
		freeplay.draw();

		if (Settings.soundEnabled)
			soundOn.draw();
		else
			soundOff.draw();

	}

	@Override
	public void pause() {
		playSong(false);
		Settings.save(game.getFileIO());
	}

	@Override
	public void resume() {
		playSong(true);
	}

	@Override
	public void dispose() {
	}

	private void playSong(boolean play) {
		float lengthOfSong = 8;
		if (play) {
			Assets.background0.pause();
			Assets.background0.play(1);
			backgroundSoundTimer = totalTime + lengthOfSong;
		} else {
			Assets.background0.pause();
		}
	}

	private void floatingNumbers() {

		String alphabet = "0123456789";
		int randomNumber;
		Random rand = new Random();

		for (int row = 0; row < matrix.length; row++) {
			matrix[row] = new String();
			for (int col = 0; col < 160; col++) {
				randomNumber = rand.nextInt(100);
				if (col == 0 || col == 159 || randomNumber < 6)
					matrix[row] += alphabet.charAt(rand.nextInt(alphabet.length()));
				else
					matrix[row] += " ";
			}
		}

	}

}
