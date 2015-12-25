package com.binarnahata.rainbowkingdom.Libs.DataSaver;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * RainbowKingdom
 * Created on 23.12.15, 11:38
 *
 * @author bat
 * @version 0.1
 */
public class SharePreferenceDataSever {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = SharePreferenceDataSever.class.getSimpleName();
	public static final String APP_PREFERENCES = "preferences";

	private static SharePreferenceDataSever sSharePreferenceDataSever;

	public SharedPreferences settings;
	public SharedPreferences.Editor editor;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private SharePreferenceDataSever(Context context) {
		settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		editor = settings.edit();
	}

	public static SharePreferenceDataSever getInstance (Context context) {
		if (sSharePreferenceDataSever == null) {
			sSharePreferenceDataSever = new SharePreferenceDataSever(context);
		}
		return sSharePreferenceDataSever;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
