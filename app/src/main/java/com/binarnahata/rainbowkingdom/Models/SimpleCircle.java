package com.binarnahata.rainbowkingdom.Models;

import com.binarnahata.rainbowkingdom.Models.Components.Speed;

/**
 * RainbowKingdom
 * Created on 06.12.15, 13:58
 *
 * @author bat
 * @version 0.1
 */
public class SimpleCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = SimpleCircle.class.getSimpleName();

	private float mX;
	private float mY;
	private int mRadius;
	private int mColor;
	private Speed mSpeed;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public float getX() {
		return mX;
	}
	public float getY() {
		return mY;
	}
	public int getRadius() {
		return mRadius;
	}
	public int getColor() {
		return mColor;
	}
	public Speed getSpeed() {
		return mSpeed;
	}

	public void setSpeed(Speed speed) {
		mSpeed = speed;
	}
	public void setRadius(int radius) { // TODO: delete after
		mRadius = radius;
	}
	public void setX(float x) {
		mX = x;
	}
	public void setY(float y) {
		mY = y;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public SimpleCircle(int x, int y, int radius, int color) {
		mX = x;
		mY = y;
		mRadius = radius;
		mColor = color;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void moveOneStep() {
		mX += mSpeed.getVectorX();
		mY += mSpeed.getVectorY();
	}
	/* МЕТОДЫ */
}
