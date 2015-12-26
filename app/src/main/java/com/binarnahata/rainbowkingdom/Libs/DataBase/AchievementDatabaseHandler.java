package com.binarnahata.rainbowkingdom.Libs.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 26.12.15, 11:07
 *
 * @author bat
 * @version 0.1
 */
public class AchievementDatabaseHandler extends SQLiteOpenHelper {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = AchievementDatabaseHandler.class.getSimpleName();
	private static final String DATABASE_NAME = "RainbowKingdom";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_ACHIEVEMENT = "achievements";
	private static final String KEY_ID = "id";
	private static final String KEY_ICON = "icon";
	private static final String KEY_TEXT = "text";
	private static final String KEY_NUMBER = "number";
	private static final String KEY_POINT = "point";
	public static final String SQL_GET_ACHIEVEMENTS = "SELECT " + KEY_ICON + "," + KEY_TEXT + "," + KEY_NUMBER + "," + KEY_POINT + " FROM " + TABLE_ACHIEVEMENT;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public AchievementDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public AchievementDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ACHIEVEMENT + "("
			+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_ICON + " TEXT,"
			+ KEY_TEXT + " TEXT," + KEY_NUMBER + " INTEGER," + KEY_POINT + " INTEGER" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENT);

		// Create tables again
		onCreate(db);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void updateAchievement(Achievement achievement) {

	}

	public ArrayList<Achievement> getLimitAchievement(int skip, int count) {
		ArrayList<Achievement> achievementArrayList = new ArrayList<>();
		// Select All Query
		String selectQuery = SQL_GET_ACHIEVEMENTS + "LIMIT " + skip + ", " + count;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Achievement achievement = new Achievement();
				achievement.setIcon(cursor.getString(0));
				achievement.setText(cursor.getString(1));
				achievement.setNumber(cursor.getInt(2));
				achievement.setPoint(cursor.getInt(3));
				// Adding contact to list
				achievementArrayList.add(achievement);
			} while (cursor.moveToNext());
		}

		// return contact list
		return achievementArrayList;
	}
	/* МЕТОДЫ */
}