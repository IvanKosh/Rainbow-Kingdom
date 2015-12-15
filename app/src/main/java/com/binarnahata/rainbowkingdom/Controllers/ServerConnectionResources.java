package com.binarnahata.rainbowkingdom.Controllers;

import android.os.AsyncTask;
import android.view.View;

import java.util.concurrent.TimeUnit;

/**
 * RainbowKingdom
 * Created on 15.12.15, 17:29
 *
 * @author bat
 * @version 0.1
 */
public class ServerConnectionResources extends AsyncTask<Void, Integer, Void> {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = ServerConnectionResources.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		//mTvInfo.setText("Полез на крышу");
		//mStart.setVisibility(View.INVISIBLE);
	}


	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		//mTvInfo.setText("Этаж: " + values[0]);
		//mProgress.setProgress(values[0]);
	}

	/*@Override
	protected void onPostExecute() {
		super.onPostExecute();
		//mTvInfo.setText("Залез" + result.toString());
		//mStart.setVisibility(View.VISIBLE);
		//mProgress.setProgress(0);
	}*/

	@Override
	protected Void doInBackground(Void... params) {
		try {
			int counter = 0;
			/*for (String url : urls) {
				// загружаем файл или лезем на другой этаж
				//getFloor(counter);
				// выводим промежуточные результаты
				publishProgress(++counter);
			}*/

			// разъединяемся
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}
	/* МЕТОДЫ */
}
