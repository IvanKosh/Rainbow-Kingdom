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
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PageFragment.class.getSimpleName();

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public static final PageFragment newInstance(String message) {
		PageFragment f = new PageFragment();
		Bundle bdl = new Bundle(1);
		bdl.putString(EXTRA_MESSAGE, message);
		f.setArguments(bdl);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		String message = getArguments().getString(EXTRA_MESSAGE);
		View v = inflater.inflate(R.layout.fragment_page, container, false);
		TextView messageTextView = (TextView) v.findViewById(R.id.textView);
		messageTextView.setText(message);
		return v;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
