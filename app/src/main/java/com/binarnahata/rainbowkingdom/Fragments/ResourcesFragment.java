package com.binarnahata.rainbowkingdom.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Libs.DataSaver.SharePreferenceDataSever;
import com.binarnahata.rainbowkingdom.Models.Resources.Resources;
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
	public static final String APP_LOCAL_RED = "Red";
	public static final String APP_LOCAL_GREEN = "local_green";
	public static final String APP_LOCAL_BLUE = "local_blue";
	public static final String APP_LOCAL_CYAN = "local_cyan";
	public static final String APP_LOCAL_MAGENTA = "local_magenta";
	public static final String APP_LOCAL_YELLOW = "local_yellow";
	private static final String APP_LOCAL_RESOURCES = "local_resources";
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public ResourcesFragment() {
	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_resources, container, false);

		TextView local_red = (TextView) view.findViewById(R.id.local_red);
		TextView local_green = (TextView) view.findViewById(R.id.local_green);
		TextView local_blue = (TextView) view.findViewById(R.id.local_blue);
		TextView local_cyan = (TextView) view.findViewById(R.id.local_cyan);
		TextView local_magenta = (TextView) view.findViewById(R.id.local_magenta);
		TextView local_yellow = (TextView) view.findViewById(R.id.local_yellow);

		JSONObject jsonObject;
		jsonObject = Resources.getInstance(getContext()).getResources();

		try {
			local_red.setText("Red: " + jsonObject.getInt(Resources.RED));
			local_green.setText("Green: " + jsonObject.getInt(Resources.GREEN));
			local_blue.setText("Blue: " + jsonObject.getInt(Resources.BLUE));
			local_cyan.setText("Cyan: " + jsonObject.getInt(Resources.CYAN));
			local_magenta.setText("Magenta: " + jsonObject.getInt(Resources.MAGENTA));
			local_yellow.setText("Yellow: " + jsonObject.getInt(Resources.YELLOW));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		ProgressBar globalProgressBar = (ProgressBar) view.findViewById(R.id.global_progress_bar);
		globalProgressBar.setVisibility(View.INVISIBLE);

		TableLayout global = (TableLayout) view.findViewById(R.id.global_table);
		global.setVisibility(View.INVISIBLE);

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */

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
