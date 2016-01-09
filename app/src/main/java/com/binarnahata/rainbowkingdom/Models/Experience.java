package com.binarnahata.rainbowkingdom.Models;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.DataSaver.SharePreferenceDataSaver;

/**
 * RainbowKingdom
 * Created on 25.12.15, 19:36
 *
 * @author bat
 * @version 0.1
 */
public class Experience {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Experience.class.getSimpleName();
	private static final String APP_LEVEL = "level";
	private static final String APP_POINT = "point";
	private final SharePreferenceDataSaver mSPDataSever;

	private static Experience sExperience;

	private int level;
	private int point;
	private String result;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private Experience(Context context) {
		mSPDataSever = SharePreferenceDataSaver.getInstance(context);
		level = mSPDataSever.settings.getInt(APP_LEVEL, 0);
		point = mSPDataSever.settings.getInt(APP_POINT, 0);
	}

	public static Experience getInstance(Context context) {
		if (sExperience == null) {
			sExperience = new Experience(context);
		}
		return sExperience;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public int getProgress() {
		return (int) (point * 100 / (Math.sqrt(level) + 30));
	}

	public void saveData() {
		mSPDataSever.editor
			.putInt(APP_LEVEL, level)
			.putInt(APP_POINT, point)
			.apply();
	}

	public void initData() {
		if (!mSPDataSever.settings.contains(APP_LEVEL) || !mSPDataSever.settings.contains(APP_POINT)) {
			mSPDataSever.editor
				.putInt(APP_LEVEL, 0)
				.putInt(APP_POINT, 0)
				.apply();
		}
	}

	public void offsetPoint(int experience) {
		point += experience;
		int top = (int) (Math.sqrt(level) + 30);
		while (top <= point) {
			level++;
			point -= top;
		}
	}

	public String getLevelString() {
		return "Level: " + level + " (" + point + "/" + (int) (Math.sqrt(level) + 30) + ")";
	}


	/* МЕТОДЫ */
}