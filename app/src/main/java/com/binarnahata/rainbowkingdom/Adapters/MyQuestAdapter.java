package com.binarnahata.rainbowkingdom.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.R;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 19.12.15, 19:35
 *
 * @author bat
 * @version 0.1
 */
public class MyQuestAdapter extends RecyclerView.Adapter<MyQuestAdapter.ViewHolder> {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MyQuestAdapter.class.getSimpleName();

	private ArrayList<Quest> mQuestArrayList;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MyQuestAdapter(ArrayList<Quest> quests) {
		mQuestArrayList = quests;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public static class ViewHolder extends RecyclerView.ViewHolder {
		// each data item is just a string in this case
		//public ImageView mImage;
		public TextView mText;
		//public TextView mRequest;
		public TextView mExperience;
		public ViewHolder(View view) {
			super(view);

			//mImage = (ImageView)view.findViewById(R.id.image);
			mText = (TextView)view.findViewById(R.id.text);
			//mRequest = (TextView)view.findViewById(R.id.request);
			mExperience = (TextView)view.findViewById(R.id.experience);
		}
	}

	/* МЕТОДЫ */

	@Override
	public MyQuestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
												   int viewType) {
		// create a new view
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_quest, parent, false);
		// set the view's size, margins, paddings and layout parameters


		ViewHolder vh = new ViewHolder(view);
		return vh;
	}

	// Replace the contents of a view (invoked by the layout manager)
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// - get element from your dataset at this position
		// - replace the contents of the view with that element
		//holder.mImage.setImageResource(mQuestArrayList.get(position).getHeroAvatar());
		holder.mText.setText(mQuestArrayList.get(position).getText());
		//holder.mRequest.setText(mQuestArrayList.get(position).getStringQuestRequestList());
		holder.mExperience.setText(String.valueOf(mQuestArrayList.get(position).getExperience()));
	}

	// Return the size of your dataset (invoked by the layout manager)
	@Override
	public int getItemCount() {
		return (mQuestArrayList != null ? mQuestArrayList.size() : 0);
	}

}