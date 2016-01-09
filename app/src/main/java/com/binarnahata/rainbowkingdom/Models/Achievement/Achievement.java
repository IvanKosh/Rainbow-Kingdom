package com.binarnahata.rainbowkingdom.Models.Achievement;

/**
 * RainbowKingdom
 * Created on 22.12.15, 18:33
 *
 * @author bat
 * @version 0.1
 */
public class Achievement {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = Achievement.class.getSimpleName();

	private int mId;
	private String mIcon;
	private String mText;
	private int mPoint;
	private int mProgress;
	private int mNecessary;
	private String mTag;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public int getId() {
		return mId;
	}

	public void setId(int id) {
		this.mId = id;
	}

	public String getIcon() {
		return mIcon;
	}

	public void setIcon(String icon) {
		mIcon = icon;
	}

	public String getText() {
		return mText;
	}

	public void setText(String text) {
		mText = text;
	}

	public int getPoint() {
		return mPoint;
	}

	public void setPoint(int point) {
		mPoint = point;
	}

	public int getProgress() {
		return mProgress;
	}

	public void setProgress(int progress) {
		mProgress = progress;
	}

	public int getNecessary() {
		return mNecessary;
	}

	public void setNecessary(int limit) {
		mNecessary = limit;
	}

	public String getTag() {
		return mTag;
	}

	public void setTag(String tag) {
		mTag = tag;
	}

	public int getNumber() {
		return mProgress / mNecessary;
	}

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Achievement() {
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}