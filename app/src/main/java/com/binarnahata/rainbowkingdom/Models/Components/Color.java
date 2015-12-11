package com.binarnahata.rainbowkingdom.Models.Components;

import android.support.annotation.ColorInt;

/**
 * RainbowKingdom
 * Created on 11.12.15, 20:41
 *
 * @author bat
 * @version 0.1
 */
public class Color {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Color.class.getSimpleName();

	public static final int WHITE		= 0xFFFFFFFF;
	public static final int BLACK		= 0xFF000000;

	public static final int RED         = 0xFFFF0000;
	public static final int GREEN       = 0xFF00FF00;
	public static final int BLUE        = 0xFF0000FF;

	public static final int YELLOW      = 0xFFFFFF00;
	public static final int CYAN        = 0xFF00FFFF;
	public static final int MAGENTA     = 0xFFFF00FF;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public static int mergeColor(int color1, int color2) {
		int or = color1 | color2;
		int and = color1 & color2;

		if (or != WHITE && and == BLACK) {
			// оба цвета базовые (RGB)
			return or;
		}
		if (and != BLACK && or == WHITE) {
			// оба цвета походные (CYM)
			return and;
		}

		return color1;
	}

	public static boolean canMerge(int color1, int color2) {
		if (color1 == color2) {
			return true;
		}
		int or = color1 | color2;
		int and = color1 & color2;

		return ((or != WHITE && and == BLACK) || (and != BLACK && or == WHITE));
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
