package com.binarnahata.rainbowkingdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.binarnahata.rainbowkingdom.Fragments.BackPressedInterface;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Libs.DataBase.AchievementDatabaseHandler;
import com.binarnahata.rainbowkingdom.Models.Achievement.Achievement;
import com.binarnahata.rainbowkingdom.Models.Achievement.AchievementData;
import com.binarnahata.rainbowkingdom.Models.Experience;
import com.binarnahata.rainbowkingdom.Models.Resources.Resources;

import java.util.ArrayList;
import java.util.List;

public class RKMainActivity extends AppCompatActivity {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = RKMainActivity.class.getSimpleName();

	private FragmentManager mFragmentManager;

	private boolean mIsBound = false;
	private BackgroundMusicService mBackgroundMusicService;
	private ServiceConnection mServiceConnection = new ServiceConnection(){
		public void onServiceConnected(ComponentName name, IBinder binder) {
			mBackgroundMusicService = ((BackgroundMusicService.ServiceBinder)binder).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			mBackgroundMusicService = null;
		}

	};
	private Intent mBackgroundMusic;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mFragmentManager = getSupportFragmentManager();
		mFragmentManager.beginTransaction()
			.add(R.id.fragment, new MenuFragment())
			.commit();

		doBindService();
		mBackgroundMusic = new Intent();
		mBackgroundMusic.setClass(this, BackgroundMusicService.class);
		startService(mBackgroundMusic);

		Resources.getInstance(this).initData();
		Experience.getInstance(this).initData();

		AchievementDatabaseHandler db = new AchievementDatabaseHandler(this);
		/**
		 * CRUD Operations
		 * */
		// Inserting Contacts
		Log.d("Insert: ", "Inserting ..");
		db.addAchievement(new Achievement("Ravi", "9100000000", 0, 5));
		db.addAchievement(new Achievement("Srinivas", "9199999999", 0, 10));
		db.addAchievement(new Achievement("Tommy", "9522222222", 0, 5));
		db.addAchievement(new Achievement("Karthik", "9533333333", 0, 15));

		// Reading all contacts
		Log.d("Reading: ", "Reading all contacts..");
		ArrayList<Achievement> contacts = db.getLimitAchievement(0, 2);

		for (Achievement achievement : contacts) {
			String log = "Icon: " + achievement.getIcon()
				+ ", Text: " + achievement.getText()
				+ ", Number: " + achievement.getNumber()
				+ ", Point: " + achievement.getPoint();
			// Writing Contacts to log
			Log.d(TAG, log);
		}

		Log.d(TAG, "step");

		contacts = db.getLimitAchievement(2, 2);

		for (Achievement achievement : contacts) {
			String log = "Icon: " + achievement.getIcon()
				+ ", Text: " + achievement.getText()
				+ ", Number: " + achievement.getNumber()
				+ ", Point: " + achievement.getPoint();
			// Writing Contacts to log
			Log.d(TAG, log);
		}

		Log.d(TAG, "step");

		contacts = db.getLimitAchievement(20, 100);

		for (Achievement achievement : contacts) {
			String log = "Icon: " + achievement.getIcon()
				+ ", Text: " + achievement.getText()
				+ ", Number: " + achievement.getNumber()
				+ ", Point: " + achievement.getPoint();
			// Writing Contacts to log
			Log.d(TAG, log);
		}

		Log.d(TAG, "step");

		contacts = db.getLimitAchievement(70, 20);

		if (contacts != null) {
			for (Achievement achievement : contacts) {
				String log = "Icon: " + achievement.getIcon()
						+ ", Text: " + achievement.getText()
						+ ", Number: " + achievement.getNumber()
						+ ", Point: " + achievement.getPoint();
					// Writing Contacts to log
					Log.d(TAG, log);
			}
		}
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void runFragment(Fragment newFragment) {
		List<Fragment> fragmentList = mFragmentManager.getFragments();
		if (fragmentList != null) {
			for (Fragment fragment : fragmentList) {
				mFragmentManager.beginTransaction()
					.remove(fragment)
					.commit();
			}
		}
		mFragmentManager.beginTransaction()
			.add(R.id.fragment, newFragment)
			.commit();
	}

	void doBindService(){
		bindService(new Intent(this, BackgroundMusicService.class),
			mServiceConnection, Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	void doUnbindService()
	{
		if(mIsBound)
		{
			unbindService(mServiceConnection);
			mIsBound = false;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		doUnbindService();
		stopService(mBackgroundMusic);
		Resources.getInstance(this).saveData();
		Experience.getInstance(this).saveData();
	}

	@Override
	public void onBackPressed() {
		BackPressedInterface fragment = (BackPressedInterface) mFragmentManager.findFragmentById(R.id.fragment);
		Fragment next = fragment.getNext();
		if (next == null) {
			super.onBackPressed();
		}
		else {
			runFragment(next);
		}
	}
	/* МЕТОДЫ */
}