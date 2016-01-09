package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
public class TutorialFragment extends Fragment implements BackPressedInterface {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = TutorialFragment.class.getSimpleName();

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tutorial, container, false);

		RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);
		int count = radioGroup.getChildCount();
		final ArrayList<RadioButton> radioButtons = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			View o = radioGroup.getChildAt(i);
			if (o instanceof RadioButton) {
				radioButtons.add((RadioButton) o);
			}
		}
		radioButtons.get(0).setChecked(true);

		List<Fragment> fragments = getFragments();

		final PageAdapter pageAdapter = new PageAdapter(getFragmentManager(), fragments);

		ViewPager pager = (ViewPager) view.findViewById(R.id.viewPager);
		pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				radioButtons.get(position).setChecked(true);
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
	private List<Fragment> getFragments() {
		List<Fragment> fragmentList = new ArrayList<>();

		fragmentList.add(PageFragment.newInstance(R.layout.page1));
		fragmentList.add(PageFragment.newInstance(R.layout.page2));
		fragmentList.add(PageFragment.newInstance(R.layout.page3));
		fragmentList.add(PageFragment.newInstance(R.layout.page4));

		return fragmentList;
	}

	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}
	/* МЕТОДЫ */
}