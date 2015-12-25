package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.Views.RKFarm;

/**
 * RainbowKingdom
 * Created on 06.12.15, 13:58
 *
 * @author bat
 * @version 0.1
 */
public class GameFragment extends Fragment implements BackPressedInterface {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = GameFragment.class.getSimpleName();
	private static final String EXTRA_NUMBER_OF_CIRCLES = "number of circles";
	private static final String EXTRA_RATING = "resources rating";
	private int mNumber;
	private int mRating;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GameFragment() {
	}

	public static GameFragment newInstance(int number, int rating) {
		Bundle args = new Bundle();
		args.putInt(EXTRA_NUMBER_OF_CIRCLES, number);
		args.putInt(EXTRA_RATING, rating);
		GameFragment fragment = new GameFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNumber = getArguments().getInt(EXTRA_NUMBER_OF_CIRCLES);
		mRating = getArguments().getInt(EXTRA_RATING);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return new RKFarm(getContext(), mNumber, mRating);
	}

	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}