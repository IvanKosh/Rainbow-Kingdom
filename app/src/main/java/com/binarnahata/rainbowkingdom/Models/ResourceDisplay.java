package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Models.ColorAnarchy.DisplayAmount;
import com.binarnahata.rainbowkingdom.Models.ColorAnarchy.RedDisplayAmount;
import com.binarnahata.rainbowkingdom.Models.Components.Color;

/**
 * RainbowKingdom
 * Created on 15.12.15, 10:08
 *
 * @author bat
 * @version 0.1
 */
public class ResourceDisplay {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = ResourceDisplay.class.getSimpleName();

	private BitmapCircle mCircle;
	private Rect mField;

	public RedDisplayAmount red;
	public RedDisplayAmount green;
	public RedDisplayAmount blue;
	public RedDisplayAmount cyan;
	public RedDisplayAmount magenta;
	public RedDisplayAmount yellow;

	public DisplayAmount now;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setColor(int color){
		mCircle.setColor(color);
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public ResourceDisplay(Bitmap bitmap, int radius, Rect field) {
		mField = field;
		mCircle = new BitmapCircle(bitmap, radius << 1, mField.centerY(), radius, Color.RED);

		now = red;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		mCircle.drawScale(canvas, paint, 0.75f);
		now.display(canvas);
	}
	/* МЕТОДЫ */
}
