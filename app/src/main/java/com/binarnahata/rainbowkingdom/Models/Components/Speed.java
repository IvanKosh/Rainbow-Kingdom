package com.binarnahata.rainbowkingdom.Models.Components;

import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Libs.DoublePoint;
import com.binarnahata.rainbowkingdom.Libs.Rectangle;
import com.binarnahata.rainbowkingdom.Utils;

/**
 * RainbowKingdom
 * Created on 06.12.15, 17:30
 *
 * @author bat
 * @version 0.1
 */
public class Speed {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Speed.class.getSimpleName();
	public static final int MAXIMUM_SPEED = 5;

	private double mVectorX;
	private double mVectorY;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public double getVectorX() {
		return mVectorX;
	}
	public double getVectorY() {
		return mVectorY;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Speed (double vectorX, double vectorY) {
		mVectorX = vectorX;
		mVectorY = vectorY;
	}

	/**
	 * Конструктор для получения скорости "выстрела"
	 * @param rectangle
	 * @param target
	 */
	public static Speed getSpeedForShoot (Rectangle rectangle, DoublePoint target) {
		double t = rectangle.width / 2 + rectangle.x;
		double h = rectangle.height + rectangle.y;

		Utils.Ray ray = new Utils.Ray(new DoublePoint(t, h), target);

		DoublePoint intersection = Utils.rayIntersection(ray,
			new Utils.Segment(rectangle.getBottomLeftPoint(), rectangle.getTopLeftPoint()));

		if (intersection == null) {
			intersection = Utils.rayIntersection(ray,
				new Utils.Segment(rectangle.getTopLeftPoint(), rectangle.getTopRightPoint()));
			if (intersection == null) {
				intersection = Utils.rayIntersection(ray,
					new Utils.Segment(rectangle.getTopRightPoint(), rectangle.getBottomRightPoint()));
			}
		}

		if (intersection == null) {
			return null;
		}

		double distance = Utils.distanceBetweenTwoPoint(new DoublePoint(t, h), intersection);

		return new Speed(MAXIMUM_SPEED*(target.x-t)/distance, MAXIMUM_SPEED*(h-target.y)/distance);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void toggleXDirection() {
		mVectorX = -mVectorX;
	}

	public void toggleYDirection() {
		mVectorY = -mVectorY;
	}

	public void toRight() {
		mVectorX = Math.abs(mVectorX);
	}

	public void toLeft() {
		mVectorX = -Math.abs(mVectorX);
	}

	public void toDown() {
		mVectorY = Math.abs(mVectorY);
	}

	public void toUp() {
		mVectorY = -Math.abs(mVectorY);
	}

	@Override
	public String toString () {
		return String.valueOf(mVectorX) + " " + String.valueOf(mVectorY);
	}
	/* МЕТОДЫ */
}