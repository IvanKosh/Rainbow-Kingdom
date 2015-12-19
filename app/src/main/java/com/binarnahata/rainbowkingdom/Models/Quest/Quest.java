package com.binarnahata.rainbowkingdom.Models.Quest;

import com.binarnahata.rainbowkingdom.Utils;

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

	private UUID mId;
	private int mHeroAvatar;
	private String mText;
	private ArrayList<QuestRequest> mQuestRequestList;
	private int mExperince;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public UUID getId () {
		return mId;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Quest() {
		mId = UUID.randomUUID();

		mQuestRequestList = new ArrayList<>();
		for (int i = Utils.rndInt(1, 3); i > 0; i--) {
			mQuestRequestList.add(new QuestRequest());
		}
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