package com.binarnahata.rainbowkingdom;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.binarnahata.rainbowkingdom.Models.Volume;

/**
 * RainbowKingdom
 * Created on 17.12.15, 21:26
 *
 * @author bat
 * @version 0.1
 */
public class BackgroundMusicService extends Service implements MediaPlayer.OnErrorListener {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = BackgroundMusicService.class.getSimpleName();

	private final IBinder mBinder = new ServiceBinder();
	//private final Context mContext;
	MediaPlayer mPlayer;
	private int length = 0;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public class ServiceBinder extends Binder {
		public BackgroundMusicService getService() {
			return BackgroundMusicService.this;
		}
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mPlayer = MediaPlayer.create(this, R.raw.la_ere_gymnopedie);
		mPlayer.setOnErrorListener(this);

		if (mPlayer != null) {
			mPlayer.setLooping(true);
			setVolume(0);
		}

		mPlayer.setOnErrorListener(new OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				onError(mPlayer, what, extra);
				return true;
			}

		});
	}

	public int onStartCommand (Intent intent , int flags, int startId) {
		mPlayer.start();
		return START_STICKY;
	}

	public void pauseMusic() {
		if (mPlayer.isPlaying()) {
			mPlayer.pause();
			length=mPlayer.getCurrentPosition();
		}
	}

	public void resumeMusic() {
		if (mPlayer.isPlaying() == false) {
			mPlayer.seekTo(length);
			mPlayer.start();
		}
	}

	public void stopMusic() {
		mPlayer.stop();
		mPlayer.release();
		mPlayer = null;
	}

	public void setVolume(float volume) {
		if (mPlayer != null) {
			mPlayer.setLooping(true);
			mPlayer.setVolume(volume, volume);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mPlayer != null) {
			try {
				mPlayer.stop();
				mPlayer.release();
			}
			finally {
				mPlayer = null;
			}
		}
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub

		Toast.makeText(this, "Music player failed", Toast.LENGTH_SHORT).show();
		if(mPlayer != null)
		{
			try{
				mPlayer.stop();
				mPlayer.release();
			}finally {
				mPlayer = null;
			}
		}

		return false;

	}
	/* МЕТОДЫ */
}