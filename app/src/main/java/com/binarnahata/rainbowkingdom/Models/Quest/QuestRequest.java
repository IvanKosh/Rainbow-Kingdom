package com.binarnahata.rainbowkingdom.Models.Quest;

import com.binarnahata.rainbowkingdom.Libs.Utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * RainbowKingdom
 * Created on 18.12.15, 18:16
 *
 * @author bat
 * @version 0.1
 */
public class QuestRequest {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = QuestRequest.class.getSimpleName();
	private static final String JSON_COLOR = "color";
	private static final String JSON_AMOUNT = "amount";

	public int color;
	public int amount;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public QuestRequest() {
		color = Utils.rndColor();
		amount = Utils.rndInt(5, 10);
	}

	public QuestRequest(JSONObject jsonObject) throws JSONException {
		color = jsonObject.getInt(JSON_COLOR);
		amount = jsonObject.getInt(JSON_AMOUNT);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_COLOR, color);
		json.put(JSON_AMOUNT, amount);
		return json;
	}
	/* МЕТОДЫ */
}
