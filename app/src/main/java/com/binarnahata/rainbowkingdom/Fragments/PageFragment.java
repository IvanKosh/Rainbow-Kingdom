package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 09.01.16, 16:16
 *
 * @author bat
 * @version 0.1
 */
public class PageFragment extends Fragment {
	public static final String EXTRA_LAYOUT = "EXTRA_LAYOUT";
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PageFragment.class.getSimpleName();

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public static final PageFragment newInstance(int layout) {
		PageFragment f = new PageFragment();
		Bundle args = new Bundle();
		args.putInt(EXTRA_LAYOUT, layout);
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View v = inflater.inflate(getArguments().getInt(EXTRA_LAYOUT), container, false);
		return v;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
