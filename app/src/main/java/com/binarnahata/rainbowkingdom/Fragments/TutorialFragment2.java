package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.Adapters.PageAdapter;
import com.binarnahata.rainbowkingdom.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RainbowKingdom
 * Created on 09.01.16, 16:00
 *
 * @author bat
 * @version 0.1
 */
public class TutorialFragment2 extends Fragment {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = TutorialFragment2.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tutorial2, container, false);

		List<Fragment> fragments = getFragments();

		PageAdapter pageAdapter = new PageAdapter(getFragmentManager(), fragments);

		ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<Fragment>();

		fList.add(MyFragment.newInstance("Fragment 1"));
		fList.add(MyFragment.newInstance("Fragment 2"));
		fList.add(MyFragment.newInstance("Fragment 3"));

		return fList;
	}
	/* МЕТОДЫ */
}
