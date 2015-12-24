package com.binarnahata.rainbowkingdom.Libs.DataSaver;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 23.12.15, 9:52
 *
 * @author bat
 * @version 1.1
 */
public class FileDataSaver {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = FileDataSaver.class.getSimpleName();
	private final Context mContext;
	private final String mFilename;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public FileDataSaver(Context context, String filename) {
		mContext = context;
		mFilename = filename;
	}

	private <T> T getInstance(Class<T> object) {
		try {
			return object.getDeclaredConstructor(JSONObject.class).newInstance(new JSONObject());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public <T> void saveData(ArrayList<T> list) {
		JSONArray dataJSONArray = new JSONArray();
		for (T object : list) {
			dataJSONArray.put(object.toString());
		}

		Writer writer = null;
		try {
			OutputStream out = mContext
				.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(dataJSONArray.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public <T> ArrayList<T> loadData(Class<T> object) {
		ArrayList<T> arrayList = new ArrayList<>();
		BufferedReader reader;

		InputStream in;
		try {
			in = mContext.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				jsonString.append(line);
			}

			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for (int i = 0; i < array.length(); i++) {
				arrayList.add(getInstance(object));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}
	/* МЕТОДЫ */
}