package com.binarnahata.rainbowkingdom.Models.GameMode;

import com.binarnahata.rainbowkingdom.Libs.Utils;

/**
 * RainbowKingdom
 * Created on 27.12.15, 20:09
 *
 * @author bat
 * @version 0.1
 */
public class QuickGameMode extends GameMode {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = QuickGameMode.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public QuickGameMode() {
		super(Utils.rndCircles(), 2);
		type = "typeq";
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
