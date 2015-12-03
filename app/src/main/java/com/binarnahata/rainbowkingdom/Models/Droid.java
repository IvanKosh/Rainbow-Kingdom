package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

import com.binarnahata.rainbowkingdom.Models.Speed;

/**
 * GameProject
 * Created on 22.11.15, 17:31
 *
 * @author bat
 * @version 0.1
 */
public class Droid {
	private static final String TAG = Droid.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private Bitmap mBitmap; // the actual mBitmap
	private int mX;   // the X coordinate
	private int mY;   // the Y coordinate12
	private boolean mTouched; // if droid is mTouched/picked up
	private Speed mSpeed;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public Bitmap getBitmap() {
		return mBitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		mBitmap = bitmap;
	}
	public int getX() {
		return mX;
	}
	public void setX(int x) {
		mX = x;
	}
	public int getY() {
		return mY;
	}
	public void setY(int y) {
		mY = y;
	}
	public boolean isTouched() {
		return mTouched;
	}

	public void setTouched(boolean touched) {
		this.mTouched = touched;
	}

	public void setSpeed(Speed speed) {
		mSpeed = speed;
	}
	public Speed getSpeed() {
		return mSpeed;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Droid(Bitmap bitmap, int x, int y) {
		mBitmap = bitmap;
		mX = x;
		mY = y;
		mSpeed = new Speed();
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {
		Log.d(TAG, "Droid draw");
		canvas.drawBitmap(mBitmap, mX - (mBitmap.getWidth() / 2),
			mY - (mBitmap.getHeight() / 2), null);
	}

	public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (mX - mBitmap.getWidth() / 2) &&
			(eventX <= (mX + mBitmap.getWidth()/2))) {
			if (eventY >= (mY - mBitmap.getHeight() / 2) &&
				(mY <= (mY + mBitmap.getHeight() / 2))) {
				// droid mTouched
				setTouched(true);
			} else {
				setTouched(false);
			}
		} else {
			setTouched(false);
		}
	}

	public void update() {
		if (!mTouched) {
			mX += (mSpeed.getXv() * mSpeed.getxDirection());
			mY += (mSpeed.getYv() * mSpeed.getyDirection());
		}
	}
	/* МЕТОДЫ */
}
