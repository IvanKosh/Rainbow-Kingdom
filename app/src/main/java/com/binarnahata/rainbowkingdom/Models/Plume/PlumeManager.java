package com.binarnahata.rainbowkingdom.Models.Plume;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Libs.Utils;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 10.01.16, 20:26
 *
 * @author bat
 * @version 0.1
 */
public class PlumeManager {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PlumeManager.class.getSimpleName();
	private final Paint paint;
	private ArrayList<Particle> particles;
	private float circlesRadius;
	private float particleRadius;
	private ArrayList<Particle> newParticles;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public PlumeManager(float circlesRadius) {
		this.circlesRadius = circlesRadius;
		this.particleRadius = circlesRadius / 10;
		particles = new ArrayList<>();
		newParticles = new ArrayList<>();
		paint = new Paint();
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void update() {
		newParticles.clear();
		for (Particle particle : particles) {
			if (particle.isLive()) {
				particle.update();
				newParticles.add(particle);
			}
		}
		particles.clear();
		particles.addAll(newParticles);
	}

	public void draw(Canvas canvas) {
		for (Particle particle : particles) {
			particle.draw(canvas);
		}
	}

	public void add(Vector3 goalPosition, Vector3 direction_n) {
		// направление - нормаль
		// нужно поменять координаты и знак на одном из них, получим поворот на
		// 90 градусов или в одну и другую сторону
		// нужно взять проивольную величину от 0 до радиуса и отсчитать точку от
		// позиции по новой нормали
		// передать полученную точку и позицию как goal

		Vector3 rotateLeft = new Vector3(direction_n.y, -direction_n.x);
		double distance = Utils.randomDouble(0, circlesRadius);
		rotateLeft.mul(distance);
		rotateLeft.add(goalPosition);
		particles.add(new Particle(rotateLeft, goalPosition, particleRadius, paint));

		Vector3 rotateRight = new Vector3(-direction_n.y, direction_n.x);
		distance = Utils.randomDouble(0, circlesRadius);
		rotateRight.mul(distance);
		rotateRight.add(goalPosition);
		particles.add(new Particle(rotateRight, goalPosition, particleRadius, paint));
	}
	/* МЕТОДЫ */
}