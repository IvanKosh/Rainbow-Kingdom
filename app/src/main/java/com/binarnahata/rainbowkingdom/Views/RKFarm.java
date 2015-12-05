package com.binarnahata.rainbowkingdom.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;

/**
 * RainbowKingdom
 * Created on 05.12.15, 17:23
 *
 * @author bat
 * @version 0.1
 */
public class RKFarm extends BH_SurfaceView {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = RKFarm.class.getSimpleName();
	private GameLoop mGameLoop;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public RKFarm(Context context) {
		super(context);
		Log.d(TAG, "Create");
		mGameLoop = new GameLoop(getHolder(), this);
		mGameLoop.setRunning(true);
		mGameLoop.execute();
		Log.d(TAG, "runner");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "Created");
		//mGameLoop.execute();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Destroyed");
		mGameLoop.cancel(true);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void update() {

	}

	@Override
	public void render(Canvas canvas) {

	}
	/* МЕТОДЫ */
}
