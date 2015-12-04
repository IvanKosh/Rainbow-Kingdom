package com.binarnahata.rainbowkingdom.Controllers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * RainbowKingdom
 * Created on 04.12.15, 12:21
 *
 * @author bat
 * @version 0.1
 */
public class FPS extends BaseSurfaceView {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private final MainThread mThread;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public FPS(Context context) {
		super(context);

		// create the game loop mThread
		mThread = new MainThread(getHolder(), this);
	}

	@Override
	public void update() {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.setRunning(true);
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}


	public void render(Canvas canvas) {
		//canvas.drawColor(Color.BLACK);
		//mDroid.draw(canvas);

		// display fps
		displayFps(canvas, avgFps);
	}

	public void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps, this.getWidth() - 50, 20, paint);
		}
	}
	/* МЕТОДЫ */
}
