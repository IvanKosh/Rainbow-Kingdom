package com.binarnahata.rainbowkingdom.Models.Refactoring;

import android.graphics.Bitmap;

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
	public void setSpeed(Vector3 direction_n, double speed) {
		mSpeed = Vector3.mul(direction_n, speed);
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
	/* МЕТОДЫ */
}