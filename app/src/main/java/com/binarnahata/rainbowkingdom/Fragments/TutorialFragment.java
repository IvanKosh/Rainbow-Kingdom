package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 09.01.16, 12:44
 *
 * @author bat
 * @version 0.1
 */
public class TutorialFragment extends Fragment implements BackPressedInterface {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = TutorialFragment.class.getSimpleName();

	private int mXDown;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public static TutorialFragment newInstance(int index) {
		TutorialFragment result = new TutorialFragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		result.setArguments(args);
		return result;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, String.valueOf(getArguments().getInt("someInt", 0)));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tutorial, container, false);
		Log.d(TAG, "onCreateView");
		view.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction() & MotionEvent.ACTION_MASK) {
					case MotionEvent.ACTION_DOWN:
						mXDown = (int) event.getX();
						break;

					case MotionEvent.ACTION_UP:
						if (event.getX() - mXDown > 30) {
							Log.d(TAG, "move RIGHT");
						}
						if (event.getX() - mXDown < -30) {
							Log.d(TAG, "move LEFT");
						}
						break;
				}
				return true;
			}
		});
		return view;
	}

	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}