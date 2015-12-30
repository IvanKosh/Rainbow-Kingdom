package com.binarnahata.rainbowkingdom.Models.Refactoring;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;

/**
 * RainbowKingdom
 * Created on 30.12.15, 19:30
 *
 * @author bat
 * @version 0.1
 */
public class RKCircle extends BitmapCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = RKCircle.class.getSimpleName();
	private static final int MAXIMUM_SPEED = 5;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public RKCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color, bitmap);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public boolean checkCollision(SimpleCircle circle) {
		if (Vector3.add(mPosition, circle.mPosition).length() < mDiameter) {
			return true;
		}
		return false;
	}

	public RKCircle merge(ColorCircle circle) {
		RKCircle resultCircle = new RKCircle(
			new Vector3((mPosition.x + circle.getX()) / 2,
				(mPosition.y + circle.getY()) / 2),
			mRadius,
			Color.mergeColor(mColor, circle.getColor()),
			mBitmap);
		resultCircle.setSpeed(Vector3.add(mSpeed, circle.getSpeed()));
		return resultCircle;
	}

	public void checkBounds(Rect bounds) {
		if (mPosition.x < bounds.left) {
			// нужно увеличивать X
			toRight();
		}
		if (bounds.right < mPosition.x) {
			// нужно уменьшать X
			toLeft();
		}

		if (mPosition.y < bounds.top) {
			// нужно увеличивать Y
			toDown();
		}
		if (bounds.bottom < mPosition.y) {
			// нужно уменьшать Y
			toUp();
		}
	}

	public void toRight() {
		mSpeed.x = Math.abs(mSpeed.x);
	}
	public void toLeft() {
		mSpeed.x = -Math.abs(mSpeed.x);
	}
	public void toDown() {
		mSpeed.y = Math.abs(mSpeed.y);
	}
	public void toUp() {
		mSpeed.y = -Math.abs(mSpeed.y);
	}
	/* МЕТОДЫ */
}