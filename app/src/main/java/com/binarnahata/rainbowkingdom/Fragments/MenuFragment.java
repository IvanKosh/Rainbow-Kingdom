package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.binarnahata.rainbowkingdom.BackPressedInterface;
import com.binarnahata.rainbowkingdom.RKMainActivity;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.Utils;

/**
 * RainbowKingdom
 * Created on 30.11.15, 17:14
 *
 * @author bat
 * @version 0.1
 */
public class MenuFragment extends Fragment implements BackPressedInterface {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MenuFragment.class.getSimpleName();
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

		Button fast = (Button) view.findViewById(R.id.fast_button);
		fast.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity)getActivity()).runFragment(new GameFragment().newInstance(6, 3));
				}
			}
		});

		Button average = (Button) view.findViewById(R.id.average_button);
		average.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity)getActivity()).runFragment(new GameFragment().newInstance(10, 2));
				}
			}
		});

		Button slow = (Button) view.findViewById(R.id.slow_button);
		slow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity)getActivity()).runFragment(new GameFragment().newInstance(20, 1));
				}
			}
		});

		Button farm = (Button) view.findViewById(R.id.farm_button);
		farm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity)getActivity()).runFragment(new GameFragment().newInstance(Utils.rndCircles(), 2));
				}
			}
		});

		Button resources = (Button) view.findViewById(R.id.resources_button);
		resources.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity)getActivity()).runFragment(new ResourcesFragment());
				}
			}
		});

		return view;
	}

	@Override
	public Fragment getNext() {
		return null;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/* МЕТОДЫ */
}