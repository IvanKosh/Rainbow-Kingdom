package com.binarnahata.rainbowkingdom.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.ViewHolder> {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = QuestAdapter.class.getSimpleName();

	private ArrayList<Quest> mQuestArrayList;
	private Callback mCallback;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public QuestAdapter(ArrayList<Quest> quests, Callback callback) {
		mQuestArrayList = quests;
		mCallback = callback;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public RelativeLayout mRelativeLayout;
		public ImageView mImage;
		public TextView mText;
		public TextView mRequest;
		public TextView mExperience;
		public ViewHolder(View view) {
			super(view);

			mRelativeLayout = (RelativeLayout)view.findViewById(R.id.container);
			mImage = (ImageView)view.findViewById(R.id.image);
			mText = (TextView)view.findViewById(R.id.text);
			mRequest = (TextView)view.findViewById(R.id.request);
			mExperience = (TextView)view.findViewById(R.id.experience);
		}
	}

	/* МЕТОДЫ */

	@Override
	public QuestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
												   int viewType) {
		View view = LayoutInflater.from(parent.getContext())
			.inflate(R.layout.item_quest, parent, false);

		ViewHolder vh = new ViewHolder(view);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, final int position) {
		holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mQuestArrayList.remove(position);
				mCallback.onSelect();
			}
		});
		holder.mImage.setImageResource(mQuestArrayList.get(position).getHeroAvatar());
		holder.mText.setText(mQuestArrayList.get(position).getText());
		holder.mRequest.setText(mQuestArrayList.get(position).getStringQuestRequestList());
		holder.mExperience.setText(String.valueOf(mQuestArrayList.get(position).getExperience()));
	}

	@Override
	public int getItemCount() {
		return (mQuestArrayList != null ? mQuestArrayList.size() : 0);
	}

	public interface Callback {
		void onSelect();
	}
}