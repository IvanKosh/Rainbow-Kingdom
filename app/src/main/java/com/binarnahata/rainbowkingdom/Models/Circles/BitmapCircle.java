package com.binarnahata.rainbowkingdom.Models.Circles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

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

	protected final Bitmap mBitmap;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BitmapCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color);
		mBitmap = bitmap;
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public Bitmap getBitmap() {
		return mBitmap;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		super.draw(canvas, paint);
		canvas.drawBitmap(mBitmap, null,
			new Rect((int) (getX() - getRadius()), (int) (getY() - getRadius()),
				(int) (getX() + getRadius()), (int) (getY() + getRadius())),
			null);
	}
	/* МЕТОДЫ */
}