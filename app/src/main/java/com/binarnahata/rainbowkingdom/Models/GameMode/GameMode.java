package com.binarnahata.rainbowkingdom.Models.GameMode;

/**
 * RainbowKingdom
 * Created on 27.12.15, 19:16
 *
 * @author bat
 * @version 0.1
 */
public abstract class GameMode {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = GameMode.class.getSimpleName();

	public int number;
	public int rating;
	public String type;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GameMode(int number, int rating) {
		this.number = number;
		this.rating = rating;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}