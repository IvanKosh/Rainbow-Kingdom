package com.binarnahata.rainbowkingdom.Controllers;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.Views.BH_SurfaceView;

/**
 * RainbowKingdom
 * Created on 17.12.15, 13:00
 *
 * @author bat
 * @version 0.1
 */
public class BackgroundMusic extends Thread {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = BackgroundMusic.class.getSimpleName();
	private final Context mContext;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public BackgroundMusic(Context context) {
		mContext = context;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void run() {
		MediaPlayer mp = MediaPlayer.create(mContext, R.raw.la_ere_gymnopedie);
		mp.setVolume(100,100);
		mp.setLooping(true);
		mp.start();
	}
	/* МЕТОДЫ */
}
