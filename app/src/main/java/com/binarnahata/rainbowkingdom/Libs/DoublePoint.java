package com.binarnahata.rainbowkingdom.Libs;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * RainbowKingdom
 * Created on 11.12.15, 9:37
 *
 * @author bat
 * @version 0.1
 */
public class DoublePoint implements Parcelable {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = DoublePoint.class.getSimpleName();

	public double x;
	public double y;

	public DoublePoint() {}

	public DoublePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public DoublePoint(DoublePoint src) {
		this.x = src.x;
		this.y = src.y;
	}
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public final void negate() {
		x = -x;
		y = -y;
	}

	public final void offset(int dx, int dy) {
		x += dx;
		y += dy;
	}


	public final boolean equals(int x, int y) {
		return this.x == x && this.y == y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DoublePoint point = (DoublePoint) o;

		if (x != point.x) return false;
		if (y != point.y) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) x;
		result = (int) (31 * result + y);
		return result;
	}

	@Override
	public String toString() {
		return "Point(" + x + ", " + y + ")";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(x);
		dest.writeDouble(y);
	}


	protected DoublePoint(Parcel in) {
		x = in.readDouble();
		y = in.readDouble();
	}

	public static final Creator<DoublePoint> CREATOR = new Creator<DoublePoint>() {
		@Override
		public DoublePoint createFromParcel(Parcel in) {
			return new DoublePoint(in);
		}

		@Override
		public DoublePoint[] newArray(int size) {
			return new DoublePoint[size];
		}
	};
	/* МЕТОДЫ */
}
