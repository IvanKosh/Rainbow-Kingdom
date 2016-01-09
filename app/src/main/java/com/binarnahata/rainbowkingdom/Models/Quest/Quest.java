package com.binarnahata.rainbowkingdom.Models.Quest;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.Utils;
import com.binarnahata.rainbowkingdom.Models.Resources.Resources;
import com.binarnahata.rainbowkingdom.R;

import org.json.JSONException;
import org.json.JSONObject;

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
	private static final String JSON_REQUEST = "request";
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

	public String getExperienceString() {
		return String.format(mContext.getResources().getString(R.string.reward), mExperience);
	}

	public JSONObject getJSONRequest() {
		return mJSONRequest;
	}

	public String getJSONRequestString() {
		String result = new String();
		result += mContext.getResources().getString(R.string.request);
		try {
			int r = mJSONRequest.getInt(Resources.RED);
			int g = mJSONRequest.getInt(Resources.GREEN);
			int b = mJSONRequest.getInt(Resources.BLUE);
			int c = mJSONRequest.getInt(Resources.CYAN);
			int m = mJSONRequest.getInt(Resources.MAGENTA);
			int y = mJSONRequest.getInt(Resources.YELLOW);

			if (r != 0) {
				result += String.format(mContext.getResources().getString(R.string.red), r);
			}
			if (g != 0) {
				result += String.format(mContext.getResources().getString(R.string.green), g);
			}
			if (b != 0) {
				result += String.format(mContext.getResources().getString(R.string.blue), b);
			}
			if (c != 0) {
				result += String.format(mContext.getResources().getString(R.string.cyan), c);
			}
			if (m != 0) {
				result += String.format(mContext.getResources().getString(R.string.magenta), m);
			}
			if (y
				!= 0) {
				result += String.format(mContext.getResources().getString(R.string.yellow), y);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Quest(Context context) {
		mContext = context;
		mId = UUID.randomUUID();
		mHeroAvatar = Utils.rndHeroAvatar();
		mText = rndQuestText(context);

		mJSONRequest = Resources.getRandom(Utils.rndInt(1, QUEST_RATION));

		mExperience = Utils.rndInt(4 * QUEST_RATION, 12 * QUEST_RATION);
	}

	public Quest(JSONObject jsonObject) throws JSONException { // NEVER delete
		mId = UUID.fromString(jsonObject.getString(JSON_ID));
		mHeroAvatar = (String) jsonObject.get(JSON_HERO_AVATAR);
		mText = (String) jsonObject.get(JSON_TEXT);
		mJSONRequest = new JSONObject(jsonObject.getString(JSON_REQUEST));
		mExperience = (int) jsonObject.get(JSON_EXPERIENCE);
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	private String rndQuestText(Context context) {
		return context.getResources().getString(
			mContext.getResources().getIdentifier(
				"quest_text" + Utils.rndInt(0, 9), "string",
				mContext.getPackageName()));
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_HERO_AVATAR, mHeroAvatar);
		json.put(JSON_TEXT, mText);
		json.put(JSON_REQUEST, mJSONRequest.toString());
		json.put(JSON_EXPERIENCE, mExperience);
		return json;
	}

	public boolean canComplete(JSONObject jsonObjectS) {
		if (jsonObjectS == null) {
			return false;
		}

		try {
			if (jsonObjectS.getInt(Resources.RED) < mJSONRequest.getInt(Resources.RED) ||
				jsonObjectS.getInt(Resources.GREEN) < mJSONRequest.getInt(Resources.GREEN) ||
				jsonObjectS.getInt(Resources.BLUE) < mJSONRequest.getInt(Resources.BLUE) ||
				jsonObjectS.getInt(Resources.CYAN) < mJSONRequest.getInt(Resources.CYAN) ||
				jsonObjectS.getInt(Resources.MAGENTA) < mJSONRequest.getInt(Resources.MAGENTA) ||
				jsonObjectS.getInt(Resources.YELLOW) < mJSONRequest.getInt(Resources.YELLOW)) {
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return true;
	}
	/* МЕТОДЫ */
}