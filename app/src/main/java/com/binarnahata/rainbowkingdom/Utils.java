package com.binarnahata.rainbowkingdom;

import android.graphics.Color;
import android.graphics.Point;

import com.binarnahata.rainbowkingdom.Libs.DoublePoint;

import java.util.Random;

/**
 * RainbowKingdom
 * Created on 06.12.15, 17:49
 *
 * @author bat
 * @version 0.1
 */
public class Utils {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Utils.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Utils(){}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* Рандомы */
	public static int rndInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

	public static float rndFlt(float min, float max) {
		return (float) (min + (max - min) * Math.random());
	}

	public static int rndColor() {
		int r = rndInt(0, 5);
		switch (r) {
			case 0:
				return Color.RED;
			case 1:
				return Color.GREEN;
			case 2:
				return Color.BLUE;
			case 3:
				return Color.YELLOW;
			case 4:
				return Color.CYAN;
			case 5:
				return Color.MAGENTA;
		}
		return Color.RED;
		/*Random rnd = new Random();
		return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));*/
	}
	/* Рандомы */

	/* Математика */
	/**
	 * Определитель второго порядка
	 */
	public static int determinant2Int (int mas[][]){
		return mas[0][0]*mas[1][1]-mas[0][1]*mas[1][0];
	}
	public static double determinant2Double (double mas[][]){
		return mas[0][0]*mas[1][1]-mas[0][1]*mas[1][0];
	}

	/**
	 * Пересечение луча и отрезка
	 */
	public static class Ray {
		public DoublePoint start;
		public DoublePoint point;

		public Ray(DoublePoint start, DoublePoint point) {
			this.start = start;
			this.point = point;
		}
	}

	public static class Segment {
		public DoublePoint start;
		public DoublePoint end;

		public Segment(DoublePoint start, DoublePoint end) {
			this.start = start;
			this.end = end;
		}
	}

	public static double[][] matrixCeilReplace(double[][] matrix, double[] replacement, int ceil){
		for(int i = 0; i<replacement.length; i++){
			matrix[i][ceil]=replacement[i];
		}
		return matrix;
	}


	public static DoublePoint rayIntersection(Ray ray, Segment segment) {

		double matrix[][] = new double[2][2];

		double gipo = Math.sqrt(Math.pow((ray.point.x - ray.start.x), 2) + Math.pow((ray.point.y - ray.start.y), 2));

		double cos = (ray.point.x-ray.start.x)/gipo;
		double sin = (ray.point.y-ray.start.y)/gipo;

		matrix[0][0]=segment.end.x-segment.start.x;
		matrix[0][1]=cos*(-1);
		matrix[1][0]=segment.end.y-segment.start.y;
		matrix[1][1]=sin*(-1);

		double res[] = new double[2];
		res[0]=(segment.start.x-ray.start.x)*(-1);
		res[1]=(segment.start.y-ray.start.y)*(-1);

		double D = determinant2Double(matrix);
		if(D==0)
			return null;

		double D1 = determinant2Double(matrixCeilReplace(matrix, res, 0));
		double D2 = determinant2Double(matrixCeilReplace(matrix, res, 1));

		double T1 = D1/D;
		double T2 = D2/D;

		if ((T2 >= 0) && ((T1 >= 0) && (T1 <= 1))) {
			return new DoublePoint(segment.start.x + T1*(segment.end.x - segment.start.x), segment.start.y + T1*(segment.end.y - segment.start.y));
		}
		return null;
	}

	public static double distanceBetweenTwoPoint(DoublePoint start, DoublePoint end) {
		return Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));
	}
	/* Математика */

	/* Работа с цветами */
	public static int mergeColor(int color1, int color2) {
		if (color1 == Color.RED) {
			if (color2 == Color.BLUE) {
				return Color.MAGENTA;
			}
			if (color2 == Color.GREEN) {
				return Color.YELLOW;
			}
			if (color2 == Color.RED) {
				return Color.RED;
			}
		}
		if (color1 == Color.BLUE) {
			if (color2 == Color.RED) {
				return Color.MAGENTA;
			}
			if (color2 == Color.GREEN) {
				return Color.CYAN;
			}
			if (color2 == Color.BLUE) {
				return Color.BLUE;
			}
		}
		if (color1 == Color.GREEN) {
			if (color2 == Color.RED) {
				return Color.YELLOW;
			}
			if (color2 == Color.BLUE) {
				return Color.CYAN;
			}
			if (color2 == Color.GREEN) {
				return Color.GREEN;
			}
		}
		return 0;
	}
	/* Работа с цветами */

	/* МЕТОДЫ */
}
