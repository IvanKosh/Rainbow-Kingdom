package com.binarnahata.rainbowkingdom.Models.Resources;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.DataSaver.SharePreferenceDataSaver;
import com.binarnahata.rainbowkingdom.Libs.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * RainbowKingdom
 * Created on 24.12.15, 9:46
 *
 * @author bat
 * @version 0.1
 */
public class Resources {
	public static final String RED = "red";
	public static final String GREEN = "green";
	public static final String BLUE = "blue";
	public static final String CYAN = "cyan";
	public static final String MAGENTA = "magenta";
	public static final String YELLOW = "yellow";
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Resources.class.getSimpleName();
	private static final String APP_INIT_PREFERENCES = "init_preferences";
	private static final String APP_LOCAL_RESOURCES = "local_resources";
	private static SharePreferenceDataSaver mSPDataSaver;
	private static Resources sResources;

	private JSONObject mResources;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private Resources(Context context) {
		mSPDataSaver = SharePreferenceDataSaver.getInstance(context);
		try {
			mResources = new JSONObject(mSPDataSaver.settings.getString(APP_LOCAL_RESOURCES, getEmpty().toString()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static Resources getInstance(Context context) {
		if (sResources == null) {
			sResources = new Resources(context);
		}
		return sResources;
	}

	private static JSONObject getEmpty() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject
				.put(RED, 0)
				.put(GREEN, 0)
				.put(BLUE, 0)
				.put(CYAN, 0)
				.put(MAGENTA, 0)
				.put(YELLOW, 0);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static JSONObject getRandom(int colorNumber) {
		ArrayList<String> colorPool = getColorPool();
		Collections.shuffle(colorPool);
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject
				.put(RED, 0)
				.put(GREEN, 0)
				.put(BLUE, 0)
				.put(CYAN, 0)
				.put(MAGENTA, 0)
				.put(YELLOW, 0);
			int r;
			for (int i = 0; i < colorNumber; i++) {
				r = Utils.randomInt(3, 10);
				jsonObject
					.put(colorPool.get(i), r);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	private static ArrayList<String> getColorPool() {
		return new ArrayList<String>() {{
			add(RED);
			add(GREEN);
			add(BLUE);
			add(CYAN);
			add(MAGENTA);
			add(YELLOW);
		}};
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public JSONObject getResources() {
		return mResources;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void initData() {
		if (!mSPDataSaver.settings.contains(APP_INIT_PREFERENCES)) {
			if (!mSPDataSaver.settings.getBoolean(APP_INIT_PREFERENCES, false)) {
				mResources = getEmpty();
				mSPDataSaver.editor
					.putString(APP_LOCAL_RESOURCES, mResources.toString())
					.putBoolean(APP_INIT_PREFERENCES, true)
					.apply();

			}
		}
	}

	public void saveData() {
		mSPDataSaver.editor
			.putString(APP_LOCAL_RESOURCES, mResources.toString())
			.apply();
	}

	public void offset(JSONObject deltaJSON) { // + delta
		try {
			int r = mResources.getInt(RED) + deltaJSON.getInt(RED);
			int g = mResources.getInt(GREEN) + deltaJSON.getInt(GREEN);
			int b = mResources.getInt(BLUE) + deltaJSON.getInt(BLUE);
			int c = mResources.getInt(CYAN) + deltaJSON.getInt(CYAN);
			int m = mResources.getInt(MAGENTA) + deltaJSON.getInt(MAGENTA);
			int y = mResources.getInt(YELLOW) + deltaJSON.getInt(YELLOW);

			mResources
				.put(RED, r)
				.put(GREEN, g)
				.put(BLUE, b)
				.put(CYAN, c)
				.put(MAGENTA, m)
				.put(YELLOW, y);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void offset(int red, int green, int blue, int cyan, int magenta, int yellow) { // + delta
		try {
			int r = mResources.getInt(RED) + red;
			int g = mResources.getInt(GREEN) + green;
			int b = mResources.getInt(BLUE) + blue;
			int c = mResources.getInt(CYAN) + cyan;
			int m = mResources.getInt(MAGENTA) + magenta;
			int y = mResources.getInt(YELLOW) + yellow;

			mResources
				.put(RED, r)
				.put(GREEN, g)
				.put(BLUE, b)
				.put(CYAN, c)
				.put(MAGENTA, m)
				.put(YELLOW, y);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void approve(JSONObject deltaJSON) { // - delta
		try {
			int r = mResources.getInt(RED) - deltaJSON.getInt(RED);
			int g = mResources.getInt(GREEN) - deltaJSON.getInt(GREEN);
			int b = mResources.getInt(BLUE) - deltaJSON.getInt(BLUE);
			int c = mResources.getInt(CYAN) - deltaJSON.getInt(CYAN);
			int m = mResources.getInt(MAGENTA) - deltaJSON.getInt(MAGENTA);
			int y = mResources.getInt(YELLOW) - deltaJSON.getInt(YELLOW);

			mResources
				.put(RED, r)
				.put(GREEN, g)
				.put(BLUE, b)
				.put(CYAN, c)
				.put(MAGENTA, m)
				.put(YELLOW, y);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/* МЕТОДЫ */
}