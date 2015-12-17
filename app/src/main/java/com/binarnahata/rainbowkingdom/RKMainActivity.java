package com.binarnahata.rainbowkingdom;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.binarnahata.rainbowkingdom.Controllers.BackgroundMusic;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Fragments.ResourcesFragment;
import com.binarnahata.rainbowkingdom.Views.MusicService;

import java.util.List;

public class RKMainActivity extends AppCompatActivity {
	private static final String TAG = RKMainActivity.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private FragmentManager mFragmentManager;
	private MusicService mMusicService;
	private BackgroundMusic mBackgroundMusic;

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
		/*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);*/

		/*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
					.setAction("Action", null).show();
			}
		});*/
		ResourcesFragment.initSettings(this);

		mBackgroundMusic = new BackgroundMusic(this);
		mBackgroundMusic.run();

		Intent music = new Intent();
		music.setClass(this,MusicService.class);
		startService(music);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}*/
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}*/
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
	/* МЕТОДЫ */
}