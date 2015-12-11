package com.binarnahata.rainbowkingdom.Models.Components;

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
	public Speed(double vectorX, double vectorY) {
		mVectorX = vectorX;
		mVectorY = vectorY;
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