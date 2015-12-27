package com.binarnahata.rainbowkingdom.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;
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
	private ArrayList<Achievement> mAchievementsArrayList;
	private Callbacks mCallbacks;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public void setAchievementsArrayList(ArrayList<Achievement> achievementsArrayList) {
		mAchievementsArrayList = achievementsArrayList;
	}

	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public AchievementAdapter(ArrayList<Achievement> achievementArrayList, Callbacks callbacks) {
		mAchievementsArrayList = achievementArrayList;
		mCallbacks = callbacks;
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
		holder.mText.setText(mAchievementsArrayList.get(position).getText());
		holder.mNumber.setText(String.valueOf(mAchievementsArrayList.get(position).getNumber()));
		holder.mPoint.setText(String.valueOf(mAchievementsArrayList.get(position).getPoint()));
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void addAchievementsArrayList(ArrayList<Achievement>[] values) {
		for (Achievement achievement : values[0]) {
			mCallbacks.offsetSum(achievement.getPoint() * achievement.getNumber());
		}
		mAchievementsArrayList.addAll(values[0]);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView mImage;
		public TextView mText;
		public TextView mNumber;
		public TextView mPoint;
		public ViewHolder(View view) {
			super(view);

			mImage = (ImageView)view.findViewById(R.id.image);
			mText = (TextView)view.findViewById(R.id.text);
			mNumber = (TextView)view.findViewById(R.id.number);
			mPoint = (TextView)view.findViewById(R.id.point);
		}
	}

	@Override
	public int getItemCount() {
		return (mAchievementsArrayList != null ? mAchievementsArrayList.size() : 0);
	}

	public interface Callbacks {
		void offsetSum(int deltaSum);
	}
	/* МЕТОДЫ */
}
