package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 22.12.15, 18:09
 *
 * @author bat
 * @version 0.1
 */
public class AchievementsFragment extends Fragment implements BackPressedInterface {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = AchievementsFragment.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_achievements, container, false);

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}
	/* МЕТОДЫ */
}
