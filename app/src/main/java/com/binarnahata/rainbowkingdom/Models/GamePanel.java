package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

/**
 * RainbowKingdom
 * Created on 13.12.15, 21:33
 *
 * @author bat
 * @version 0.1
 */
public class GamePanel {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = GamePanel.class.getSimpleName();

	private final Bitmap mFon;
	private final Bitmap mForLeft;
	private final Bitmap mForRight;

	private Rect mLeft;
	private Rect mRight;

	private Rect mRectForLeft;
	private Rect mRectForRight;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GamePanel(Rect dstRect, Bitmap fon, Bitmap forLeft, Bitmap forRight) {
		mFon = fon;
		mForLeft = forLeft;
		mForRight = forRight;

		int size = dstRect.height() >> 1;
		Rect centerRect = new Rect(dstRect.centerX() - size, dstRect.centerY() - size,
			dstRect.centerX() + size, dstRect.centerY() + size);

		mLeft = new Rect(dstRect.left, dstRect.top, centerRect.left, dstRect.bottom);
		mRight = new Rect(centerRect.right, dstRect.top, dstRect.right, dstRect.bottom);

		mRectForLeft = new Rect(centerRect.left, centerRect.top, centerRect.left, centerRect.right + forLeft.getWidth());
		mRectForRight = new Rect(centerRect.right - forRight.getWidth(), centerRect.top, centerRect.right-forRight.getWidth(), centerRect.bottom);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mFon, null, mLeft, null);
		//canvas.drawBitmap(mGun, null, new Rect(/*dstRect.centerX()*/0, dstRect.centerY(), mGun.getWidth(), mGun.getHeight()), null);
		canvas.drawBitmap(mForLeft, null, mRectForLeft, null);
		canvas.drawBitmap(mForRight, null, mRectForRight, null);
		canvas.drawBitmap(mFon, null, mRight, null);

	}
	/* МЕТОДЫ */
}