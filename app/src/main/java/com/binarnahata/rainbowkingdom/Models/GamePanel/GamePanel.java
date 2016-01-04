package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Models.Components.Color;

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
	public static final String TEXT_BALLS_N = "Balls: ";

	private final Bitmap mFon;
	private final Bitmap mForLeft;
	private final Bitmap mForRight;
	private final Paint mPaint;
	private final int mTextSize;

	public Rect mRectLeft;
	public Rect mRectRight;

	private Rect mRectForLeft;
	private Rect mRectForRight;

	private int mAvailableBalls;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void incrementAvailableBalls() {
		mAvailableBalls++;
	}
	public void decrementAvailableBalls() {
		mAvailableBalls--;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GamePanel(Rect dstRect, Bitmap fon, Bitmap forLeft, Bitmap forRight, int availableBalls) {
		mFon = fon;
		mForLeft = forLeft;
		mForRight = forRight;
		mAvailableBalls = availableBalls;
		mTextSize = dstRect.height() >> 1;

		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setTextAlign(Paint.Align.CENTER);
		mPaint.setTextSize(mTextSize);

		Rect centerRect = new Rect(dstRect.centerX() - mTextSize, dstRect.centerY() - mTextSize,
			dstRect.centerX() + mTextSize, dstRect.centerY() + mTextSize);

		mRectLeft = new Rect(dstRect.left, dstRect.top, centerRect.left, dstRect.bottom);
		mRectRight = new Rect(centerRect.right, dstRect.top, dstRect.right, dstRect.bottom);

		mRectForLeft = new Rect(centerRect.left, centerRect.top, centerRect.left, centerRect.right + forLeft.getWidth());
		mRectForRight = new Rect(centerRect.right - forRight.getWidth(), centerRect.top, centerRect.right-forRight.getWidth(), centerRect.bottom);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mFon, null, mRectLeft, null);
		canvas.drawBitmap(mForLeft, null, mRectForLeft, null);
		canvas.drawBitmap(mForRight, null, mRectForRight, null);
		canvas.drawBitmap(mFon, null, mRectRight, null);

		//canvas.drawText(TEXT_BALLS_N, mRectRight.centerX(), mRectRight.centerY(), mPaint);
		drawCenter(canvas, TEXT_BALLS_N + mAvailableBalls);
	}

	private void drawCenter(Canvas canvas, String text) {
		int cWidth = mRectRight.width();
		int cHeight = mRectRight.height();
		Rect r = new Rect();
		mPaint.setTextAlign(Paint.Align.LEFT);
		mPaint.getTextBounds(text, 0, text.length(), r);
		float x = cWidth / 2f - r.width() / 2f - r.left;
		float y = cHeight / 2f + r.height() / 2f - r.bottom;
		canvas.drawText(text, x + mRectRight.left, y + mRectRight.top, mPaint);
	}
	/* МЕТОДЫ */
}