package com.binarnahata.rainbowkingdom.Models.Circles;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;

/**
 * RainbowKingdom
 * Created on 30.12.15, 18:50
 *
 * @author bat
 * @version 0.1
 */
public class MovableCircle extends BitmapCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MovableCircle.class.getSimpleName();

	protected Vector3 mSpeed;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setSpeed(Vector3 speed) {
		mSpeed = speed;
	}
	public Vector3 getSpeed() {
		return mSpeed;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MovableCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color, bitmap);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void move() {
		mPosition.add(mSpeed);
	}

	public void checkBounds(Rect bounds) {
		if (getX() < bounds.left) {
			// нужно увеличивать X
			toRight();
		}
		if (bounds.right < getX()) {
			// нужно уменьшать X
			toLeft();
		}

		if (getY() < bounds.top) {
			// нужно увеличивать Y
			toDown();
		}
		if (bounds.bottom < getY()) {
			// нужно уменьшать Y
			toUp();
		}
	}

	public void toRight() {
		mSpeed.x = Math.abs(mSpeed.x);
	}
	public void toLeft() {
		mSpeed.x = -Math.abs(mSpeed.x);
	}
	public void toDown() {
		mSpeed.y = Math.abs(mSpeed.y);
	}
	public void toUp() {
		mSpeed.y = -Math.abs(mSpeed.y);
	}
	/* МЕТОДЫ */
}