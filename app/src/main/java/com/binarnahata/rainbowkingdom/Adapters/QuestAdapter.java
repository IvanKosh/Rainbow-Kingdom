package com.binarnahata.rainbowkingdom.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

	/*public Quest getItem(Quest position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}*/
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
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		View mView = convertView;
		final ViewHolder holder;

		try {
			if (convertView == null) {
				mView = mInflater.inflate(R.layout.item_quest, parent, false);
				holder = new ViewHolder();

				holder.text = (TextView) mView.findViewById(R.id.text);
				holder.text.setText(mQuestArrayList.get(position).getId().toString());
				mView.setTag(holder);
			} else {
				holder = (ViewHolder) mView.getTag();
			}

		} catch (Exception e) {
			Log.e(TAG, "Error 1. " + e.toString());
		}

		return mView;
	}
}
