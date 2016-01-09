package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
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
public class TutorialFragment extends Fragment {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = TutorialFragment.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tutorial2, container, false);

		List<Fragment> fragments = getFragments();

		final PageAdapter pageAdapter = new PageAdapter(getFragmentManager(), fragments);

		ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
		pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}
			@Override
			public void onPageSelected(int position) {
				Log.d(TAG, String.valueOf(position));
			}
			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
		pager.setAdapter(pageAdapter);

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	private List<Fragment> getFragments(){
		List<Fragment> fList = new ArrayList<>();

		fList.add(MyFragment.newInstance("Fragment 1"));
		fList.add(MyFragment.newInstance("Fragment 2"));
		fList.add(MyFragment.newInstance("Fragment 3"));

		return fList;
	}
	/* МЕТОДЫ */
}
