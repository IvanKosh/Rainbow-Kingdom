package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * RainbowKingdom
 * Created on 03.01.16, 17:20
 *
 * @author bat
 * @version 0.1
 */
public class BottomPanel {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = BottomPanel.class.getSimpleName();
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

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BottomPanel(Rect dstRect, Bitmap line_h, Bitmap line_v, Bitmap corner_bl, Bitmap corner_br, int availableBalls) {
		mLineH = line_h;
		mLineV = line_v;
		mCornerBL = corner_bl;
		mCornerBR = corner_br;

		int size = dstRect.height() / 2;

		Rect centerRect = new Rect(dstRect.centerX() - size, dstRect.centerY() - size,
			dstRect.centerX() + size, dstRect.centerY() + size);

		int border = dstRect.height() / 10;

		mCornerL = new Rect(centerRect.left-border, centerRect.top, centerRect.top + border, centerRect.left);
		mLineHLRect = new Rect(0, 0, 10, 10);
		mLineVLRect = new Rect(0, 0, 10, 10);

		mCornerR = new Rect(0, 0, 10, 10);
		mLineHRRect = new Rect(0, 0, 10, 10);
		mLineVRRect = new Rect(0, 0, 10, 10);
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
	}
	/* МЕТОДЫ */
}