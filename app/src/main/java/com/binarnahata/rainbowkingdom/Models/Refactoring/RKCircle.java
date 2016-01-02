package com.binarnahata.rainbowkingdom.Models.Refactoring;

import android.graphics.Bitmap;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Models.Components.Color;

/**
 * RainbowKingdom
 * Created on 30.12.15, 19:30
 *
 * @author bat
 * @version 0.1
 */
public class RKCircle extends MovableCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = RKCircle.class.getSimpleName();
	private static final int MAXIMUM_SPEED = 5;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public RKCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color, bitmap);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public static RKCircle merge(MovableCircle circle1, MovableCircle circle2) {
		RKCircle resultCircle = new RKCircle(
			new Vector3((circle1.getX() + circle2.getX()) / 2,
				(circle1.getY() + circle2.getY()) / 2),
			circle1.getRadius(),
			Color.mergeColor(circle1.getColor(), circle2.getColor()),
			circle1.getBitmap());
		resultCircle.setSpeed(Vector3.add(circle1.getSpeed(), circle2.getSpeed()));
		return resultCircle;
	}
	/* МЕТОДЫ */
}