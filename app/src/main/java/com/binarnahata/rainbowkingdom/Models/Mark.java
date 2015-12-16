package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * RainbowKingdom
 * Created on 16.12.15, 16:10
 *
 * @author bat
 * @version 0.1
 */
public class Mark {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Mark.class.getSimpleName();
	public static final int LIVE_TIME = 180;
	private final Paint mPaint;
	private int mX;
	private int mY;
	private int mLive;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Mark(Paint paint) {
		mPaint = paint;
	}

	public void setCoordinate(int x, int y) {
		mX = x;
		mY = y;
		mLive = LIVE_TIME;
	}

	public void update() {
		if (mLive > 0) {
			mLive--;
		}
	}

	public void draw(Canvas canvas) {
		if (mLive > 0) {
			canvas.drawCircle(mX, mY, 3, mPaint);
		}
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
