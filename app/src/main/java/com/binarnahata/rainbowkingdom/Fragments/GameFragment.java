package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.Views.RKFarm;

/**
 * RainbowKingdom
 * Created on 05.12.15, 9:16
 *
 * @author bat
 * @version 0.1
 */
public class GameFragment extends Fragment {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GameFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//return inflater.inflate(R.layout.fragment_game_engine, container, false);
		return new RKFarm(getContext());
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
