package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Libs.Utils;
import com.binarnahata.rainbowkingdom.Models.Circles.BitmapCircle;
import com.binarnahata.rainbowkingdom.Models.Circles.RKCircle;
import com.binarnahata.rainbowkingdom.Models.Components.Color;

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
	private Vector3 mTo;
	private Vector3 mStart;
	private Vector3 mStep = new Vector3(0, -1);

	private RKCircle mFirstBall;
	private RKCircle mSecondBall;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public RKCircle getCircle() {
		if (needMoveState) {
			return null;
		}
		RKCircle circle	= mFirstBall;
		mFirstBall = mSecondBall;
		mSecondBall = new RKCircle(mStart, mRadius, Color.getRandom(), mBitmap);
		needMoveState = true;
		return circle;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BallPool(Bitmap bitmap, int diameter, Vector3 to) {
		needMoveState = false;
		mTo = to;
		mBitmap = bitmap;
		mDiameter = diameter;
		mRadius = diameter >> 1;
		mStart = new Vector3(to.x, to.y+diameter*2);

		// генерация пула шаров
		mFirstBall = new RKCircle(mTo, mRadius, Color.getRandom(), mBitmap);
		mSecondBall = new RKCircle(new Vector3(mTo.x, mTo.y+mDiameter),
			mRadius, Color.getRandom(), mBitmap);
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
				mFirstBall.getPosition().add(mStep);
				if (mTo.y + mDiameter < mSecondBall.getY()) {
					mSecondBall.getPosition().add(mStep);
				}
				else {
					needMoveState = false;
				}
			}
			else {
				needMoveState = false;
			}
		}
	}
	/* МЕТОДЫ */
}