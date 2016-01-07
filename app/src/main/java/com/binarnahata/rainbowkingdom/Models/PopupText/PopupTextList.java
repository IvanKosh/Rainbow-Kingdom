package com.binarnahata.rainbowkingdom.Models.PopupText;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Collections;

/**
 * RainbowKingdom
 * Created on 07.01.16, 17:15
 *
 * @author bat
 * @version 0.1
 */
public class PopupTextList {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PopupTextList.class.getSimpleName();

	private Paint mPaint;
	private ArrayList<PopupText> mPopupTexts;
	private int mCursor;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public PopupText getPopupText() {
		if (mCursor > mPopupTexts.size()) {
			mCursor = 0;
		}
		return mPopupTexts.get(mCursor++);
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public PopupTextList(String[] strings, int textSize) {
		mPaint = new Paint();
		mPaint.setTextSize(textSize);

		for (String text : strings) {
			Rect r = new Rect();
			mPaint.getTextBounds(text, 0, text.length(), r);
			mPopupTexts.add(new PopupText(text,
				new Point(- r.width() / 2 - r.left, r.height() / 2 - r.bottom)));
		}
		Collections.shuffle(mPopupTexts);
		mCursor = 0;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}