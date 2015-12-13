package com.binarnahata.rainbowkingdom.Libs;

/**
 * RainbowKingdom
 * Created on 12.12.15, 17:34
 *
 * @author bat
 * @version 0.1
 */
public class Rectangle {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Rectangle.class.getSimpleName();

	public double x;
	public double y;
	public double width;
	public double height;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public DoublePoint getTopLeftPoint() {
		return new DoublePoint(x, y);
	}
	public DoublePoint getTopRightPoint() {
		return new DoublePoint(width+x, y);
	}
	public DoublePoint getBottomLeftPoint() {
		return new DoublePoint(x, height+y);
	}
	public DoublePoint getBottomRightPoint() {
		return new DoublePoint(width+x, height+y);
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Rectangle(DoublePoint topLeftPoint, DoublePoint bottomRightPoint) {
		this.x = topLeftPoint.x;
		this.y = topLeftPoint.y;
		this.width = bottomRightPoint.x - this.x;
		this.height = bottomRightPoint.y - this.y;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public boolean containsPoint(DoublePoint point) {
		return (x <= point.x
			&& point.x <= width + x
			&& y <= point.y
			&& point.y <= height + y);
	}
	/* МЕТОДЫ */
}
