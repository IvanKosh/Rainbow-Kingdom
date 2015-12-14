package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;
import com.binarnahata.rainbowkingdom.Utils;

/**
 * RainbowKingdom
 * Created on 06.12.15, 13:58
 *
 * @author bat
 * @version 0.1
 */
public class SimpleCircle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = SimpleCircle.class.getSimpleName();

	protected float mX;
	protected float mY;
	protected int mRadius;
	protected int mDiameter;
	protected int mColor;
	protected Speed mSpeed;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public float getX() {
		return mX;
	}
	public float getY() {
		return mY;
	}
	public int getRadius() {
		return mRadius;
	}
	public int getColor() {
		return mColor;
	}
	public Speed getSpeed() {
		return mSpeed;
	}

	public void setSpeed(Speed speed) {
		mSpeed = speed;
	}
	public void setRadius(int radius) {
		mRadius = radius;
		mDiameter = radius << 1;
	}
	public void setX(float x) {
		mX = x;
	}
	public void setY(float y) {
		mY = y;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public SimpleCircle(int x, int y, int radius, int color) {
		mX = x;
		mY = y;
		setRadius(radius);
		mColor = color;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void moveOneStep() {
		mX += mSpeed.getVectorX();
		mY += mSpeed.getVectorY();
	}

	public void checkBounds(Rect bounds/*int width, int height*/) {
		if (mX < bounds.left) {
			// нужно увеличивать X
			mSpeed.toRight();
		}
		if (bounds.right < mX) {
			// нужно уменьшать X
			mSpeed.toLeft();
		}

		if (mY < bounds.top) {
			// нужно увеличивать Y
			mSpeed.toDown();
		}
		if (bounds.bottom < mY) {
			// нужно уменьшать Y
			mSpeed.toUp();
		}
	}

	public boolean checkCollisionsAndSetNewOptions(SimpleCircle circle) {
		double dx = mX-circle.getX();
		double dy = mY-circle.getY();
		double distanceBetweenCircles = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if (distanceBetweenCircles < mDiameter) { // если происходит столкновение
			// вычисление новых параметров
			double p1 = dx * dy / Math.pow(distanceBetweenCircles, 2);
			double p2 = Math.pow(dx / distanceBetweenCircles, 2);
			double p3 = Math.pow(dy / distanceBetweenCircles, 2);

			double d1 = mSpeed.getVectorY() * p1
				+ mSpeed.getVectorX() * p2
				- circle.getSpeed().getVectorY() * p1
				- circle.getSpeed().getVectorX() * p2;
			double d2 = mSpeed.getVectorX() * p1
				+ mSpeed.getVectorY() * p3
				- circle.getSpeed().getVectorX() * p1
				- circle.getSpeed().getVectorY() * p3;

			// изменение направления движения
			mSpeed = new Speed(mSpeed.getVectorX() - d1, mSpeed.getVectorY() - d2);
			circle.setSpeed(new Speed(circle.getSpeed().getVectorX() + d1, circle.getSpeed().getVectorY() + d2));

			// при соударении шары всегда "проникают" друг в друга, поэтому раздвигаем их
			p3 = (mDiameter - distanceBetweenCircles) / 2; //при соударении шары всегда "проникают" друг в друга, поэтому раздвигаем их
			p1 = p3 * (dx / distanceBetweenCircles);
			p2 = p3 * (dy / distanceBetweenCircles);
			mX = (float) (mX + p1);
			mY = (float) (mY + p2);
			circle.setX((float) (circle.getX() - p1));
			circle.setY((float) (circle.getY() - p2));

			return true;
		}
		return false;
	}

	public SimpleCircle checkCollisionsAndMerge(SimpleCircle circle) {
		double dx = mX-circle.getX();
		double dy = mY-circle.getY();
		double distanceBetweenCircles = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if (distanceBetweenCircles < mDiameter) { // если происходит столкновение
			SimpleCircle resultCircle = new SimpleCircle((int) ((mX + circle.getX()) / 2),
				(int) ((mY + circle.getY()) / 2), mRadius,
				Color.mergeColor(mColor, circle.getColor()));
			resultCircle.setSpeed(new Speed((mSpeed.getVectorX() + circle.getSpeed().getVectorX()) / 2,
				(mSpeed.getVectorY() + circle.getSpeed().getVectorY()) / 2));
			return resultCircle;
		}
		return null;
	}

	public String centerToString() {
		return "Center:(" + String.valueOf(mX) + ", " + String.valueOf(mY) + ")";
	}
	/* МЕТОДЫ */
}
