package com.binarnahata.rainbowkingdom.Libs.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;
import com.binarnahata.rainbowkingdom.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	public static final String SQL_GET_ACHIEVEMENTS = "SELECT * FROM " + TABLE_ACHIEVEMENT;
	private final Context mContext;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public AchievementDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		mContext = context;
	}
	public AchievementDatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
		mContext = context;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ACHIEVEMENT + "("
			+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_ICON + " TEXT,"
			+ KEY_TEXT + " TEXT," + KEY_NUMBER + " INTEGER," + KEY_POINT + " INTEGER" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);

		initAchievements(db, R.raw.achievements);
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
	public int updateAchievement(Achievement achievement) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ICON, achievement.getIcon());
		values.put(KEY_TEXT, achievement.getText());
		values.put(KEY_NUMBER, achievement.getNumber());
		values.put(KEY_POINT, achievement.getPoint());

		// updating row
		return db.update(TABLE_ACHIEVEMENT, values, KEY_ID + " = ?",
			new String[]{String.valueOf(achievement.getId())});
	}

	public ArrayList<Achievement> getLimitAchievement(int skip, int count) {
		String selectQuery = SQL_GET_ACHIEVEMENTS + " LIMIT " + count + " OFFSET " + skip;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		ArrayList<Achievement> achievementArrayList = new ArrayList<>();
		if (cursor.moveToFirst()) {
			do {
				Achievement achievement = new Achievement();
				achievement.setId(cursor.getInt(0));
				achievement.setIcon(cursor.getString(1));
				achievement.setText(cursor.getString(2));
				achievement.setNumber(cursor.getInt(3));
				achievement.setPoint(cursor.getInt(4));

				achievementArrayList.add(achievement);
			} while (cursor.moveToNext());
		}

		db.close();
		return achievementArrayList;
	}

	public void addAchievement(Achievement achievement) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ICON, achievement.getIcon());
		values.put(KEY_TEXT, achievement.getText());
		values.put(KEY_NUMBER, achievement.getNumber());
		values.put(KEY_POINT, achievement.getPoint());

		db.insert(TABLE_ACHIEVEMENT, null, values);
		db.close();
	}

	private void initAchievements(SQLiteDatabase db, int resourceId) {
		db.beginTransaction();

		InputStream insertsStream = mContext.getResources().openRawResource(resourceId);
		BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

		String line;
		StringBuilder jsonString = new StringBuilder();
		try {
			while((line = insertReader.readLine()) != null) {
				jsonString.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONArray array = null;
		JSONObject oj;
		try {
			final ContentValues contentValues = new ContentValues();
			array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for (int i = 0; i < array.length(); i++) {
				Log.d(TAG, String.valueOf(array.getJSONObject(i)));
				oj = array.getJSONObject(i);
				contentValues.put(KEY_ICON, oj.getString(KEY_ICON));
				contentValues.put(KEY_TEXT, oj.getString(KEY_TEXT));
				contentValues.put(KEY_NUMBER, oj.getInt(KEY_NUMBER));
				contentValues.put(KEY_POINT, oj.getInt(KEY_POINT));
				db.insert(TABLE_ACHIEVEMENT, null, contentValues);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}

		db.setTransactionSuccessful();
		db.endTransaction();
	}
	/* МЕТОДЫ */
}