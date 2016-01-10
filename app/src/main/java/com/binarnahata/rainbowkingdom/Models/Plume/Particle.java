package com.binarnahata.rainbowkingdom.Models.Plume;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;

/**
 * RainbowKingdom
 * Created on 10.01.16, 20:30
 *
 * @author bat
 * @version 0.1
 */
public class Particle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Particle.class.getSimpleName();
	public static final int PARTICLE_LIFETIME = 20;
	private int lifetime;
	private Vector3 position;
	private final Vector3 goalPosition;
	private final float radius;
	private final Paint paint;
	private final Vector3 speed;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public boolean isLive() {
		return lifetime > 0 ? true : false;
	}

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Particle(Vector3 position, Vector3 goalPosition, float radius, Paint paint) {
		this.position = position;
		this.goalPosition = goalPosition;
		this.radius = radius;
		this.paint = paint;
		speed = new Vector3(goalPosition.x - position.x, goalPosition.y - position.y);
		speed.div(PARTICLE_LIFETIME);
		lifetime = PARTICLE_LIFETIME;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {
		canvas.drawCircle((float) position.x, (float) position.y, radius, paint);
	}

	public void update() {
		position.add(speed);
		lifetime--;
	}
	/* МЕТОДЫ */
}