package com.binarnahata.rainbowkingdom.Models.Circles;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Views.RKFarm;

/**
 * RainbowKingdom
 * Created on 30.12.15, 18:50
 *
 * @author bat
 * @version 0.1
 */
public class MovableCircle extends BitmapCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MovableCircle.class.getSimpleName();

	protected Vector3 mRoute;
	protected double mSpeed;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setSpeed(Vector3 speed) {
		mSpeed = speed.length();
		limitedSpeed();
		mRoute = Vector3.div(speed, mSpeed);
	}
	public void setSpeed(Vector3 route_n, double speed) {
		mRoute = route_n;
		mSpeed = speed;
		limitedSpeed();
	}
	public Vector3 getSpeed() {
		return Vector3.mul(mRoute, mSpeed);
	}
	/*public void setSpeed(double speed, Vector3 speed_n) {
		if (speed > )
	}*/
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MovableCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color, bitmap);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void move() {
		mPosition.add(Vector3.mul(mRoute, mSpeed));
	}

	public void checkBounds(Rect bounds) {
		if (getX() < bounds.left) {
			// нужно увеличивать X
			toRight();
		}
		if (bounds.right < getX()) {
			// нужно уменьшать X
			toLeft();
		}

		if (getY() < bounds.top) {
			// нужно увеличивать Y
			toDown();
		}
		if (bounds.bottom < getY()) {
			// нужно уменьшать Y
			toUp();
		}
	}

	public void toRight() {
		mRoute.x = Math.abs(mRoute.x);
	}
	public void toLeft() {
		mRoute.x = -Math.abs(mRoute.x);
	}
	public void toDown() {
		mRoute.y = Math.abs(mRoute.y);
	}
	public void toUp() {
		mRoute.y = -Math.abs(mRoute.y);
	}

	public void limitedSpeed() {
		if (mSpeed > RKFarm.sMaxSpeed) {
			mSpeed = RKFarm.sMaxSpeed;
		}
	}
	/* МЕТОДЫ */
}