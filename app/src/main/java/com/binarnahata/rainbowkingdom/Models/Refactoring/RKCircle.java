package com.binarnahata.rainbowkingdom.Models.Refactoring;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;

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
	public RKCircle merge(MovableCircle circle) {
		RKCircle resultCircle = new RKCircle(
			new Vector3((getX() + circle.getX()) / 2,
				(getY() + circle.getY()) / 2),
			getRadius(),
			Color.mergeColor(getColor(), circle.getColor()),
			mBitmap);
		resultCircle.setSpeed(Vector3.add(getSpeed(), circle.getSpeed()));
		return resultCircle;
	}

	public void checkBounds(Rect bounds) {
		if (mPosition.x < bounds.left) {
			// нужно увеличивать X
			toRight();
		}
		if (bounds.right < mPosition.x) {
			// нужно уменьшать X
			toLeft();
		}

		if (mPosition.y < bounds.top) {
			// нужно увеличивать Y
			toDown();
		}
		if (bounds.bottom < mPosition.y) {
			// нужно уменьшать Y
			toUp();
		}
	}

	public void toRight() {
		mRoute.x = Math.abs(mRoute.x);
	}
	public void toLeft() {
		mRoute.x = -Math.abs(mRoute.x);
	}
	public void toDown() {
		mRoute.y = Math.abs(mRoute.y);
	}
	public void toUp() {
		mRoute.y = -Math.abs(mRoute.y);
	}

	@Override
	public void move() {
		mPosition.add(Vector3.mul(mRoute, MAXIMUM_SPEED));
	}
	/* МЕТОДЫ */
}