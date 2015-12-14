package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * RainbowKingdom
 * Created on 13.12.15, 15:58
 *
 * @author bat
 * @version 0.1
 */
public abstract class DrawableObject {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = DrawableObject.class.getSimpleName();

	private float mPriority;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setPriority(float f) {
		mPriority = f;
	}

	public float getPriority() {
		return mPriority;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public DrawableObject() {
		super();
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public abstract void draw(Canvas canvas, Rect dstRect);
	/* МЕТОДЫ */
}