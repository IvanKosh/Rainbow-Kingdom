package com.binarnahata.rainbowkingdom.Libs;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;

import com.binarnahata.rainbowkingdom.Models.Components.Color;

import java.util.ArrayList;
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
	public static void setRandom() {
		random.setSeed(System.currentTimeMillis());
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* Рандомы */
	public static int rndInt(int min, int max) {
		int r = random.nextInt(max - min) + min;
		return r;
	}

	public static double rndDouble() {
		return random.nextDouble();
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

	public static double distanceBetweenTwoPoint(Point start, Point end) {
		return Math.sqrt(Math.pow(end.x - start.x, 2) + Math.pow(end.y - start.y, 2));
	}

	public static int rndCircles() {
		switch (rndInt(0, 3)) {
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
	public static ArrayList<Integer> getColorPool() {
		return new ArrayList<Integer>(){{
			add(Color.RED); add(Color.GREEN); add(Color.BLUE);
			add(Color.CYAN); add(Color.MAGENTA); add(Color.YELLOW);
		}};
	}
	/* Работа с цветами */

	/* МЕТОДЫ */
}
