package com.binarnahata.rainbowkingdom.Controllers;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Models.Droid;
import com.binarnahata.rainbowkingdom.Models.Speed;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.RKMainActivity;

/**
 * RainbowKingdom
 * Created on 03.12.15, 11:30
 *
 * @author bat
 * @version 0.1
 */
public class GameEngine extends BaseSurfaceView  {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = GameEngine.class.getSimpleName();
	private final Droid mDroid;
	private final MainThread mThread;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public GameEngine(Context context) {
		super(context);

		mContext = context;

		mDroid = new Droid(BitmapFactory.decodeResource(getResources(),
			R.drawable.droid_1), rndInt(0, 100), rndInt(0, 100));

		// create the game loop mThread
		mThread = new MainThread(getHolder(), this);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.setRunning(true);
		mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mThread.setRunning(false);
		Log.d(TAG, "Surface is being destroyed");
		boolean retry = true;
		while (retry) {
			try {
				mThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	public void render(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		//mDroid.draw(canvas);

		// display fps
		displayFps(canvas, avgFps);
	}

	public void update() {
		// check collision with right wall if heading right
		if (mDroid.getSpeed().getxDirection() == Speed.DIRECTION_RIGHT
			&& mDroid.getX() + mDroid.getBitmap().getWidth() / 2 >= getWidth()) {
			mDroid.getSpeed().toggleXDirection();
		}
		// check collision with left wall if heading left
		if (mDroid.getSpeed().getxDirection() == Speed.DIRECTION_LEFT
			&& mDroid.getX() - mDroid.getBitmap().getWidth() / 2 <= 0) {
			mDroid.getSpeed().toggleXDirection();
		}
		// check collision with bottom wall if heading down
		if (mDroid.getSpeed().getyDirection() == Speed.DIRECTION_DOWN
			&& mDroid.getY() + mDroid.getBitmap().getHeight() / 2 >= getHeight()) {
			mDroid.getSpeed().toggleYDirection();
		}
		// check collision with top wall if heading up
		if (mDroid.getSpeed().getyDirection() == Speed.DIRECTION_UP
			&& mDroid.getY() - mDroid.getBitmap().getHeight() / 2 <= 0) {
			mDroid.getSpeed().toggleYDirection();
		}
		// Update the lone droid
		mDroid.update();
	}

	public void displayFps(Canvas canvas, String fps) {
		if (canvas != null && fps != null) {
			Paint paint = new Paint();
			paint.setARGB(255, 255, 255, 255);
			canvas.drawText(fps, this.getWidth() - 50, 20, paint);
		}
	}

	// Return an integer that ranges from min inclusive to max inclusive.
	static int rndInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		Log.d(TAG, "Click");
		if (mContext instanceof RKMainActivity) {
			boolean retry = true;
			while (retry) {
				try {
					mThread.join();
					retry = false;
				} catch (InterruptedException e) {
					// try again shutting down the thread
				}
			}
			((RKMainActivity) mContext).runFragment(new MenuFragment());
		}
		else {
			Log.d(TAG, mContext.toString());
		}
		return true;
	}
	/* МЕТОДЫ */
}