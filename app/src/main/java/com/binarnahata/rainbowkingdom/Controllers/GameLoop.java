package com.binarnahata.rainbowkingdom.Controllers;

import android.graphics.Canvas;
import android.os.AsyncTask;
import android.util.Log;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Views.BH_SurfaceView;

/**
 * RainbowKingdom
 * Created on 05.12.15, 9:17
 *
 * @author bat
 * @version 0.1
 */
public class GameLoop extends AsyncTask<Void, Void, Void> {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = GameLoop.class.getSimpleName();
	private final SurfaceHolder mSurfaceHolder;
	private final BH_SurfaceView mGame;
	private boolean mRunning;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setRunning(boolean running) {
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
	protected Void doInBackground(Void... params) {
		Log.d(TAG, "Run");
		long i = 0;
		/*while (i < 100) {
			++i;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Log.d(TAG, String.valueOf(i));
		}*/
		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		while (mRunning) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing on the surface
			try {
				canvas = this.mSurfaceHolder.lockCanvas();
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
		return null;
	}

	@Override
	protected void onCancelled() {
		Log.d(TAG, "onCancelled(Void) start");
		super.onCancelled();
		Log.d(TAG, "onCancelled(Void) finish");
	}
	/* МЕТОДЫ */
}
