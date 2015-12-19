package com.binarnahata.rainbowkingdom.Models.Quest;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 18.12.15, 16:55
 *
 * @author bat
 * @version 0.1
 */
public class QuestDateJSON {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = QuestDateJSON.class.getSimpleName();

	private Context mContext;
	private String mFilename;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public QuestDateJSON(Context context, String filename) {
		mContext = context;
		mFilename = filename;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */

	public void saveData(ArrayList<Quest> data)
		throws JSONException, IOException {
		JSONArray dataJSONArray = new JSONArray();
		for (Quest quest : data) {
			dataJSONArray.put(quest.toJSON());
		}

		Writer writer = null;
		try {
			OutputStream out = mContext
				.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(dataJSONArray.toString());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public ArrayList<Quest> loadData() throws JSONException, IOException {
		ArrayList<Quest> questArrayList = new ArrayList<>();
		BufferedReader reader = null;

		try {
			InputStream in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line /*= null*/;
			while((line = reader.readLine()) != null) {
				jsonString.append(line);
			}

			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for (int i = 0; i < array.length(); i++) {
				questArrayList.add(new Quest(array.getJSONObject(i)));
			}
		} catch (FileNotFoundException e) {
			Log.e(TAG, "Error 5. " + e.toString());
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		return questArrayList;
	}
}