package com.binarnahata.rainbowkingdom.Models.Quest;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * RainbowKingdom
 * Created on 18.12.15, 16:52
 *
 * @author bat
 * @version 0.1
 */
public class QuestData {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = QuestData.class.getSimpleName();
	private static final String FILENAME = "data.json";

	private QuestDataJSON mQuestDateJSON;

	private static QuestData sQuestData;
	private Context mContext;

	private ArrayList<Quest> mQuestArrayList;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public ArrayList<Quest> getQuestArrayList (){
		return mQuestArrayList;
	}

	public Quest getQuest(UUID id) {
		for (Quest quest : mQuestArrayList) {
			if(quest.getId().equals(id)){
				return quest;
			}
		}

		return null;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private QuestData (Context context) {
		mContext = context;
		mQuestDateJSON = new QuestDataJSON(mContext, FILENAME);

		try {
			mQuestArrayList = mQuestDateJSON.loadData();
		} catch (Exception e) {
		}
	}

	public static QuestData getInstance(Context context) {
		if (sQuestData == null) {
			sQuestData = new QuestData(context.getApplicationContext());
		}
		return sQuestData;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public boolean saveData() {
		try {
			mQuestDateJSON.saveData(mQuestArrayList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void saveData(ArrayList<Quest> list) {
		mQuestArrayList = list;
		saveData();
	}
	/* МЕТОДЫ */
}