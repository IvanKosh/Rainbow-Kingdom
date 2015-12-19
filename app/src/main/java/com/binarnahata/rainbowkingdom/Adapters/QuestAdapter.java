package com.binarnahata.rainbowkingdom.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.R;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 18.12.15, 18:36
 *
 * @author bat
 * @version 0.1
 */
public class QuestAdapter extends ArrayAdapter<Quest> {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = QuestAdapter.class.getSimpleName();
	private final LayoutInflater mInflater;

	private Callbacks mCallbacks;
	private ArrayList<Quest> mQuestArrayList;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	public int getCount() {
		return mQuestArrayList.size();
	}

	public Quest getItem(Quest position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public QuestAdapter(Context context, int resource, ArrayList<Quest> questArrayList) {
		super(context, resource);
		mQuestArrayList = questArrayList;

		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public interface Callbacks {
		void onSelect();
	}
	/* МЕТОДЫ */

	public static class ViewHolder {
		public TextView text;
		public ImageView image;
		public TextView request;
		public TextView experience;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final ViewHolder holder;

		try {
			if (convertView == null) {
				view = mInflater.inflate(R.layout.item_quest, parent, false);
				holder = new ViewHolder();

				holder.image = (ImageView)view.findViewById(R.id.image);
				holder.image.setImageResource(mQuestArrayList.get(position).getHeroAvatar());

				holder.text = (TextView)view.findViewById(R.id.text);
				holder.text.setText(mQuestArrayList.get(position).getText());

				holder.request = (TextView)view.findViewById(R.id.request);
				holder.request.setText(mQuestArrayList.get(position).getStringQuestRequestList());
				
				holder.experience = (TextView)view.findViewById(R.id.experience);
				holder.experience.setText(String.valueOf(mQuestArrayList.get(position).getExperience()));
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

		} catch (Exception e) {
			Log.e(TAG, "Error 1. " + e.toString());
		}

		return view;
	}
}