package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
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
	public DisplayAmount black;

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

		mCircle = new BitmapCircle(new Vector3(radius << 1, mField.centerY()), radius*.9, Color.getRandom(), bitmap);

		mAmountX = radius << 2;

		red = new DisplayAmount();
		green = new DisplayAmount();
		blue = new DisplayAmount();
		cyan = new DisplayAmount();
		magenta = new DisplayAmount();
		yellow = new DisplayAmount();

		switch(mCircle.getColor()) {
			case Color.RED:
				now = red;
				mCircle.setColor(Color.RED);
				break;
			case Color.GREEN:
				now = green;
				mCircle.setColor(Color.GREEN);
				break;
			case Color.BLUE:
				now = blue;
				mCircle.setColor(Color.BLUE);
				break;
			case Color.CYAN:
				now = cyan;
				mCircle.setColor(Color.CYAN);
				break;
			case Color.MAGENTA:
				now = magenta;
				mCircle.setColor(Color.MAGENTA);
				break;
			case Color.YELLOW:
				now = yellow;
				mCircle.setColor(Color.YELLOW);
				break;
			default:
				now = black;
				mCircle.setColor(Color.BLACK);
		}


		Rect r = new Rect();
		now.paint.getTextBounds("0", 0, "0".length(), r);
		mCenterY = (int) (field.height() / 2f + r.height() / 2f - r.bottom + field.top);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		mCircle.draw(canvas, paint);
		now.display(canvas, mAmountX, mCenterY);
	}

	public void setRed() {
		now = red;
		mCircle.setColor(Color.RED);
		now.amount++;
	}

	public void setGreen() {
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