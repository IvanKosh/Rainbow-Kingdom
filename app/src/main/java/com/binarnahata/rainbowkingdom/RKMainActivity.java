package com.binarnahata.rainbowkingdom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.binarnahata.rainbowkingdom.Controllers.VolumeControl;
import com.binarnahata.rainbowkingdom.Fragments.BackPressedInterface;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Fragments.TutorialFragment;
import com.binarnahata.rainbowkingdom.Libs.Utils;
import com.binarnahata.rainbowkingdom.Models.Experience;
import com.binarnahata.rainbowkingdom.Models.Resources.Resources;
import com.binarnahata.rainbowkingdom.Models.Volume;

import java.util.List;

public class RKMainActivity extends AppCompatActivity implements VolumeControl {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = RKMainActivity.class.getSimpleName();
	SharedPreferences prefs = null;
	private FragmentManager mFragmentManager;
	private boolean mIsBound = false;
	private BackgroundMusicService mBackgroundMusicService;
	private Intent mBackgroundMusic;
	private Volume mVolume;
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder binder) {
			mBackgroundMusicService = ((BackgroundMusicService.ServiceBinder) binder).getService();

			mBackgroundMusicService.setVolume(mVolume.getMusicVolume());
		}

		public void onServiceDisconnected(ComponentName name) {
			mBackgroundMusicService = null;
		}

	};

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
			.add(R.id.fragment, new MenuFragment(), MenuFragment.class.getSimpleName())
			.commit();

		mVolume = Volume.getInstance(this);

		mBackgroundMusic = new Intent();
		mBackgroundMusic.setClass(this, BackgroundMusicService.class);
		startService(mBackgroundMusic);
		doBindService();

		Resources.getInstance(this).initData();
		Experience.getInstance(this).initData();
		prefs = getSharedPreferences(this.getPackageName(), MODE_PRIVATE);
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void runFragment(Fragment newFragment) {
		List<Fragment> fragmentList = mFragmentManager.getFragments();
		if (fragmentList != null) {
			for (Fragment fragment : fragmentList) {
				if (fragment != null) {
					mFragmentManager.beginTransaction()
						.remove(fragment)
						.commit();
				}
			}
		}

		mFragmentManager.beginTransaction()
			.add(R.id.fragment, newFragment, newFragment.getClass().getSimpleName())
			.commit();
	}

	public void replaceFragment(Fragment newFragment) {
		mFragmentManager.beginTransaction()
			.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
			.replace(R.id.fragment, newFragment, newFragment.getClass().getSimpleName())
			.commit();
	}

	void doBindService() {
		bindService(new Intent(this, BackgroundMusicService.class),
			mServiceConnection, Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	void doUnbindService() {
		if (mIsBound) {
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
		} else {
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

		if (prefs.getBoolean("firstRun", true)) {
			runFragment(new TutorialFragment());
			prefs.edit().putBoolean("firstRun", false).commit();
		}
	}

	@Override
	public void setVolume(float volume) {
		mBackgroundMusicService.setVolume(volume);
	}
	/* МЕТОДЫ */
}