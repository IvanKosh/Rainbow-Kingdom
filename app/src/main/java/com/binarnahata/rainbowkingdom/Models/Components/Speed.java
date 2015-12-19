package com.binarnahata.rainbowkingdom.Models.Components;

import android.graphics.Point;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Libs.Ray;
import com.binarnahata.rainbowkingdom.Libs.Segment;
import com.binarnahata.rainbowkingdom.Libs.Utils;

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
	public static Speed getSpeedForShoot (Rect rectangle, Point target) {
		double t = rectangle.right / 2 + rectangle.left;
		double h = rectangle.bottom + rectangle.top;

		Point LeftBottomPoint = new Point(rectangle.left, rectangle.bottom);
		Point LeftTopPoint = new Point(rectangle.left, rectangle.top);
		Point RightTopPoint = new Point(rectangle.right, rectangle.top);
		Point RightBottomPoint = new Point(rectangle.right, rectangle.bottom);

		Ray ray = new Ray(new Point((int) t, (int) h), target);

		Point intersection = Utils.rayIntersection(ray,
			new Segment(LeftBottomPoint, LeftTopPoint));

		if (intersection == null) {
			intersection = Utils.rayIntersection(ray,
				new Segment(LeftTopPoint, RightTopPoint));
			if (intersection == null) {
				intersection = Utils.rayIntersection(ray,
					new Segment(RightTopPoint, RightBottomPoint));
			}
		}

		if (intersection == null) {
			return null;
		}

		double distance = Utils.distanceBetweenTwoPoint(new Point((int) t, (int) h), intersection);

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