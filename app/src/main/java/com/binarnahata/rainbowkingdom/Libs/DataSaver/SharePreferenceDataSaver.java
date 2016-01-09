package com.binarnahata.rainbowkingdom.Libs.DataSaver;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * RainbowKingdom
 * Created on 23.12.15, 11:38
 *
 * @author bat
 * @version 0.1
 */
public class SharePreferenceDataSaver {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = SharePreferenceDataSaver.class.getSimpleName();
	public static final String APP_PREFERENCES = "preferences";

	private static SharePreferenceDataSaver sSharePreferenceDataSaver;

	public SharedPreferences settings;
	public SharedPreferences.Editor editor;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private SharePreferenceDataSaver(Context context) {
		settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		editor = settings.edit();
	}

	public static SharePreferenceDataSaver getInstance (Context context) {
		if (sSharePreferenceDataSaver == null) {
			sSharePreferenceDataSaver = new SharePreferenceDataSaver(context);
		}
		return sSharePreferenceDataSaver;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
