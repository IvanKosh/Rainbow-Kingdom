package com.binarnahata.rainbowkingdom.Libs.Math;


import com.binarnahata.rainbowkingdom.Libs.Utils;

/**
 * RainbowKingdom
 * Created on 30.12.15, 17:41
 *
 * @author bat
 * @version 0.1
 */
public class Vector3 {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Vector3.class.getSimpleName();

	public double x;
	public double y;
	public double z;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = .0;
	}
	public void setXY(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public void setXYZ(Vector3 v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Vector3() {
		x = .0;
		y = .0;
		z = .0;
	}
	public Vector3(double x, double y) {
		this.x = x;
		this.y = y;
		this.z = .0;
	}
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/**
	 * Длинна вектора
	 * Calculate the magnitude (length) of the vector
	 * @return the length of the vector
	 */
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	public double length() {
		return Math.sqrt(x*x + y*y + z*z);
	}

	public Vector3 copy() {
		return new Vector3(x, y, z);
	}
	public static Vector3 copy(Vector3 v) {
		return new Vector3(v.x, v.y, v.z);
	}

	public void add(Vector3 v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}
	public void offset(Vector3 v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}

	public void sub(Vector3 v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}
	public void approve(Vector3 v) {
		x -= v.x;
		y -= v.y;
		z -= v.z;
	}

	public void mul(double n) {
		x *= n;
		y *= n;
		z *= n;
	}
	public void mul(long n) {
		x *= n;
		y *= n;
		z *= n;
	}

	public void div(double n) {
		x /= n;
		y /= n;
		z /= n;
	}
	public void div(long n) {
		x /= n;
		y /= n;
		z /= n;
	}

	/**
	 * Скалярное произведение
	 * Calculate the dot product with another vector
	 * @return  the dot product
	 */
	public double dot(Vector3 v) {
		return x*v.x + y*v.y + z*v.z;
	}

	/**
	 * Векторное произведение
	 * Calculate the cross product with another vector
	 * @return  the cross product
	 */
	public Vector3 cross(Vector3 v) {
		double crossX = y * v.z - v.y * z;
		double crossY = z * v.x - v.z * x;
		double crossZ = x * v.y - v.x * y;
		return(new Vector3(crossX,crossY,crossZ));
	}

	/**
	 * Normalize the vector to length 1 (make it a unit vector)
	 */
	public void normalize() {
		double m = length();
		if (m > 0) {
			div(m);
		}
	}

	/**
	 * Limit the length of this vector
	 * @param max the maximum length to limit this vector
	 */
	public void limit(double max) {
		if (length() > max) {
			normalize();
			mul(max);
		}
	}

	/**
	 * Calculate the angle of rotation for this vector (only 2D vectors)
	 * @return the angle of rotation
	 */
	public double heading2D() {
		return -1*Math.atan2(-y, x);
	}

	/**
	 * Rotates a 2D Vector
	 * @param theta, angle in radians to rotate vector
	 */
	public void rotate2D(double theta) {
		double currentTheta = heading2D();
		double length = length();
		currentTheta += theta;
		x = length*Math.cos(currentTheta);
		y = length*Math.sin(currentTheta);
	}

	/**
	 * Add two vectors
	 * @param v1 a vector
	 * @param v2 another vector
	 * @return a new vector that is the sum of v1 and v2
	 */
	public static Vector3 add(Vector3 v1, Vector3 v2) {
		return new Vector3(v1.x + v2.x, v1.y + v2.y, v1.z + v2.z);
	}

	/**
	 * Subtract one vector from another
	 * @param v1 a vector
	 * @param v2 another vector
	 * @return a new vector that is v1 - v2
	 */
	public static Vector3 sub(Vector3 v1, Vector3 v2) {
		return new Vector3(v1.x - v2.x, v1.y - v2.y, v1.z - v2.z);
	}

	/**
	 * Divide a vector by a scalar
	 * @param v1 a vector
	 * @param n scalar
	 * @return a new vector that is v1 / n
	 */
	public static Vector3 div(Vector3 v1, double n) {
		return new Vector3(v1.x/n, v1.y/n, v1.z/n);
	}

	/**
	 * Multiply a vector by a scalar
	 * @param v1 a vector
	 * @param n scalar
	 * @return a new vector that is v1 * n
	 */
	public static Vector3 mul(Vector3 v1, double n) {
		return new Vector3(v1.x*n, v1.y*n, v1.z*n);
	}

	/**
	 * Rotates a 2D Vector
	 * @param theta, angle in radians to rotate vector
	 * @return a new Vector object, rotated by theta
	 */
	public static Vector3 rotate2D(Vector3 v, double theta) {
		// What is my current heading
		double currentTheta = v.heading2D();
		// What is my current speed
		double length = v.length();
		// Turn me
		currentTheta += theta;
		// Look, polar coordinates to cartesian!!
		return  new Vector3(length*Math.cos(currentTheta), length*Math.cos(currentTheta));
	}

	/**
	 * Calculate the Euclidean distance between two points (considering a point as a vector object)
	 * @param v1 a vector
	 * @param v2 another vector
	 * @return the Euclidean distance between v1 and v2
	 */
	public static double distance(Vector3 v1, Vector3 v2) {
		double dx = v1.x - v2.x;
		double dy = v1.y - v2.y;
		double dz = v1.z - v2.z;
		return Math.sqrt(dx*dx + dy*dy + dz*dz);
	}

	/**
	 * Calculate the angle between two vectors, using the dot product
	 * @param v1 a vector
	 * @param v2 another vector
	 * @return the angle between the vectors
	 */
	public static double angleBetween(Vector3 v1, Vector3 v2) {
		double dot = v1.dot(v2);
		return Math.acos(dot / (v1.length() * v2.length()));
	}

	public static Vector3 getRandomNormal() {
		Vector3 result = new Vector3(Utils.rndDouble(), Utils.rndDouble());
		result.normalize();
		return result;
	}

	public String toString() {
		return "x: " + x + ", y: " + y + ", z: " + z;
	}
	/* МЕТОДЫ */
}