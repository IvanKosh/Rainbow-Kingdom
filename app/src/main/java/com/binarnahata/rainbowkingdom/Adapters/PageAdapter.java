package com.binarnahata.rainbowkingdom.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * RainbowKingdom
 * Created on 09.01.16, 16:14
 *
 * @author bat
 * @version 0.1
 */
public class PageAdapter extends FragmentPagerAdapter {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = PageAdapter.class.getSimpleName();
	private List<Fragment> fragments;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public PageAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
	/* МЕТОДЫ */
}