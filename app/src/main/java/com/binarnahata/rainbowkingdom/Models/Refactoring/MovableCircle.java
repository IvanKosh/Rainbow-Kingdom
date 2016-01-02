package com.binarnahata.rainbowkingdom.Models.Refactoring;

import android.graphics.Bitmap;
import android.util.Log;

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

	protected double mSpeed;
	protected Vector3 mRoute;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setSpeed(Vector3 speed) {
		mRoute = speed;
		mSpeed = speed.length();
		if (mSpeed > 0) {
			mRoute.div(mSpeed);
		}
		Log.d(TAG, mRoute.toString() + " " + mSpeed);
		Log.d(TAG, Vector3.mul(mRoute, mSpeed).toString());
	}
	public Vector3 getSpeed() {
		return Vector3.mul(mRoute, mSpeed);
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MovableCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color, bitmap);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void move() {
		mPosition.add(Vector3.mul(mRoute, mSpeed));
	}
	/* МЕТОДЫ */
}