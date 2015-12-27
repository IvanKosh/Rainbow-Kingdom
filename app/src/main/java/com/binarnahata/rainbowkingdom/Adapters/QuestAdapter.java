package com.binarnahata.rainbowkingdom.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.binarnahata.rainbowkingdom.Libs.DataBase.AchievementDatabaseHandler;
import com.binarnahata.rainbowkingdom.Models.Experience;
import com.binarnahata.rainbowkingdom.Models.Quest.Quest;
import com.binarnahata.rainbowkingdom.Models.Resources.Resources;
import com.binarnahata.rainbowkingdom.R;

import org.json.JSONObject;

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
	public static final String APP_PREFERENCES = "resources";
	private final Context mContext;
	private final Experience mExperience;
	private final AchievementDatabaseHandler mDB;
	private Resources mResources;

	private ArrayList<Quest> mQuestArrayList;
	private Callback mCallback;
	private JSONObject mJSONObject;
	private SharedPreferences mSettings;
	private SharedPreferences.Editor mEditor;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public QuestAdapter(Context context, ArrayList<Quest> quests, Callback callback) {
		mQuestArrayList = quests;
		mCallback = callback;
		mContext = context;

		mSettings = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_APPEND);
		mEditor = mSettings.edit();
		mResources = Resources.getInstance(context);
		mExperience = Experience.getInstance(context);
		updateSettings();

		mDB = AchievementDatabaseHandler.getInstance(context);
	}

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
		if (mQuestArrayList.get(position).canComplete(mJSONObject)) {
			holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Resources.getInstance(mContext).approve(mQuestArrayList.get(position).getJSONRequest());
					mExperience.offsetPoint(mQuestArrayList.get(position).getExperience());
					mQuestArrayList.remove(position);
					mDB.offsetAchievementProgress("quest", 1);
					mCallback.onSelect();
				}
			});
		}
		else {
			holder.mRelativeLayout.setAlpha(0.5F);
		}

		holder.mImage.setImageResource(mQuestArrayList.get(position).getHeroAvatar());
		holder.mText.setText(mQuestArrayList.get(position).getText());
		holder.mRequest.setText(mQuestArrayList.get(position).getJSONRequestString());
		holder.mExperience.setText(String.valueOf(mQuestArrayList.get(position).getExperience()));
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

	@Override
	public int getItemCount() {
		return (mQuestArrayList != null ? mQuestArrayList.size() : 0);
	}

	public interface Callback {
		void onSelect();
	}

	public void updateSettings() {
		mJSONObject = mResources.getResources();
	}
	/* МЕТОДЫ */
}