package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Models.Circles.BitmapCircle;
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
	private final int mAmountX;
	private final int mCenterY;

	private BitmapCircle mCircle;
	private Rect mField;

	public DisplayAmount red;
	public DisplayAmount green;
	public DisplayAmount blue;
	public DisplayAmount cyan;
	public DisplayAmount magenta;
	public DisplayAmount yellow;

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

		mAmountX = radius << 2;

		red = new DisplayAmount();
		green = new DisplayAmount();
		blue = new DisplayAmount();
		cyan = new DisplayAmount();
		magenta = new DisplayAmount();
		yellow = new DisplayAmount();

		now = red;


		Rect r = new Rect();
		now.paint.getTextBounds("0", 0, "0".length(), r);
		mCenterY = (int) (field.height() / 2f + r.height() / 2f - r.bottom + field.top);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		mCircle.drawScale(canvas, paint, 0.75f);
		now.display(canvas, mAmountX, mCenterY);
	}

	public void setRed() {
		now = red;
		mCircle.setColor(Color.RED);
		now.amount++;
	}

	public void setGreed() {
		now = green;
		mCircle.setColor(Color.GREEN);
		now.amount++;
	}

	public void setBlue() {
		now = blue;
		mCircle.setColor(Color.BLUE);
		now.amount++;
	}

	public void setCyan() {
		now = cyan;
		mCircle.setColor(Color.CYAN);
		now.amount++;
	}

	public void setMagenta() {
		now = magenta;
		mCircle.setColor(Color.MAGENTA);
		now.amount++;
	}

	public void setYellow() {
		now = yellow;
		mCircle.setColor(Color.YELLOW);
		now.amount++;
	}
	/* МЕТОДЫ */
}