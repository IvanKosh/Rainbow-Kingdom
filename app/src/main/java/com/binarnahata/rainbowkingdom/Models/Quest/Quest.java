package com.binarnahata.rainbowkingdom.Models.Quest;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.Utils;

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
	private static final String JSON_ID = "id";
	private static final String JSON_HERO_AVATAR = "hero_avatar";
	private static final String JSON_TEXT = "text";
	private static final String JSON_EXPERIENCE = "experience";
	private Context mContext;

	private UUID mId;
	private String mHeroAvatar;
	private String mText;
	private ArrayList<QuestRequest> mQuestRequestList;
	private int mExperience;
	private boolean mStatus;
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
	public String getStringQuestRequestList() {
		String result = new String();
		for (QuestRequest questRequest : mQuestRequestList) {
			result += questRequest.toString();
		}
		return result;
	}

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Quest(Context context) {
		mContext = context;
		mId = UUID.randomUUID();
		mHeroAvatar = Utils.rndHeroAvatar();
		mText = "Quest text";
		mQuestRequestList = new ArrayList<>();

		ArrayList<Integer> colorPool = Utils.getColorPool();
		Collections.shuffle(colorPool);
		for (int i = Utils.rndInt(1, QUEST_RATION); i > 0; i--) {
			mQuestRequestList.add(new QuestRequest(colorPool.get(i)));
		}

		mExperience = 0;
		for (QuestRequest questRequest : mQuestRequestList) {
			mExperience += questRequest.amount;
		}
		mExperience *= QUEST_RATION - mQuestRequestList.size();
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
	/* МЕТОДЫ */
}