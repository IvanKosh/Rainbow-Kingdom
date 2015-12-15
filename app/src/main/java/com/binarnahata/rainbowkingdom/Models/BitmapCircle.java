package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;

/**
 * RainbowKingdom
 * Created on 14.12.15, 11:27
 *
 * @author bat
 * @version 0.1
 */
public class BitmapCircle extends SimpleCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = BitmapCircle.class.getSimpleName();

	private Bitmap mBitmap;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BitmapCircle(Bitmap bitmap, int x, int y, int radius, int color) {
		super(x, y, radius, color);
		mBitmap = bitmap;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		paint.setColor(mColor);
		canvas.drawCircle(mX, mY, mRadius, paint);
		canvas.drawBitmap(mBitmap, null,
			new Rect((int) (mX - mRadius), (int) (mY-mRadius),
				(int) (mX+mRadius), (int)(mY+mRadius)),
			null);
	}

	public void drawScale(Canvas canvas, Paint paint, float scale) {
		paint.setColor(mColor);
		float r = mRadius * scale;
		canvas.drawCircle(mX, mY, r, paint);
		canvas.drawBitmap(mBitmap, null,
			new Rect((int) (mX - r), (int) (mY-r),
				(int) (mX+r), (int)(mY+r)),
			null);
	}

	@Override
	public BitmapCircle checkCollisionsAndMerge(SimpleCircle circle) {
		double dx = mX-circle.getX();
		double dy = mY-circle.getY();
		double distanceBetweenCircles = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if (distanceBetweenCircles < mDiameter) { // если происходит столкновение
			BitmapCircle resultCircle = new BitmapCircle(mBitmap,
				(int) ((mX + circle.getX()) / 2),
				(int) ((mY + circle.getY()) / 2), mRadius,
				Color.mergeColor(mColor, circle.getColor()));
			resultCircle.setSpeed(new Speed((mSpeed.getVectorX() + circle.getSpeed().getVectorX()) / 2,
				(mSpeed.getVectorY() + circle.getSpeed().getVectorY()) / 2));
			return resultCircle;
		}
		return null;
	}
	/* МЕТОДЫ */
}
