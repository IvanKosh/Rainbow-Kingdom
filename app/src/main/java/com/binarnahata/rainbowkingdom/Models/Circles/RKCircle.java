package com.binarnahata.rainbowkingdom.Models.Circles;

import android.graphics.Bitmap;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Models.Components.Color;

/**
 * RainbowKingdom
 * Created on 30.12.15, 19:30
 *
 * @author bat
 * @version 0.1
 */
public class RKCircle extends MovableCircle {
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
	public static RKCircle merge(MovableCircle circle1, MovableCircle circle2) {
		Vector3 delta = new Vector3(circle1.getX() - circle2.getX(), circle1.getY() - circle2.getY());
		double distanceBetweenCircles = delta.length();

		if (distanceBetweenCircles <= circle1.getDiameter()) {
			RKCircle resultCircle = new RKCircle(
				new Vector3((circle1.getX() + circle2.getX()) / 2,
					(circle1.getY() + circle2.getY()) / 2),
				circle1.getRadius(),
				Color.mergeColor(circle1.getColor(), circle2.getColor()),
				circle1.getBitmap());
			resultCircle.setSpeed(Vector3.add(circle1.getSpeed(), circle2.getSpeed()));
			return resultCircle;
		}
		return null;
	}

	public static boolean solveCollision(RKCircle circle1, RKCircle circle2) {
		Vector3 delta = new Vector3(circle1.getX() - circle2.getX(), circle1.getY() - circle2.getY());
		double distanceBetweenCircles = delta.length();

		if (distanceBetweenCircles <= circle1.getDiameter()) {
			double p1 = delta.x * delta.y / Math.pow(distanceBetweenCircles, 2);
			double p2 = Math.pow(delta.x / distanceBetweenCircles, 2);
			double p3 = Math.pow(delta.y / distanceBetweenCircles, 2);

			Vector3 d = Vector3.sub(circle1.getSpeed(), circle2.getSpeed());
			Vector3 speedBalance = new Vector3(
				Vector3.dot(d, new Vector3(p2, p1)),
				Vector3.dot(d, new Vector3(p1, p3))
			);
			// изменение направления движения
			circle1.getSpeed().sub(speedBalance);
			circle2.getSpeed().add(speedBalance);

			// при соударении шары всегда "проникают" друг в друга, поэтому раздвигаем их
			p3 = (circle1.getDiameter() - distanceBetweenCircles) / 2;
			delta.div(distanceBetweenCircles);
			delta.mul(p3);
			circle1.getPosition().add(delta);
			circle2.getPosition().sub(delta);
			return true;
		}
		return false;
	}
	/* МЕТОДЫ */
}