package com.binarnahata.rainbowkingdom.Models;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.DataSaver.SharePreferenceDataSever;

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

	private static SharePreferenceDataSever sSPDataSever;
	private static Volume sVolume;

	private float mMusicVolume;
	private float mEffectsVolume;
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
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private Volume(Context context) {
		sSPDataSever = SharePreferenceDataSever.getInstance(context);
		mMusicVolume = sSPDataSever.settings.getFloat(APP_MUSIC, 1f);
		mEffectsVolume = sSPDataSever.settings.getFloat(APP_EFFECTS, 1f);
	}

	public static Volume getInstance (Context context) {
		if (sVolume == null) {
			sVolume = new Volume(context);
		}
		return sVolume;
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
