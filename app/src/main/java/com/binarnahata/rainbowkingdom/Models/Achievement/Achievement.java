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
	private int mNumber;
	private int mPoint;
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
	public int getNumber() {
		return mNumber;
	}
	public void setNumber(int number) {
		mNumber = number;
	}
	public int getPoint() {
		return mPoint;
	}
	public void setPoint(int point) {
		mPoint = point;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public Achievement() {
	}
	public Achievement(String icon, String text, int number, int point) {
		mIcon = icon;
		mText = text;
		mNumber = number;
		mPoint = point;
	}
	public Achievement(int id, String icon, String text, int number, int point) {
		mId = id;
		mIcon = icon;
		mText = text;
		mNumber = number;
		mPoint = point;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
