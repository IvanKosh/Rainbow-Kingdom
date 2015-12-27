package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.Models.GameMode.AverageGameMode;
import com.binarnahata.rainbowkingdom.Models.GameMode.FastGameMode;
import com.binarnahata.rainbowkingdom.Models.GameMode.GameMode;
import com.binarnahata.rainbowkingdom.Models.GameMode.QuickGameMode;
import com.binarnahata.rainbowkingdom.Models.GameMode.SlowGameMode;
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
	private static final String EXTRA_TYPE = "type";
	private GameMode mGameMode;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GameFragment() {
	}

	public static GameFragment newInstance(int type) {
		Bundle args = new Bundle();
		args.putInt(EXTRA_TYPE, type);
		GameFragment fragment = new GameFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switch (getArguments().getInt(EXTRA_TYPE)) {
			case 0:
				mGameMode = new QuickGameMode();
				break;
			case 1:
				mGameMode = new FastGameMode();
				break;
			case 2:
				mGameMode = new AverageGameMode();
				break;
			case 3:
				mGameMode = new SlowGameMode();
				break;
			default:
				mGameMode = new QuickGameMode();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return new RKFarm(getContext(), mGameMode);
	}

	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}