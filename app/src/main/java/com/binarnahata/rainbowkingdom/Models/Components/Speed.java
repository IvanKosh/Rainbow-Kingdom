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

	private float mVectorX;
	private float mVectorY;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public float getVectorX() {
		return mVectorX;
	}
	public float getVectorY() {
		return mVectorY;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Speed(float vectorX, float vectorY) {
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
	/* МЕТОДЫ */
}