package com.binarnahata.rainbowkingdom.Libs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;

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
	private static final Random random = new Random();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Utils(){
		random.setSeed(System.currentTimeMillis());
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* Рандомы */
	public static int rndInt(int min, int max) {
		int r = random.nextInt(max - min) + min;
		return r;
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

	public static String rndHeroAvatar() {
		return "hero_avatar" + rndInt(0, 18);
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
	public static double[][] matrixCeilReplace(double[][] matrix, double[] replacement, int ceil){
		for(int i = 0; i<replacement.length; i++){
			matrix[i][ceil]=replacement[i];
		}
		return matrix;
	}


	public static Point rayIntersection(Ray ray, Segment segment) {

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
			return new Point((int) (segment.start.x + T1*(segment.end.x - segment.start.x)), (int) (segment.start.y + T1*(segment.end.y - segment.start.y)));
		}
		return null;
	}

	public static double distanceBetweenTwoPoint(Point start, Point end) {
		return Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));
	}

	public static int rndCircles() {
		switch (rndInt(0, 2)) {
			case 0:
				return 6;
			case 1:
				return 10;
			case 2:
				return 20;
			default:
				return 10;
		}
	}
	/* Математика */

	/* Работа с цветами */
	/* Работа с цветами */

	/* МЕТОДЫ */
}
