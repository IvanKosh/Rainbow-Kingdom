package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binarnahata.rainbowkingdom.Adapters.AchievementAdapter;
import com.binarnahata.rainbowkingdom.Adapters.QuestAdapter;
import com.binarnahata.rainbowkingdom.Libs.DataBase.AchievementDatabaseHandler;
import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;
import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.Models.Quest.QuestData;
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

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDB = new AchievementDatabaseHandler(getContext());
		initAchievementList();
		initAchievementAdapter();
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

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}

	private void initAchievementList() {
		mAchievementListArray = mDB.getLimitAchievement(0, 10);
	}

	private void initAchievementAdapter() {
		mAdapter = new AchievementAdapter(mAchievementListArray);
	}
	/* МЕТОДЫ */
}
