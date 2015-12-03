package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.binarnahata.rainbowkingdom.Controllers.GameEngine;
import com.binarnahata.rainbowkingdom.RKMainActivity;
import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 30.11.15, 17:14
 *
 * @author bat
 * @version 0.1
 */
public class MenuFragment extends Fragment {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MenuFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu, container, false);

		Button farm = (Button) view.findViewById(R.id.farm_button);
		farm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity)getActivity()).runFragment(new GameFragment());
					((RKMainActivity)getActivity()).runFragment(new GameFragment());
				}
				/*if (getContext() instanceof RKMainActivity) {
					((RKMainActivity)getActivity()).setGame(new GameEngine(getContext()));
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					((RKMainActivity)getActivity()).setGame(new GameEngine(getContext()));
				}*/
			}
		});

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}
