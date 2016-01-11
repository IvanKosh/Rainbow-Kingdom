package com.binarnahata.rainbowkingdom.Controllers;

import android.content.Context;
import android.os.AsyncTask;

import com.binarnahata.rainbowkingdom.Adapters.AchievementAdapter;
import com.binarnahata.rainbowkingdom.Libs.DataBase.AchievementDatabaseHandler;
import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 27.12.15, 11:35
 *
 * @author bat
 * @version 0.1
 */
public class loadAchievements extends AsyncTask<Void, ArrayList<Achievement>, Void> {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = loadAchievements.class.getSimpleName();
	private final AchievementDatabaseHandler mDB;
	private final AchievementAdapter mAdapter;

	private int mSkip;
	private int mCount;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public loadAchievements(Context context, AchievementAdapter adapter) {
		mDB = AchievementDatabaseHandler.getInstance(context);//new AchievementDatabaseHandler(context);
		mSkip = 0;
		mCount = 10;
		mAdapter = adapter;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	protected Void doInBackground(Void... params) {
		ArrayList<Achievement> array;
		do {
			array = mDB.getLimitAchievement(mSkip, mCount);
			mSkip += mCount;
			publishProgress(array);
		} while (array.size() != 0);
		return null;
	}

	@Override
	protected void onProgressUpdate(ArrayList<Achievement>... values) {
		super.onProgressUpdate(values);
		mAdapter.addAchievementsArrayList(values);
		mAdapter.notifyDataSetChanged();
	}
	/* МЕТОДЫ */
}