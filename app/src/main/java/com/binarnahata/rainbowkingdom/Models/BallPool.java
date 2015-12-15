package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.binarnahata.rainbowkingdom.Utils;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 14.12.15, 16:47
 *
 * @author bat
 * @version 0.1
 */
public class BallPool {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = BallPool.class.getSimpleName();
	private final Bitmap mBitmap;
	private final int mRadius;
	private final int mDiameter;

	private boolean needMoveState;
	private Point mTo;

	private BitmapCircle mFirstBall;
	private BitmapCircle mSecondBall;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public BitmapCircle getCircle() {
		BitmapCircle circle	= mFirstBall;
		mFirstBall = mSecondBall;
		mSecondBall = new BitmapCircle(mBitmap, mTo.x, mTo.y + 2*mDiameter,
			mRadius, Utils.rndColor());
		needMoveState = true;
		return circle;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BallPool(Bitmap bitmap, int diameter, Point to) {
		needMoveState = false;
		mTo = to;
		mBitmap = bitmap;
		mDiameter = diameter;
		mRadius = diameter >> 1;


		// генерация пула шаров
		mFirstBall = new BitmapCircle(mBitmap, mTo.x, mTo.y, mRadius, Utils.rndColor());
		mSecondBall = new BitmapCircle(mBitmap, mTo.x, mTo.y + mDiameter,
			mRadius, Utils.rndColor());
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Paint paint) {
		mFirstBall.draw(canvas, paint);
		mSecondBall.draw(canvas, paint);
	}

	public void update() {
		if (needMoveState) {
			if (mTo.y < mFirstBall.getY()) {
				mFirstBall.offset(0, -1);
				if (mTo.y + mDiameter < mSecondBall.getY()) {
					mSecondBall.offset(0, -1);
				}
				else {
					needMoveState = false;
				}
			}
		}
	}
	/* МЕТОДЫ */
}