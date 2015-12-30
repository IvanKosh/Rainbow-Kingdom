package com.binarnahata.rainbowkingdom.Models.Refactoring;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;

/**
 * RainbowKingdom
 * Created on 30.12.15, 18:32
 *
 * @author bat
 * @version 0.1
 */
public class ColorCircle extends MovableCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = ColorCircle.class.getSimpleName();

	protected int mColor;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public int getColor() {
		return mColor;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public ColorCircle(Vector3 position, double radius, int color) {
		super(position, radius);
		mColor = color;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}