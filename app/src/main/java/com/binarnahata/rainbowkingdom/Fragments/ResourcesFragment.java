package com.binarnahata.rainbowkingdom.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 15.12.15, 12:51
 *
 * @author bat
 * @version 0.1
 */
public class ResourcesFragment extends Fragment {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = ResourcesFragment.class.getSimpleName();
	public static final String APP_PREFERENCES = "resources";
	public static final String APP_INIT_PREFERENCES = "init_pref";
	public static final String APP_LOCAL_RED = "local_red";
	public static final String APP_LOCAL_GREEN = "local_green";
	public static final String APP_LOCAL_BLUE = "local_blue";
	public static final String APP_LOCAL_CYAN = "local_cyan";
	public static final String APP_LOCAL_MAGENTA = "local_magenta";
	public static final String APP_LOCAL_YELLOW = "local_yellow";

	private SharedPreferences mSettings;
	private SharedPreferences.Editor mEditor;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public ResourcesFragment() {
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		preparingSettings();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_resources, container, false);

		if (mSettings.contains(APP_LOCAL_RED)) {
			int check = mSettings.getInt(APP_LOCAL_RED, 0);
			TextView local_red = (TextView) view.findViewById(R.id.local_red);
				local_red.setText("Red: " + check);
		}

		if (mSettings.contains(APP_LOCAL_GREEN)) {
			int check = mSettings.getInt(APP_LOCAL_GREEN, 0);
			TextView local_red = (TextView) view.findViewById(R.id.local_green);
				local_red.setText("Green: " + check);
		}

		if (mSettings.contains(APP_LOCAL_BLUE)) {
			int check = mSettings.getInt(APP_LOCAL_BLUE, 0);
			TextView local_red = (TextView) view.findViewById(R.id.local_blue);
				local_red.setText("Blue: " + check);
		}

		if (mSettings.contains(APP_LOCAL_CYAN)) {
			int check = mSettings.getInt(APP_LOCAL_CYAN, 0);
			TextView local_red = (TextView) view.findViewById(R.id.local_cyan);
				local_red.setText("Cyan: " + check);
		}

		if (mSettings.contains(APP_LOCAL_MAGENTA)) {
			int check = mSettings.getInt(APP_LOCAL_MAGENTA, 0);
			TextView local_red = (TextView) view.findViewById(R.id.local_magenta);
				local_red.setText("Magenta: " + check);
		}

		if (mSettings.contains(APP_LOCAL_YELLOW)) {
			int check = mSettings.getInt(APP_LOCAL_YELLOW, 0);
			TextView local_red = (TextView) view.findViewById(R.id.local_yellow);
				local_red.setText("Yellow: " + check);
		}

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	private void preparingSettings() {
		mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		mEditor = mSettings.edit();
	}

	public static void initSettings(Context context) {
		SharedPreferences settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		SharedPreferences.Editor editor = settings.edit();

		if (!settings.contains(APP_INIT_PREFERENCES)) {
			if (!settings.getBoolean(APP_INIT_PREFERENCES, false)) {
				Log.d(TAG, "preferences init");
				editor
					.putInt(APP_LOCAL_RED, 0)
					.putInt(APP_LOCAL_GREEN, 0)
					.putInt(APP_LOCAL_BLUE, 0)
					.putInt(APP_LOCAL_CYAN, 0)
					.putInt(APP_LOCAL_MAGENTA, 0)
					.putInt(APP_LOCAL_YELLOW, 0)
					.apply();
			}
		}
		else {
			Log.e(TAG, "Нет данных");
		}
	}

	public static void offsetAmounts(Context context, int red, int green, int blue, int cyan, int magenta, int yellow) {
		SharedPreferences settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		SharedPreferences.Editor editor = settings.edit();

		int r = settings.getInt(APP_LOCAL_RED, 0) + red;
		int g = settings.getInt(APP_LOCAL_GREEN, 0) + green;
		int b = settings.getInt(APP_LOCAL_BLUE, 0) + blue;
		int c = settings.getInt(APP_LOCAL_CYAN, 0) + cyan;
		int m = settings.getInt(APP_LOCAL_MAGENTA, 0) + magenta;
		int y = settings.getInt(APP_LOCAL_YELLOW, 0) + yellow;
		editor
			.putInt(APP_LOCAL_RED, r)
			.putInt(APP_LOCAL_GREEN, g)
			.putInt(APP_LOCAL_BLUE, b)
			.putInt(APP_LOCAL_CYAN, c)
			.putInt(APP_LOCAL_MAGENTA, m)
			.putInt(APP_LOCAL_YELLOW, y)
			.apply();

	}
	/* МЕТОДЫ */
}
