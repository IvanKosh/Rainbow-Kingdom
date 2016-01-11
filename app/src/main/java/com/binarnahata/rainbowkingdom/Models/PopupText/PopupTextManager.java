package com.binarnahata.rainbowkingdom.Models.PopupText;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * RainbowKingdom
 * Created on 07.01.16, 17:15
 *
 * @author bat
 * @version 0.1
 */
public class PopupTextManager {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PopupTextManager.class.getSimpleName();

	private Paint mPaint;
	private ArrayList<PopupText> mPopupTexts;
	private int mCursor;

	private PopupText now = null;

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public PopupTextManager(String[] strings, int textSize) {
		mPaint = new Paint();
		mPaint.setTextSize(textSize);

		mPopupTexts = new ArrayList<>();
		for (String text : strings) {
			Rect r = new Rect();
			mPaint.getTextBounds(text, 0, text.length(), r);
			mPopupTexts.add(new PopupText(text,
				new Point(-r.width() / 2 - r.left, r.height() / 2 - r.bottom),
				mPaint));
		}
		Collections.shuffle(mPopupTexts);
		mCursor = 0;
	}

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public PopupText getPopupText(int x, int y) {
		if (mCursor == mPopupTexts.size()) {
			mCursor = 0;
		}
		PopupText result = mPopupTexts.get(mCursor++).copy();
		result.offset(x, y);
		return result;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}