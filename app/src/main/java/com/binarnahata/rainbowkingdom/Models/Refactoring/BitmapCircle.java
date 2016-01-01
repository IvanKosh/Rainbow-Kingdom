package com.binarnahata.rainbowkingdom.Models.Refactoring;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;

/**
 * RainbowKingdom
 * Created on 30.12.15, 18:55
 *
 * @author bat
 * @version 0.1
 */
public class BitmapCircle extends DrawableCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = BitmapCircle.class.getSimpleName();

	protected Bitmap mBitmap;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BitmapCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color);
		mBitmap = bitmap;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		canvas.drawBitmap(mBitmap, null,
			new Rect((int) (getX() - mRadius), (int) (getY() - mRadius),
				(int) (getX() + mRadius), (int) (getY() + mRadius)),
			null);
	}
	/* МЕТОДЫ */
}