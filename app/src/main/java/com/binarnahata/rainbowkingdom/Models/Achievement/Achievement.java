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

	private int _id;
	private String mIcon;
	private String mText;
	private int mNumber;
	private int mPoint;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
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
	public Achievement(int _id, String icon, String text, int number, int point) {
		this._id = _id;
		mIcon = icon;
		mText = text;
		mNumber = number;
		mPoint = point;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
