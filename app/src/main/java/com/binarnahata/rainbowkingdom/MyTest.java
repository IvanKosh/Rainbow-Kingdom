package com.binarnahata.rainbowkingdom;

import junit.framework.Test;

/**
 * RainbowKingdom
 * Created on 22.12.15, 20:49
 *
 * @author bat
 * @version 0.1
 */
public class MyTest {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MyTest.class.getSimpleName();

	public static MyTest sInt;

	public Integer mInt;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */

	public Integer getInt() {
		return mInt;
	}

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private MyTest () {
		mInt = 30;
	}

	public static MyTest getInstance() {
		if (sInt == null) {
			sInt = new MyTest();
		}
		return sInt;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
