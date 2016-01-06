package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.binarnahata.rainbowkingdom.Models.Components.Color;

/**
 * RainbowKingdom
 * Created on 15.12.15, 11:17
 *
 * @author bat
 * @version 0.1
 */
public class DisplayAmount {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = DisplayAmount.class.getSimpleName();

	public int amount;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public DisplayAmount() {
		amount = 0;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void display(Canvas canvas, int x, int y, Paint paint) {
		canvas.drawText(String.valueOf(amount), x, y, paint);
	}
	/* МЕТОДЫ */
}
