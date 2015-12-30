package com.binarnahata.rainbowkingdom.Models.Refactoring;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;

/**
 * RainbowKingdom
 * Created on 30.12.15, 18:50
 *
 * @author bat
 * @version 0.1
 */
public class MovableCircle extends SimpleCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MovableCircle.class.getSimpleName();

	protected Vector3 mSpeed;
	protected Vector3 mBoost;
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
	public MovableCircle(Vector3 position, double radius) {
		super(position, radius);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void move() {
		mPosition.add(mSpeed);
	}
	/* МЕТОДЫ */
}