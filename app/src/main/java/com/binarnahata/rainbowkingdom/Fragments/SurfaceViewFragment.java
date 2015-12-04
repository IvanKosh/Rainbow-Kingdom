package com.binarnahata.rainbowkingdom.Fragments;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.Controllers.FPS;
import com.binarnahata.rainbowkingdom.Controllers.GameEngine;
import com.binarnahata.rainbowkingdom.Controllers.SurfaceViewController;
import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 04.12.15, 12:10
 *
 * @author bat
 * @version 0.1
 */
public class SurfaceViewFragment extends Fragment {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = SurfaceViewFragment.class.getSimpleName();
	private final Boolean mType;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public SurfaceViewFragment(Boolean type) {
		mType = type;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		if (mType) {
			Log.d(TAG,  getActivity().getPackageName());
			return new GameEngine(getActivity());
		}
		else {
			return new FPS(getActivity());
		}
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
