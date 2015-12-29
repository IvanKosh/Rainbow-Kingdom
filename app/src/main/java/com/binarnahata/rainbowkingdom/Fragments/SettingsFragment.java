package com.binarnahata.rainbowkingdom.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.binarnahata.rainbowkingdom.Controllers.VolumeControl;
import com.binarnahata.rainbowkingdom.Models.Volume;
import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 22.12.15, 18:46
 *
 * @author bat
 * @version 0.1
 */
public class SettingsFragment extends Fragment implements BackPressedInterface {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = SettingsFragment.class.getSimpleName();

	private Volume mVolume;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mVolume = Volume.getInstance(getContext());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_settings, container, false);

		SeekBar music = (SeekBar) view.findViewById(R.id.settings_music_seek_bar);
		music.setProgress((int) (mVolume.getMusicVolume() * 100));
		music.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (getContext() instanceof VolumeControl) {
					((VolumeControl)getContext()).setVolume(seekBar.getProgress() / 100f);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				mVolume.setMusicVolume(seekBar.getProgress() / 100f);
			}
		});

		SeekBar effect = (SeekBar) view.findViewById(R.id.settings_effects_seek_bar);
		effect.setProgress((int) (mVolume.getEffectsVolume()*100));
		effect.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				mVolume.setEffectsVolume(seekBar.getProgress() / 100f);
			}
		});

		return view;
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public Fragment getNext() {
		return new MenuFragment();
	}

	@Override
	public void onPause() {
		super.onPause();
		mVolume.saveData();
	}
	/* МЕТОДЫ */
}
