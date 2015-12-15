package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * RainbowKingdom
 * Created on 13.12.15, 16:57
 *
 * @author bat
 * @version 0.1
 */
public class DrawableBitmap extends DrawableObject  {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = DrawableBitmap.class.getSimpleName();

	private Bitmap mBitmap;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public DrawableBitmap(Bitmap bitmap) {
		mBitmap = bitmap;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void draw(Canvas canvas, Rect dstRect) {
		canvas.drawBitmap(mBitmap, null, dstRect, null);
	}
	/* МЕТОДЫ */
}