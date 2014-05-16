package com.gardensnake.cyberiq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class Splash extends Activity {
	protected int splashTimeLimit = 2500;
	protected int splashRunningTime = 0;
	protected boolean touchedScreen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) { // onCreate
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);

		Thread splashTimer = new Thread() {
			public void run() {
				try {
					while (!touchedScreen && splashRunningTime < splashTimeLimit) {
						sleep(100);
						splashRunningTime += 100;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
					Intent startGame = new Intent(Splash.this, CyberIQ.class);
					startActivity(startGame);
				}
			}

		};
		splashTimer.start();

	}// onCreate

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			touchedScreen = true;
		}
		return false;

	}

}
