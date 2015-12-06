package com.binarnahata.rainbowkingdom.Models;

/**
 * GameProject
 * Created on 22.11.15, 17:55
 *
 * @author bat
 * @version 0.1
 */
public class Speed {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_LEFT  = -1;
	public static final int DIRECTION_UP    = -1;
	public static final int DIRECTION_DOWN  = 1;

	private float xv = 1;   // velocity value on the X axis
	private float yv = 1;   // velocity value on the Y axis

	private int xDirection = DIRECTION_RIGHT;
	private int yDirection = DIRECTION_DOWN;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public float getXv() {
		return xv;
	}
	public void setXv(float xv) {
		this.xv = xv;
	}
	public float getYv() {
		return yv;
	}
	public void setYv(float yv) {
		this.yv = yv;
	}

	public int getxDirection() {
		return xDirection;
	}
	public void setxDirection(int xDirection) {
		this.xDirection = xDirection;
	}
	public int getyDirection() {
		return yDirection;
	}
	public void setyDirection(int yDirection) {
		this.yDirection = yDirection;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Speed() {
		this.xv = 1;
		this.yv = 1;
	}

	public Speed(float xv, float yv) {
		this.xv = xv;
		this.yv = yv;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	// changes the direction on the X axis
	public void toggleXDirection() {
		xDirection = xDirection * -1;
	}

	// changes the direction on the Y axis
	public void toggleYDirection() {
		yDirection = yDirection * -1;
	}
	/* МЕТОДЫ */
}
