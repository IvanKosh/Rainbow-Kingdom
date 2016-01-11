package com.binarnahata.rainbowkingdom.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * RainbowKingdom
 * Created on 05.12.15, 17:25
 *
 * @author bat
 * @version 0.1
 */
public abstract class BH_SurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private String mAvgFps;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BH_SurfaceView(Context context) {
		super(context);
		// adding the callback (this) to the surface holder to intercept events
		getHolder().addCallback(this);

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public abstract void update();

	public abstract void render(Canvas canvas);

	public void setAvgFps(String avgFps) {
		mAvgFps = avgFps;
	}
	/* МЕТОДЫ */
}