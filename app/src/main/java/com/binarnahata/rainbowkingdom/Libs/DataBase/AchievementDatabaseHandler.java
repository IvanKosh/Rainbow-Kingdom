package com.binarnahata.rainbowkingdom.Libs.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;
import com.binarnahata.rainbowkingdom.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

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

		if (true) {
			initData(db, R.raw.achievement);
		}
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
		String selectQuery = SQL_GET_ACHIEVEMENTS + " LIMIT " + count + " OFFSET " + skip;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		ArrayList<Achievement> achievementArrayList = new ArrayList<>();
		if (cursor.moveToFirst()) {
			do {
				Achievement achievement = new Achievement();
				achievement.setIcon(cursor.getString(0));
				achievement.setText(cursor.getString(1));
				achievement.setNumber(cursor.getInt(2));
				achievement.setPoint(cursor.getInt(3));

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

	private void initData(SQLiteDatabase db, int resourceId) {
		db.beginTransaction();

		InputStream insertsStream = mContext.getResources().openRawResource(resourceId);
		BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

		// Iterate through lines (assuming each insert has its own line and theres no other stuff)
		try {
			while (insertReader.ready()) {
				String insertStmt = insertReader.readLine();
				db.execSQL(insertStmt);
			}
			insertReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*final ContentValues cv = new ContentValues();
		for (final Iterator i = jo.keys(); i.hasNext(); ){
			final String unit = (String)i.next();
			final String description = jo.optString(unit);
			final String fprint = getFingerprint(unit);
			cv.put(ClassificationEntry._FACTOR_FPRINT, fprint);
			cv.put(ClassificationEntry._DESCRIPTION, description);
			db.insert(DB_CLASSIFICATION_TABLE, null, cv);
		}*/
		db.setTransactionSuccessful();
		db.endTransaction();
//		db.close();
	}

	/*private JSONObject loadInitialWeights(int resourceId){
		try{

			final JSONObject jo = loadJsonObjectFromRawResource(context, resourceId);

			// remove all "comments", which are just key entries that start with "--"
			for (final Iterator i = jo.keys(); i.hasNext(); ){
				final String key = (String)i.next();
				if (key.startsWith("--")){
					i.remove();
				}
			}

			return jo;

		}catch (final Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public int insertFromFile(Context context, int resourceId) throws IOException {
		// Reseting Counter
		int result = 0;

		// Open the resource
		InputStream insertsStream = context.getResources().openRawResource(resourceId);
		BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));

		// Iterate through lines (assuming each insert has its own line and theres no other stuff)
		while (insertReader.ready()) {
			String insertStmt = insertReader.readLine();
			db.execSQL(insertStmt);
			result++;
		}
		insertReader.close();

		// returning number of inserted rows
		return result;
	}*/

	/* МЕТОДЫ */
}