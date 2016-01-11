package com.binarnahata.rainbowkingdom.Models.Quest;

import android.content.Context;

import com.binarnahata.rainbowkingdom.Libs.DataSaver.FileDataSaver;

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
	private static final String FILENAME = "quests.json";
	private static QuestData sQuestData;
	private FileDataSaver mQuestDataSaver;
	private Context mContext;

	private ArrayList<Quest> mQuestArrayList;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	private QuestData(Context context) {
		mContext = context;
		mQuestDataSaver = new FileDataSaver(mContext, FILENAME);

		try {
			mQuestArrayList = mQuestDataSaver.loadData(Quest.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static QuestData getInstance(Context context) {
		if (sQuestData == null) {
			sQuestData = new QuestData(context.getApplicationContext());
		}
		return sQuestData;
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public ArrayList<Quest> getQuestArrayList() {
		return mQuestArrayList;
	}

	public Quest getQuest(UUID id) {
		for (Quest quest : mQuestArrayList) {
			if (quest.getId().equals(id)) {
				return quest;
			}
		}

		return null;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public boolean saveData() {
		try {
			mQuestDataSaver.saveData(mQuestArrayList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void saveData(ArrayList<Quest> list) {
		mQuestArrayList = list;
		saveData();
	}
	/* МЕТОДЫ */
}