package com.gardensnake.framework.impl;

import android.media.SoundPool;

import com.gardensnake.cyberiq.Settings;
import com.gardensnake.framework.Sound;

public class AndroidSound implements Sound {
	int soundId;
	SoundPool soundPool;
	int streamId;

	public AndroidSound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	public void play(float volume) {
		if (!Settings.soundEnabled)
			return;
		streamId = soundPool.play(soundId, volume, volume, 0, 0, 1);
	}

	public void dispose() {
		soundPool.unload(soundId);
	}

	public void pause() {
		soundPool.stop(streamId);
	}

}
