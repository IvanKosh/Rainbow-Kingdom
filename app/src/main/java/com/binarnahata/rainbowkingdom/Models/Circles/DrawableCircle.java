package com.binarnahata.rainbowkingdom.Models.Circles;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;

/**
 * RainbowKingdom
 * Created on 30.12.15, 18:35
 *
 * @author bat
 * @version 0.1
 */
public class DrawableCircle extends SimpleCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = DrawableCircle.class.getSimpleName();

	private int mColor;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public DrawableCircle(Vector3 position, double radius, int color) {
		super(position, radius);
		mColor = color;
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public int getColor() {
		return mColor;
	}

	public void setColor(int color) {
		mColor = color;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(mColor);
		canvas.drawCircle((float) getX(), (float) getY(),
			(float) getRadius(), paint);
	}
	/* МЕТОДЫ */
}