package com.binarnahata.rainbowkingdom.Models.Resources;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Models.Quest.QuestDataJSON;

import org.json.JSONObject;

/**
 * RainbowKingdom
 * Created on 22.12.15, 20:21
 *
 * @author bat
 * @version 0.1
 */
public class ResourcesData {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = ResourcesData.class.getSimpleName();
	private static ResourcesData sResourcesData;
	private final ResourcesDataJSON mResourcesDateJSON;

	private Context mContext;
	private JSONObject mResources;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private ResourcesData (Context context) {
		mContext = context;
		mResourcesDateJSON = new ResourcesDataJSON(mContext);

		try {
			mResources = mResourcesDateJSON.loadData();
		} catch (Exception e) {
		}
	}

	public static ResourcesData getInstance(Context context) {
		if (sResourcesData == null) {
			sResourcesData = new ResourcesData(context.getApplicationContext());
		}
		return sResourcesData;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
