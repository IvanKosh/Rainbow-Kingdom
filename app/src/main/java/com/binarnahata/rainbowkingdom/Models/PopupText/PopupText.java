package com.binarnahata.rainbowkingdom.Models.PopupText;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * RainbowKingdom
 * Created on 07.01.16, 17:15
 *
 * @author bat
 * @version 0.1
 */
public class PopupText {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PopupText.class.getSimpleName();
	private static final int MAX_LIVE_TIME = 60;
	private final Paint mPaint;

	private String mText;
	private Point mPosition;
	private int mLiveTime;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public PopupText(String text, Point bias, Paint paint) {
		mText = text;
		mPosition = bias;
		mPaint = paint;
		mLiveTime = MAX_LIVE_TIME;
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public boolean isAlive() {
		mLiveTime--;
		if (mLiveTime > 0) {
			return true;
		}
		return false;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {
		canvas.drawText(mText, mPosition.x, mPosition.y, mPaint);
	}

	public PopupText copy() {
		return new PopupText(mText, new Point(mPosition), mPaint);
	}

	public void offset(int x, int y) {
		mPosition.offset(x, y);
	}

	public void die() {
		mLiveTime = 0;
	}
	/* МЕТОДЫ */
}