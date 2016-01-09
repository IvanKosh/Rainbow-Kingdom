package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Models.Components.Color;

/**
 * RainbowKingdom
 * Created on 03.01.16, 17:20
 *
 * @author bat
 * @version 0.1
 */
public class BottomPanel {
	public static final String TEXT_BALLS_N = "Balls: ";
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = BottomPanel.class.getSimpleName();
	public final Rect mRectLeft;
	private final Bitmap mLineH;
	private final Bitmap mLineV;
	private final Bitmap mCornerBL;
	private final Bitmap mCornerBR;
	private final Rect mCornerL;
	private final Rect mLineHLRect;
	private final Rect mLineVLRect;
	private final Rect mCornerR;
	private final Rect mLineHRRect;
	private final Rect mLineVRRect;
	private final Paint mPaint;
	private final int mTextSize;
	private final Rect mRectRight;
	private int mAvailableBalls;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BottomPanel(Rect dstRect, Bitmap line_h, Bitmap line_v,
					   Bitmap corner_bl, Bitmap corner_br, int availableBalls) {
		mLineH = line_h;
		mLineV = line_v;
		mCornerBL = corner_bl;
		mCornerBR = corner_br;
		mAvailableBalls = availableBalls;

		int size = dstRect.height() / 2;

		Rect centerRect = new Rect(dstRect.centerX() - size, dstRect.centerY() - size,
			dstRect.centerX() + size, dstRect.centerY() + size);

		int border = dstRect.height() / 10;

		mCornerL = new Rect(centerRect.left, centerRect.top - border,
			centerRect.left + border, centerRect.top);
		mLineHLRect = new Rect(dstRect.left, dstRect.top - border,
			centerRect.left, centerRect.top);
		mLineVLRect = new Rect(centerRect.left, centerRect.top,
			centerRect.left + border, dstRect.bottom);

		mCornerR = new Rect(centerRect.right - border, centerRect.top - border,
			centerRect.right, centerRect.top);
		mLineHRRect = new Rect(centerRect.right, centerRect.top - border,
			dstRect.right, dstRect.top);
		mLineVRRect = new Rect(centerRect.right - border, centerRect.top,
			centerRect.right, centerRect.bottom);

		mRectRight = new Rect(mCornerR.right, mCornerR.bottom,
			dstRect.right, dstRect.bottom);
		mRectLeft = new Rect(dstRect.left, dstRect.top,
			centerRect.left, centerRect.bottom);

		mTextSize = dstRect.height() >> 1;
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setTextAlign(Paint.Align.LEFT);
		mPaint.setTextSize(mTextSize);
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void incrementAvailableBalls() {
		mAvailableBalls++;
	}

	public void decrementAvailableBalls() {
		mAvailableBalls--;
	}

	public int getAvailableBalls() {
		return mAvailableBalls;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mCornerBL, null, mCornerL, null);
		canvas.drawBitmap(mLineH, null, mLineHLRect, null);
		canvas.drawBitmap(mLineV, null, mLineVLRect, null);

		canvas.drawBitmap(mCornerBR, null, mCornerR, null);
		canvas.drawBitmap(mLineH, null, mLineHRRect, null);
		canvas.drawBitmap(mLineV, null, mLineVRRect, null);

		drawCenter(canvas, TEXT_BALLS_N + mAvailableBalls);
	}

	private void drawCenter(Canvas canvas, String text) {
		Rect r = new Rect();
		mPaint.getTextBounds(text, 0, text.length(), r);
		float x = mRectRight.width() / 2f - r.width() / 2f - r.left;
		float y = mRectRight.height() / 2f + r.height() / 2f - r.bottom;
		canvas.drawText(text, x + mRectRight.left, y + mRectRight.top, mPaint);
	}
	/* МЕТОДЫ */
}