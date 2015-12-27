package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Adapters.AchievementAdapter;
import com.binarnahata.rainbowkingdom.Controllers.loadAchievements;
import com.binarnahata.rainbowkingdom.Libs.DataBase.AchievementDatabaseHandler;
import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;
import com.binarnahata.rainbowkingdom.R;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 22.12.15, 18:09
 *
 * @author bat
 * @version 0.1
 */
public class AchievementsFragment extends Fragment implements BackPressedInterface {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = AchievementsFragment.class.getSimpleName();
	private AchievementDatabaseHandler mDB;
	private ArrayList<Achievement> mAchievementListArray;
	private AchievementAdapter mAdapter;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;

	private int count = 10;
	private int skip = -10;
	private TextView mSumTextView;
	private int mSum = 0;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initAchievementAdapter();
		initAchievementList();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_achievements, container, false);

		mRecyclerView = (RecyclerView)view.findViewById(R.id.achievements);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setAdapter(mAdapter);

		mSumTextView = (TextView) view.findViewById(R.id.achievements_point);
		mSumTextView.setText(String.valueOf(mSum));
		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}

	private void initAchievementList() {
		loadAchievements loadAchievements = new loadAchievements(getContext(), mAdapter);
		loadAchievements.execute();
	}

	private void initAchievementAdapter() {
		mAchievementListArray = new ArrayList<>();
		mAdapter = new AchievementAdapter(mAchievementListArray, new AchievementAdapter.Callbacks() {
			@Override
			public void offsetSum(int deltaSum) {
				mSum += deltaSum;
				mSumTextView.setText(String.valueOf(mSum));
			}
		});
	}
	/* МЕТОДЫ */
}