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
	public final Paint paint;

	public int amount;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public DisplayAmount() {
		amount = 0;
		paint = new Paint();
		paint.setColor(Color.WHITE);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void display(Canvas canvas, int x, int y) {
		canvas.drawText(String.valueOf(amount), x, y, paint);
	}
	/* МЕТОДЫ */
}