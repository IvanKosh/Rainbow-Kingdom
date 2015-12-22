package com.binarnahata.rainbowkingdom.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;
import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.R;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 22.12.15, 18:15
 *
 * @author bat
 * @version 0.1
 */
public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder> {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = AchievementAdapter.class.getSimpleName();
	private final ArrayList<Achievement> mAchievementsArrayList;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public AchievementAdapter(ArrayList<Achievement> achievementArrayList) {
		mAchievementsArrayList = achievementArrayList;

	}

	@Override
	public AchievementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
													  int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_achievement, parent, false);

		ViewHolder vh = new ViewHolder(view);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {

	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(View view) {
			super(view);
		}
	}

	@Override
	public int getItemCount() {
		return (mAchievementsArrayList != null ? mAchievementsArrayList.size() : 0);
	}
	/* МЕТОДЫ */
}
