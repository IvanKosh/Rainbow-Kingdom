package com.binarnahata.rainbowkingdom.Views;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.binarnahata.rainbowkingdom.R;

/**
 * RainbowKingdom
 * Created on 17.12.15, 13:07
 *
 * @author bat
 * @version 0.1
 */
public class MusicService extends Service  implements MediaPlayer.OnErrorListener {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = MusicService.class.getSimpleName();

	private final IBinder mBinder = new ServiceBinder();
	MediaPlayer mPlayer;
	private int length = 0;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public MusicService() { }

	@Override
	public void onCreate (){
		super.onCreate();

		mPlayer = MediaPlayer.create(this, R.raw.la_ere_gymnopedie);
		mPlayer.setOnErrorListener(this);

		if(mPlayer!= null)
		{
			mPlayer.setLooping(true);
			mPlayer.setVolume(100,100);
		}


		mPlayer.setOnErrorListener(new OnErrorListener() {

			public boolean onError(MediaPlayer mp, int what, int
				extra){

				onError(mPlayer, what, extra);
				return true;
			}
		});
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public class ServiceBinder extends Binder {
		MusicService getService()
		{
			return MusicService.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0){return mBinder;}

	@Override
	public int onStartCommand (Intent intent, int flags, int startId)
	{
		mPlayer.start();
		return START_STICKY;
	}

	public void pauseMusic()
	{
		if(mPlayer.isPlaying())
		{
			mPlayer.pause();
			length=mPlayer.getCurrentPosition();

		}
	}

	public void resumeMusic()
	{
		if(mPlayer.isPlaying()==false)
		{
			mPlayer.seekTo(length);
			mPlayer.start();
		}
	}

	public void stopMusic()
	{
		mPlayer.stop();
		mPlayer.release();
		mPlayer = null;
	}

	@Override
	public void onDestroy ()
	{
		super.onDestroy();
		if(mPlayer != null)
		{
			try{
				mPlayer.stop();
				mPlayer.release();
			}finally {
				mPlayer = null;
			}
		}
	}

	public boolean onError(MediaPlayer mp, int what, int extra) {

		Toast.makeText(this, "music player failed", Toast.LENGTH_SHORT).show();
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