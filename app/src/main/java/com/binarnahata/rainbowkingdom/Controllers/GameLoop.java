package com.binarnahata.rainbowkingdom.Controllers;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Views.BH_SurfaceView;

/**
 * RainbowKingdom
 * Created on 06.12.15, 13:58
 *
 * @author bat
 * @version 0.1
 */
public class GameLoop extends Thread {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = GameLoop.class.getSimpleName();
	private final BH_SurfaceView mGame;
	private Boolean mRunning;
	private SurfaceHolder mSurfaceHolder;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setRunning(Boolean running) {
		mRunning = running;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */

	public GameLoop(SurfaceHolder surfaceHolder, BH_SurfaceView game) {
		mSurfaceHolder = surfaceHolder;
		mGame = game;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void run() {
		Log.d(TAG, "run");
		/*int i = 0;
		while(mRunning) {
			Log.d(TAG, String.valueOf(++i));
		}*/

		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		while (mRunning) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing on the surface
			try {
				canvas = mSurfaceHolder.lockCanvas();
				synchronized (mSurfaceHolder) {
					// update game state
					mGame.update();
					// render state to the screen
					// draws the canvas on the panel
					mGame.render(canvas);
				}
			} finally {
				// in case of an exception the surface is not left in
				// an inconsistent state
				if (canvas != null) {
					mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
			} // end finally
		}
	}
	/* МЕТОДЫ */
}
