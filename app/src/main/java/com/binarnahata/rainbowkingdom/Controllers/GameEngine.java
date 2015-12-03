package com.binarnahata.rainbowkingdom.Controllers;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * RainbowKingdom
 * Created on 03.12.15, 11:30
 *
 * @author bat
 * @version 0.1
 */
public class GameEngine extends SurfaceView implements SurfaceHolder.Callback  {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public GameEngine(Context context) {
		super(context);
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}
	/* МЕТОДЫ */
}