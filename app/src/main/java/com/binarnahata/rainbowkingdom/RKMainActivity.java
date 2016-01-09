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

import com.binarnahata.rainbowkingdom.Controllers.VolumeControl;
import com.binarnahata.rainbowkingdom.Fragments.BackPressedInterface;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Libs.Utils;
import com.binarnahata.rainbowkingdom.Models.Experience;
import com.binarnahata.rainbowkingdom.Models.Resources.Resources;
import com.binarnahata.rainbowkingdom.Models.Volume;

import java.util.List;

public class RKMainActivity extends AppCompatActivity implements VolumeControl {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = RKMainActivity.class.getSimpleName();

	private FragmentManager mFragmentManager;

	private boolean mIsBound = false;
	private BackgroundMusicService mBackgroundMusicService;
	private ServiceConnection mServiceConnection = new ServiceConnection(){
		public void onServiceConnected(ComponentName name, IBinder binder) {
			mBackgroundMusicService = ((BackgroundMusicService.ServiceBinder)binder).getService();

			mBackgroundMusicService.setVolume(mVolume.getMusicVolume());
		}

		public void onServiceDisconnected(ComponentName name) {
			mBackgroundMusicService = null;
		}

	};
	private Intent mBackgroundMusic;
	private Volume mVolume;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Utils.setRandom();
		mFragmentManager = getSupportFragmentManager();
		mFragmentManager.beginTransaction()
			.add(R.id.fragment, new MenuFragment())
			.commit();

		mVolume = Volume.getInstance(this);

		mBackgroundMusic = new Intent();
		mBackgroundMusic.setClass(this, BackgroundMusicService.class);
		startService(mBackgroundMusic);
		doBindService();

		Resources.getInstance(this).initData();
		Experience.getInstance(this).initData();
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void runFragment(Fragment newFragment) {
		List<Fragment> fragmentList = mFragmentManager.getFragments();
		Log.d(TAG, String.valueOf(fragmentList.size()));
		if (fragmentList != null) {
			for (int i = fragmentList.size()-1; i > 0; i--) {
				mFragmentManager.beginTransaction()
					.remove(fragmentList.get(i))
					.commit();
			}
			/*for (Fragment fragment : fragmentList) {
				mFragmentManager.beginTransaction()
					.remove(fragment)
					.commit();
			}*/
		}

		fragmentList = mFragmentManager.getFragments();
		if (fragmentList != null) {
			Log.d(TAG, String.valueOf(fragmentList.size()));
		}
		mFragmentManager.beginTransaction()
			.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
			//.setCustomAnimations(null, null)
			.add(R.id.fragment, newFragment)
			.commit();

		fragmentList = mFragmentManager.getFragments();
		if (fragmentList != null) {
			Log.d(TAG, String.valueOf(fragmentList.size()));

			for (Fragment fragment : fragmentList) {
				Log.d(TAG, fragment.toString());
			}

			if (fragmentList.size() > 1) {
				mFragmentManager.beginTransaction()
					.remove(fragmentList.get(0))
					.commit();
			}
		}

		fragmentList = mFragmentManager.getFragments();
		if (fragmentList != null) {
			Log.d(TAG, String.valueOf(fragmentList.size()));
		}
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

	@Override
	public void onPause() {
		super.onPause();
		if (mBackgroundMusicService != null) {
			mBackgroundMusicService.pauseMusic();
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mBackgroundMusicService != null) {
			mBackgroundMusicService.resumeMusic();
		}
	}

	@Override
	public void setVolume(float volume) {
		mBackgroundMusicService.setVolume(volume);
	}
	/* МЕТОДЫ */
}