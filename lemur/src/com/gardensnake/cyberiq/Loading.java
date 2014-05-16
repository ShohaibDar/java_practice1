package com.gardensnake.cyberiq;

import com.gardensnake.framework.Game;
import com.gardensnake.framework.Graphics;
import com.gardensnake.framework.Graphics.PixmapFormat;
import com.gardensnake.framework.Screen;

public class Loading extends Screen {

	public Loading(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();

		Assets.buttons = g.newPixmap("buttons.png", PixmapFormat.RGB565);
		Assets.puzzleitems = g.newPixmap("puzzleitems.png", PixmapFormat.RGB565);

		Assets.button0 = game.getAudio().newSound("button0.ogg"); // clink
		Assets.button1 = game.getAudio().newSound("button1.ogg"); // boop1
		Assets.button2 = game.getAudio().newSound("button2.ogg"); // boop2
		Assets.button3 = game.getAudio().newSound("button3.ogg"); // bleep
		Assets.button4 = game.getAudio().newSound("button4.ogg"); // rubber

		Assets.successclassic = game.getAudio().newSound("successclassic.ogg");
		Assets.successcomplete = game.getAudio().newSound("successcomplete.ogg");
		Assets.successlevelup = game.getAudio().newSound("successlevelup.ogg");
		Assets.successlightning = game.getAudio().newSound("successlightning.ogg");
		Assets.successpoint = game.getAudio().newSound("successpoint.ogg");
		Assets.successpoint2 = game.getAudio().newSound("successpoint2.ogg");

		Assets.failclassic = game.getAudio().newSound("failclassic.ogg");
		Assets.failclassicdull = game.getAudio().newSound("failclassicdull.ogg");
		Assets.failjeopardy = game.getAudio().newSound("failjeopardy.ogg");
		Assets.failwindows = game.getAudio().newSound("failwindows.ogg");

		Assets.tapclassic = game.getAudio().newSound("tapclassic.ogg");
		Assets.tapcoin = game.getAudio().newSound("tapcoin.ogg");
		Assets.tapmouse = game.getAudio().newSound("tapmouse.ogg");
		Assets.tapswipe = game.getAudio().newSound("tapswipe.ogg");
		Assets.tapglass = game.getAudio().newSound("tapglass.ogg");
		Assets.tapboop = game.getAudio().newSound("tapboop.ogg");
		Assets.tapsoftthud = game.getAudio().newSound("tapsoftthud.ogg");
		Assets.taphardthud = game.getAudio().newSound("taphardthud.ogg");

		Assets.type = game.getAudio().newSound("type.ogg");
		Assets.keyboard = game.getAudio().newSound("keyboard.ogg");
		Assets.numberscroll = game.getAudio().newSound("numberscroll.ogg");
		Assets.phase = game.getAudio().newSound("phase.ogg");

		Assets.background0 = game.getAudio().newSound("background0.ogg");

		Settings.load(game.getFileIO());
		game.setScreen(new MainMenu(game));
	}

	@Override
	public void present(float deltaTime) {
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

}
