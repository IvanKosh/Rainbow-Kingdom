package com.binarnahata.rainbowkingdom.Models;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.DataSaver.SharePreferenceDataSaver;

/**
 * RainbowKingdom
 * Created on 29.12.15, 10:03
 *
 * @author bat
 * @version 0.1
 */
public class Volume {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Volume.class.getSimpleName();
	private static final String APP_MUSIC = "music";
	private static final String APP_EFFECTS = "effects";

	private static SharePreferenceDataSaver sSPDataSever;
	private static Volume sVolume;

	private float mMusicVolume;
	private float mEffectsVolume;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private Volume(Context context) {
		sSPDataSever = SharePreferenceDataSaver.getInstance(context);
		mMusicVolume = sSPDataSever.settings.getFloat(APP_MUSIC, 1f);
		mEffectsVolume = sSPDataSever.settings.getFloat(APP_EFFECTS, 1f);
	}

	public static Volume getInstance(Context context) {
		if (sVolume == null) {
			sVolume = new Volume(context);
		}
		return sVolume;
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public float getMusicVolume() {
		return mMusicVolume;
	}

	public void setMusicVolume(float musicVolume) {
		mMusicVolume = musicVolume;
	}

	public float getEffectsVolume() {
		return mEffectsVolume;
	}

	public void setEffectsVolume(float effectsVolume) {
		mEffectsVolume = effectsVolume;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void saveData() {
		sSPDataSever.editor
			.putFloat(APP_MUSIC, mMusicVolume)
			.putFloat(APP_EFFECTS, mEffectsVolume)
			.apply();
	}
	/* МЕТОДЫ */
}
