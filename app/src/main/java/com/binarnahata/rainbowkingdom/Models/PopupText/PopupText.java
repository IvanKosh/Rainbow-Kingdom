package com.binarnahata.rainbowkingdom.Models.PopupText;

import android.graphics.Canvas;
import android.graphics.Point;

/**
 * RainbowKingdom
 * Created on 07.01.16, 17:15
 *
 * @author bat
 * @version 0.1
 */
public class PopupText {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PopupText.class.getSimpleName();
	private static final int MAX_LIVE_TIME = 120;

	private String mText;
	private Point mBias;
	private int mLiveTime;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public PopupText(String text, Point bias) {
		mText = text;
		mBias = bias;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {

	}
	/* МЕТОДЫ */
}
