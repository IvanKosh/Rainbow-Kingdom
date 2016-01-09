package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Adapters.QuestAdapter;
import com.binarnahata.rainbowkingdom.Models.Experience;
import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.Models.Quest.QuestData;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.RKMainActivity;

import java.util.ArrayList;


/**
 * RainbowKingdom
 * Created on 30.11.15, 17:14
 *
 * @author bat
 * @version 0.1
 */
public class MenuFragment extends Fragment implements BackPressedInterface {
	public static final int MAX_NUMBER_OF_QUESTS = 10;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MenuFragment.class.getSimpleName();
	private ArrayList<Quest> mQuestListArray;

	private RecyclerView mRecyclerView;
	private QuestAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	private ProgressBar mProgressBar;

	private Experience mExperience;
	private TextView mLevel;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MenuFragment() {
	}

	@Override
	public void onCreate(Bundle savedBundleInstance) {
		super.onCreate(savedBundleInstance);

		initQuestList();
		initQuestAdapter();
		mExperience = Experience.getInstance(getContext());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu, container, false);

		mRecyclerView = (RecyclerView) view.findViewById(R.id.quests);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setAdapter(mAdapter);

		ImageView settings = (ImageView) view.findViewById(R.id.settings_button);
		settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity) getActivity()).runFragment(new SettingsFragment());
				}
			}
		});

		Button fast = (Button) view.findViewById(R.id.fast_button);
		fast.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity) getActivity()).runFragment(new GameFragment().newInstance(1));
				}
			}
		});

		Button average = (Button) view.findViewById(R.id.average_button);
		average.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity) getActivity()).runFragment(new GameFragment().newInstance(2));
				}
			}
		});

		Button slow = (Button) view.findViewById(R.id.slow_button);
		slow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity) getActivity()).runFragment(new GameFragment().newInstance(3));
				}
			}
		});

		Button farm = (Button) view.findViewById(R.id.farm_button);
		farm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity) getActivity()).runFragment(new GameFragment().newInstance(0));
				}
			}
		});

		Button resources = (Button) view.findViewById(R.id.resources_button);
		resources.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity) getActivity()).runFragment(new ResourcesFragment());
				}
			}
		});

		Button achievement = (Button) view.findViewById(R.id.achievements_button);
		achievement.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (getContext() instanceof RKMainActivity) {
					((RKMainActivity) getActivity()).replaceFragment(new AchievementsFragment());
				}
			}
		});

		mLevel = (TextView) view.findViewById(R.id.level);
		mLevel.setText(mExperience.getLevelString());

		mProgressBar = (ProgressBar) view.findViewById(R.id.experienceProgressBar);
		mProgressBar.setProgress(mExperience.getProgress());

		return view;
	}

	@Override
	public Fragment getNext() {
		return null;
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	private void initQuestList() {
		mQuestListArray = QuestData.getInstance(getActivity()).getQuestArrayList();

		while (mQuestListArray.size() < MAX_NUMBER_OF_QUESTS) {
			mQuestListArray.add(new Quest(getContext()));
		}
	}

	private void initQuestAdapter() {
		mAdapter = new QuestAdapter(getContext(), mQuestListArray, new QuestAdapter.Callback() {
			@Override
			public void onSelect() {
				mProgressBar.setProgress(mExperience.getProgress());
				mLevel.setText(mExperience.getLevelString());
				mAdapter.updateSettings();
				mAdapter.notifyDataSetChanged();
				mQuestListArray.add(new Quest(getContext()));
			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
		QuestData.getInstance(getActivity()).saveData(mQuestListArray);
	}
	/* МЕТОДЫ */
}