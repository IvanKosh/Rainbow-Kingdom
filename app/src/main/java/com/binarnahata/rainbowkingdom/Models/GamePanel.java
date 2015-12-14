package com.binarnahata.rainbowkingdom.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 13.12.15, 21:33
 *
 * @author bat
 * @version 0.1
 */
public class GamePanel {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = GamePanel.class.getSimpleName();

	private final Bitmap mFon;
	private final Bitmap mGun;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GamePanel(Bitmap fon, Bitmap gun) {
		mFon = fon;
		mGun = gun;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas, Rect dstRect) {
		canvas.drawBitmap(mFon, null, dstRect, null);
		//canvas.drawBitmap(mGun, null, new Rect(/*dstRect.centerX()*/0, dstRect.centerY(), mGun.getWidth(), mGun.getHeight()), null);
		canvas.drawBitmap(mGun, null, new Rect(0, 0, 48, 48), null);
	}
	/* МЕТОДЫ */
}
