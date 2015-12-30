package com.binarnahata.rainbowkingdom.Models.Refactoring;

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
public class DrawableCircle extends ColorCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = DrawableCircle.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public DrawableCircle(Vector3 position, double radius, int color) {
		super(position, radius, color);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(mColor);
		canvas.drawCircle((float) mPosition.x, (float) mPosition.y,
			(float) mRadius, paint);
	}
	/* МЕТОДЫ */
}