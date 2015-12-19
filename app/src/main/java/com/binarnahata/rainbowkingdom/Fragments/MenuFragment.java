package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.binarnahata.rainbowkingdom.Adapters.MyQuestAdapter;
import com.binarnahata.rainbowkingdom.Adapters.QuestAdapter;
import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.Models.Quest.QuestData;
import com.binarnahata.rainbowkingdom.RKMainActivity;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.Libs.Utils;

import java.util.ArrayList;
import java.util.List;


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

	private ArrayList<Quest> mQuestListArray;
	private QuestAdapter mQuestAdapter;


	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
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
		/*initQuestAdapter();*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu, container, false);


		mRecyclerView = (RecyclerView)view.findViewById(R.id.quests);

		mRecyclerView.setHasFixedSize(true);

		// use a linear layout manager
		mLayoutManager = new LinearLayoutManager(getContext());
		mRecyclerView.setLayoutManager(mLayoutManager);

		String[] myDataset;

		myDataset = new String[3];

		myDataset[0] = String.valueOf("cool");
		myDataset[1] = "norm";
		myDataset[2] = "bad";

		mAdapter = new MyQuestAdapter(mQuestListArray);
		mRecyclerView.setAdapter(mAdapter);

		/*ListView listView = (ListView)view.findViewById(R.id.quests);
		listView.setAdapter(mQuestAdapter);*/

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

		while (mQuestListArray.size() < 10) {
			mQuestListArray.add(new Quest(getContext()));
		}
	}

	private void initQuestAdapter() {
		mQuestAdapter = new QuestAdapter(getContext(), 0, mQuestListArray);
	}

	@Override
	public void onPause() {
		super.onPause();
		QuestData.getInstance(getActivity()).saveData(mQuestListArray);
	}
	/* МЕТОДЫ */
}