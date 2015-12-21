package com.binarnahata.rainbowkingdom.Models.Quest;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.Utils;
import com.binarnahata.rainbowkingdom.Models.Components.Color;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

/**
 * RainbowKingdom
 * Created on 18.12.15, 16:52
 *
 * @author bat
 * @version 0.1
 */
public class Quest {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Quest.class.getSimpleName();
	public static final int MAX_QUEST_REQUESTING = 3;
	public static final int QUEST_RATION = MAX_QUEST_REQUESTING + 1;
	public static final String APP_LOCAL_RED = "local_red";
	public static final String APP_LOCAL_GREEN = "local_green";
	public static final String APP_LOCAL_BLUE = "local_blue";
	public static final String APP_LOCAL_CYAN = "local_cyan";
	public static final String APP_LOCAL_MAGENTA = "local_magenta";
	public static final String APP_LOCAL_YELLOW = "local_yellow";
	private static final String JSON_ID = "id";
	private static final String JSON_HERO_AVATAR = "hero_avatar";
	private static final String JSON_TEXT = "text";
	private static final String JSON_EXPERIENCE = "experience";
	private Context mContext;

	private UUID mId;
	private String mHeroAvatar;
	private String mText;
	private JSONObject mJSONRequest;
	private int mExperience;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public UUID getId() {
		return mId;
	}
	public int getHeroAvatar() {
		return mContext.getResources().getIdentifier(mHeroAvatar, "drawable", mContext.getPackageName());
	}
	public String getText() {
		return mText;
	}
	public int getExperience() {
		return mExperience;
	}

	public JSONObject getJSONRequest() {
		return mJSONRequest;
	}

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Quest(Context context) {
		mContext = context;
		mId = UUID.randomUUID();
		mHeroAvatar = Utils.rndHeroAvatar();
		mText = "Quest text";

		/*ArrayList<Integer> colorPool = Utils.getColorPool();
		Collections.shuffle(colorPool);
		for (int i = Utils.rndInt(1, QUEST_RATION); i > 0; i--) {
			mQuestRequestList.add(new QuestRequest(colorPool.get(i)));
		}*/

		ArrayList<String> colorPool = getColorPool();
		Collections.shuffle(colorPool);
		mJSONRequest = new JSONObject();
		int amount = 0;
		int r;
		int number = Utils.rndInt(1, QUEST_RATION);
		try {
			mJSONRequest
				.put(APP_LOCAL_RED, 0)
				.put(APP_LOCAL_GREEN, 0)
				.put(APP_LOCAL_BLUE, 0)
				.put(APP_LOCAL_CYAN, 0)
				.put(APP_LOCAL_MAGENTA, 0)
				.put(APP_LOCAL_YELLOW, 0);
			for (int i = number; i > 0; i--) {
				r = Utils.rndInt(5, 10);
				amount += r;
				mJSONRequest
					.put(colorPool.get(i), r);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		mExperience = amount;
		mExperience *= QUEST_RATION - number;
	}

	public Quest(JSONObject jsonObject) throws JSONException{
		mId = UUID.fromString(jsonObject.getString(JSON_ID));
		mHeroAvatar = (String) jsonObject.get(JSON_HERO_AVATAR);
		mText = (String) jsonObject.get(JSON_TEXT);
		mExperience = (int) jsonObject.get(JSON_EXPERIENCE);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_HERO_AVATAR, mHeroAvatar);
		json.put(JSON_TEXT, mText);
		json.put(JSON_EXPERIENCE, mExperience);
		return json;
	}

	public boolean canComplete(JSONObject jsonObjectS) {
		return false;
	}

	private ArrayList<String> getColorPool() {
		return new ArrayList<String>(){{
			add(APP_LOCAL_RED); add(APP_LOCAL_GREEN); add(APP_LOCAL_BLUE);
			add(APP_LOCAL_CYAN); add(APP_LOCAL_MAGENTA); add(APP_LOCAL_YELLOW);
		}};
	}
	/* МЕТОДЫ */
}