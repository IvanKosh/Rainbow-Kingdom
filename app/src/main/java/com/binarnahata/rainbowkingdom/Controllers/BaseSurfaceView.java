package com.binarnahata.rainbowkingdom.Controllers;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * RainbowKingdom
 * Created on 04.12.15, 12:28
 *
 * @author bat
 * @version 0.1
 */
public abstract class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback  {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	public String avgFps;
	public Context mContext;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setAvgFps(String avgFps) {
		this.avgFps = avgFps;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BaseSurfaceView(Context context) {
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
	public abstract void displayFps(Canvas canvas, String fps);
	/* МЕТОДЫ */
}
