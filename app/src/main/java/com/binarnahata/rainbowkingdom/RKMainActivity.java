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

import com.binarnahata.rainbowkingdom.Fragments.BackPressedInterface;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Fragments.ResourcesFragment;

import org.json.JSONException;

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
		try {
			ResourcesFragment.initSettings(this);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		doBindService();
		mBackgroundMusic = new Intent();
		mBackgroundMusic.setClass(this, BackgroundMusicService.class);
		startService(mBackgroundMusic);
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