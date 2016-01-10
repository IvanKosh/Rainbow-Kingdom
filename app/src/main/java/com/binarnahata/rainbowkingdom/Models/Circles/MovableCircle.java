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

	protected Vector3 direction;
	protected double mSpeed;

	/*public void setSpeed(double speed, Vector3 speed_n) {
		if (speed > )
	}*/
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MovableCircle(Vector3 position, double radius, int color, Bitmap bitmap) {
		super(position, radius, color, bitmap);
	}

	public void setSpeed(Vector3 direction_n, double speed) {
		direction = direction_n;
		mSpeed = speed;
		limitedSpeed();
	}

	public Vector3 getSpeed() {
		return Vector3.mul(direction, mSpeed);
	}


	public Vector3 getDirection() {
		return direction;
	}
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setSpeed(Vector3 speed) {
		mSpeed = speed.length();
		limitedSpeed();
		direction = Vector3.div(speed, mSpeed);
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void move() {
		mPosition.add(Vector3.mul(direction, mSpeed));
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
		direction.x = Math.abs(direction.x);
	}

	public void toLeft() {
		direction.x = -Math.abs(direction.x);
	}

	public void toDown() {
		direction.y = Math.abs(direction.y);
	}

	public void toUp() {
		direction.y = -Math.abs(direction.y);
	}

	public void limitedSpeed() {
		if (mSpeed > RKFarm.sMaxSpeed) {
			mSpeed = RKFarm.sMaxSpeed;
		}
	}
	/* МЕТОДЫ */
}