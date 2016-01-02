package com.binarnahata.rainbowkingdom.Models.Circles;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;

/**
 * RainbowKingdom
 * Created on 30.12.15, 17:30
 *
 * @author bat
 * @version 0.1
 */
public class SimpleCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = SimpleCircle.class.getSimpleName();

	protected Vector3 mPosition;
	protected double mRadius;
	protected double mDiameter;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public Vector3 getPosition() {
		return mPosition;
	}
	public void setPosition(Vector3 position) {
		mPosition = position;
	}
	public double getRadius() {
		return mRadius;
	}
	public double getX() {
		return mPosition.x;
	}
	public double getY() {
		return mPosition.y;
	}
	public double getDiameter() {
		return mDiameter;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public SimpleCircle(Vector3 position, double radius) {
		mPosition = position;
		mRadius = radius;
		mDiameter = radius*2;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}