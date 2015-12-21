package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.binarnahata.rainbowkingdom.Adapters.QuestAdapter;
import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.Models.Quest.QuestData;
import com.binarnahata.rainbowkingdom.RKMainActivity;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.Libs.Utils;

import org.json.JSONException;

import java.util.ArrayList;


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
	public static final int MAX_NUMBER_OF_QUESTS = 10;

	private ArrayList<Quest> mQuestListArray;

	private RecyclerView mRecyclerView;
	private QuestAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu, container, false);



		mRecyclerView = (RecyclerView)view.findViewById(R.id.quests);
		mRecyclerView.setHasFixedSize(true);
		mLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setAdapter(mAdapter);

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
					((RKMainActivity) getActivity()).runFragment(new ResourcesFragment());
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
				mAdapter.updateSettings();
				mAdapter.notifyDataSetChanged();
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