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

	private static SharePreferenceDataSever mSPDataSever;
	private static Volume sVolume;

	private int mMusicVolume;
	private int mEffectsVolume;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public int getMusicVolume() {
		return mMusicVolume;
	}
	public void setMusicVolume(int musicVolume) {
		mMusicVolume = musicVolume;
	}
	public int getEffectsVolume() {
		return mEffectsVolume;
	}
	public void setEffectsVolume(int effectsVolume) {
		mEffectsVolume = effectsVolume;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private Volume(Context context) {
		mSPDataSever = SharePreferenceDataSever.getInstance(context);
		mMusicVolume = mSPDataSever.settings.getInt(APP_MUSIC, 100);
		mEffectsVolume = mSPDataSever.settings.getInt(APP_EFFECTS, 100);
	}

	public static Volume getInstance (Context context) {
		if (sVolume == null) {
			sVolume = new Volume(context);
		}
		return sVolume;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
