package com.binarnahata.rainbowkingdom.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * RainbowKingdom
 * Created on 15.12.15, 12:51
 *
 * @author bat
 * @version 0.1
 */
public class ResourcesFragment extends Fragment implements BackPressedInterface {
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
	private static final String APP_LOCAL_RESOURCES = "local_resources";
	private static final String APP_INIT_PREFERENCES2 = "init_preferences";

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

		JSONObject jsonObject;
		try {
			if (mSettings.contains(APP_LOCAL_RESOURCES)) {
					jsonObject = new JSONObject(mSettings.getString(APP_LOCAL_RESOURCES, getEmptyAmountJSON().toString()));
			}
			else {
				jsonObject = getEmptyAmountJSON();
			}

			TextView local_red = (TextView) view.findViewById(R.id.local_red);
			local_red.setText("Red: " + jsonObject.getInt(APP_LOCAL_RED));
			TextView local_green = (TextView) view.findViewById(R.id.local_green);
			local_green.setText("Green: " + jsonObject.getInt(APP_LOCAL_GREEN));
			TextView local_blue = (TextView) view.findViewById(R.id.local_blue);
			local_blue.setText("Blue: " + jsonObject.getInt(APP_LOCAL_BLUE));
			TextView local_cyan = (TextView) view.findViewById(R.id.local_cyan);
			local_cyan.setText("Cyan: " + jsonObject.getInt(APP_LOCAL_CYAN));
			TextView local_magenta = (TextView) view.findViewById(R.id.local_magenta);
			local_magenta.setText("Magenta: " + jsonObject.getInt(APP_LOCAL_MAGENTA));
			TextView local_yellow = (TextView) view.findViewById(R.id.local_yellow);
			local_yellow.setText("Yellow: " + jsonObject.getInt(APP_LOCAL_YELLOW));
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		TableLayout global = (TableLayout) view.findViewById(R.id.global_table);
		global.setVisibility(View.INVISIBLE);

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	private void preparingSettings() {
		mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
		mEditor = mSettings.edit();
	}

	public static void initSettings(Context context) throws JSONException {
		SharedPreferences settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		SharedPreferences.Editor editor = settings.edit();

		if (!settings.contains(APP_INIT_PREFERENCES2)) {
			if (!settings.getBoolean(APP_INIT_PREFERENCES2, false)) {
				JSONObject jsonObject = getEmptyAmountJSON();
				editor
					.putString(APP_LOCAL_RESOURCES, jsonObject.toString())
					.putBoolean(APP_INIT_PREFERENCES2, true)
					.apply();

			}
		}
	}

	public static void offsetAmounts(Context context, int red, int green, int blue, int cyan, int magenta, int yellow) throws JSONException {
		SharedPreferences settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		SharedPreferences.Editor editor = settings.edit();

		JSONObject jsonObject;
		if (settings.contains(APP_LOCAL_RESOURCES)) {
			jsonObject = new JSONObject(settings.getString(APP_LOCAL_RESOURCES, getEmptyAmountJSON().toString()));
		}
		else {
			jsonObject = getEmptyAmountJSON();
		}

		int r = jsonObject.getInt(APP_LOCAL_RED) + red;
		int g = jsonObject.getInt(APP_LOCAL_GREEN) + green;
		int b = jsonObject.getInt(APP_LOCAL_BLUE) + blue;
		int c = jsonObject.getInt(APP_LOCAL_CYAN) + cyan;
		int m = jsonObject.getInt(APP_LOCAL_MAGENTA) + magenta;
		int y = jsonObject.getInt(APP_LOCAL_YELLOW) + yellow;

		jsonObject
			.put(APP_LOCAL_RED, r)
			.put(APP_LOCAL_GREEN, g)
			.put(APP_LOCAL_BLUE, b)
			.put(APP_LOCAL_CYAN, c)
			.put(APP_LOCAL_MAGENTA, m)
			.put(APP_LOCAL_YELLOW, y);

		editor
			.putString(APP_LOCAL_RESOURCES, jsonObject.toString())
			.apply();
	}

	public static JSONObject getAmount(Context context) throws JSONException {
		SharedPreferences settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);

		return new JSONObject(settings.getString(APP_LOCAL_RESOURCES, getEmptyAmountJSON().toString()));
	}

	public static void offsetAmount(Context context, JSONObject jsonObject) throws JSONException {
		SharedPreferences settings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		SharedPreferences.Editor editor = settings.edit();

		JSONObject jsonResult;
		if (settings.contains(APP_LOCAL_RESOURCES)) {
			jsonResult = new JSONObject(settings.getString(APP_LOCAL_RESOURCES, getEmptyAmountJSON().toString()));
		}
		else {
			jsonResult = getEmptyAmountJSON();
		}

		int r = jsonResult.getInt(APP_LOCAL_RED) - jsonObject.getInt(APP_LOCAL_RED);
		int g = jsonResult.getInt(APP_LOCAL_GREEN) - jsonObject.getInt(APP_LOCAL_GREEN);
		int b = jsonResult.getInt(APP_LOCAL_BLUE) - jsonObject.getInt(APP_LOCAL_BLUE);
		int c = jsonResult.getInt(APP_LOCAL_CYAN) - jsonObject.getInt(APP_LOCAL_CYAN);
		int m = jsonResult.getInt(APP_LOCAL_MAGENTA) - jsonObject.getInt(APP_LOCAL_MAGENTA);
		int y = jsonResult.getInt(APP_LOCAL_YELLOW) - jsonObject.getInt(APP_LOCAL_YELLOW);

		jsonResult
			.put(APP_LOCAL_RED, r)
			.put(APP_LOCAL_GREEN, g)
			.put(APP_LOCAL_BLUE, b)
			.put(APP_LOCAL_CYAN, c)
			.put(APP_LOCAL_MAGENTA, m)
			.put(APP_LOCAL_YELLOW, y);

		editor
			.putString(APP_LOCAL_RESOURCES, jsonResult.toString())
			.apply();
	}

	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}

	private static JSONObject getEmptyAmountJSON() throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject
			.put(APP_LOCAL_RED, 0)
			.put(APP_LOCAL_GREEN, 0)
			.put(APP_LOCAL_BLUE, 0)
			.put(APP_LOCAL_CYAN, 0)
			.put(APP_LOCAL_MAGENTA, 0)
			.put(APP_LOCAL_YELLOW, 0);
		return jsonObject;
	}
	/* МЕТОДЫ */
}
