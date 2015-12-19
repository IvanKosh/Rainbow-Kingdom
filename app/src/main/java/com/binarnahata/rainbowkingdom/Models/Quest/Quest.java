package com.binarnahata.rainbowkingdom.Models.Quest;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
	private static final String JSON_ID = "id";
	public static final int MAX_QUEST_REQUESTING = 3;
	public static final int QUEST_RATION = MAX_QUEST_REQUESTING - 1;

	private UUID mId;
	private int mHeroAvatar;
	private String mText;
	private ArrayList<QuestRequest> mQuestRequestList;
	private int mExperience;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public UUID getId() {
		return mId;
	}
	public int getHeroAvatar() {
		return mHeroAvatar;
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
		mId = UUID.randomUUID();
		mHeroAvatar = Utils.rndHeroAvatar(context);
		mText = "Quest text";
		mQuestRequestList = new ArrayList<>();
		for (int i = Utils.rndInt(1, MAX_QUEST_REQUESTING); i > 0; i--) {
			mQuestRequestList.add(new QuestRequest());
		}

		mExperience = 0;
		for (QuestRequest questRequest : mQuestRequestList) {
			mExperience += questRequest.amount;
		}
		mExperience *= QUEST_RATION - mQuestRequestList.size();
	}

	public Quest(JSONObject jsonObject) throws JSONException{
		mId = UUID.fromString(jsonObject.getString(JSON_ID));
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		return json;
	}
	/* МЕТОДЫ */
}