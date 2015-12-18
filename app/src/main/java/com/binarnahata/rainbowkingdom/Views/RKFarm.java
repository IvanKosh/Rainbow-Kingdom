package com.binarnahata.rainbowkingdom.Views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Fragments.ResourcesFragment;
import com.binarnahata.rainbowkingdom.Libs.DoublePoint;
import com.binarnahata.rainbowkingdom.Models.BallPool;
import com.binarnahata.rainbowkingdom.Models.BitmapCircle;
import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;
import com.binarnahata.rainbowkingdom.Models.GamePanel;
import com.binarnahata.rainbowkingdom.Models.Mark;
import com.binarnahata.rainbowkingdom.Models.ResourceDisplay;
import com.binarnahata.rainbowkingdom.Models.SimpleCircle;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.RKMainActivity;
import com.binarnahata.rainbowkingdom.Utils;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 06.12.15, 14:00
 *
 * @author bat
 * @version 0.1
 */
public class RKFarm extends BH_SurfaceView {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = RKFarm.class.getSimpleName();
	public static final int START_NUMBER_OF_BALLS_ON_THE_FIELD = 3;
	private final Paint mPaint;
	private final Context mContext;
	private int[] mSoundIndexes;
	private SoundPool mSoundPool;
	private GameLoop mGameLoopThread;
	private ArrayList<BitmapCircle> mCircles;
	private BitmapCircle mShoot;
	private Canvas mCanvas;
	private GamePanel mGamePanel;

	private int mRadius;
	private int mDiameter;
	private Rect mRectField;
	private Bitmap mBall;
	private BallPool mBallPool;
	private ResourceDisplay mResourceDisplay;
	private Mark mMark;
	private int mMaximumNumberOfCircles;
	private int mRating;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@TargetApi(21)
	public RKFarm(Context context, int number, int rating) {
		super(context);
		mContext = context;
		mGameLoopThread = new GameLoop(getHolder(), this);
		mCircles = new ArrayList<>();
		mPaint = new Paint();
		mMaximumNumberOfCircles = number;
		mRating = rating;

		if (android.os.Build.VERSION.SDK_INT < 21) {
			setSoundPool17();
		}
		else {
			setSoundPool21();
		}

		//setVolumeControlStream(AudioManager.STREAM_MUSIC);

		mSoundIndexes = new int[10];
		mSoundIndexes[0] = mSoundPool.load(context, R.raw.water_drop, 1);
		mSoundIndexes[1] = mSoundPool.load(context, R.raw.pool_ball, 1);
	}

	@TargetApi(17)
	private void setSoundPool17() {
		mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	}

	@TargetApi(21)
	private void setSoundPool21() {
		AudioAttributes attrs = new AudioAttributes.Builder()
			.setUsage(AudioAttributes.USAGE_GAME)
			.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
			.build();
		mSoundPool = new SoundPool.Builder()
			.setMaxStreams(10)
			.setAudioAttributes(attrs)
			.build();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mGameLoopThread.setRunning(true);
		mGameLoopThread.start();

		mRadius = getWidth() < getHeight() ? getWidth()/20 : getHeight()/20;
		mDiameter = mRadius << 1;
		mRectField = new Rect(mRadius, mRadius, getWidth()-mRating, getHeight()-mDiameter*2);

		mBall = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

		BitmapCircle circle = new BitmapCircle(mBall, Utils.rndInt(mRectField.left, mRectField.right),
			Utils.rndInt(mRectField.top, mRectField.bottom), mRadius, Color.RED);
		circle.setSpeed(new Speed(Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new BitmapCircle(mBall, Utils.rndInt(mRectField.left, mRectField.right),
			Utils.rndInt(mRectField.top, mRectField.bottom), mRadius, Color.GREEN);
		circle.setSpeed(new Speed(Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new BitmapCircle(mBall, Utils.rndInt(mRectField.left, mRectField.right),
			Utils.rndInt(mRectField.top, mRectField.bottom), mRadius, Color.BLUE);
		circle.setSpeed(new Speed(Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		mGamePanel = new GamePanel(new Rect(0, getHeight()-mDiameter, getWidth(), getHeight()),//mRectField,
			BitmapFactory.decodeResource(getResources(), R.drawable.game_panel_fon),
			BitmapFactory.decodeResource(getResources(), R.drawable.for_left),
			BitmapFactory.decodeResource(getResources(), R.drawable.for_right),
			mMaximumNumberOfCircles - START_NUMBER_OF_BALLS_ON_THE_FIELD);

		mBallPool = new BallPool(mBall, mDiameter, new Point(getWidth()/2, getHeight()-mDiameter));
		mResourceDisplay = new ResourceDisplay(mBall, mRadius, mGamePanel.mRectLeft);

		mMark = new Mark(mPaint);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mGameLoopThread.setRunning(false);
		Log.d(TAG, "Surface is being destroyed");
		boolean retry = true;
		while (retry) {
			try {
				Log.d(TAG, "try");
				mGameLoopThread.join();
				retry = false;
				Log.d(TAG, "join");
			} catch (InterruptedException e) {
				// try again shutting down the thread
				Log.d(TAG, e.toString());
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
		mSoundPool.release();
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void update() {
		// движение по плоскости
		for (SimpleCircle circle : mCircles) {
			circle.moveOneStep();
			circle.checkBounds(mRectField);
		}

		// разрешение коллизии между шарами
		for (SimpleCircle circle : mCircles) {
			for (int i = mCircles.indexOf(circle) + 1; i < mCircles.size(); i++) {
				circle.checkCollisionsAndSetNewOptions(mCircles.get(i));
			}
		}

		// движение и коллизия выстрела
		if (mShoot != null) {
			mShoot.moveOneStep();
			mShoot.checkBounds(mRectField);

			for (SimpleCircle circle : mCircles) {
				if (Color.canMerge(circle.getColor(), mShoot.getColor())) {
					BitmapCircle tempCircle = mShoot.checkCollisionsAndMerge(circle);
					if (tempCircle != null) {
						mSoundPool.play(mSoundIndexes[0], 1, 1, 1, 0, 1.0f);
						mCircles.add(tempCircle);
						mCircles.remove(circle);

						if (tempCircle.getColor() == circle.getColor()) {
							switch (tempCircle.getColor()) {
								case Color.RED:
									mResourceDisplay.setRed();
									break;
								case Color.GREEN:
									mResourceDisplay.setGreed();
									break;
								case Color.BLUE:
									mResourceDisplay.setBlue();
									break;
								case Color.CYAN:
									mResourceDisplay.setCyan();
									break;
								case Color.MAGENTA:
									mResourceDisplay.setMagenta();
									break;
								case Color.YELLOW:
									mResourceDisplay.setYellow();
									break;
							}
						}

						mShoot = null;
						mGamePanel.incrementAvailableBalls();
						mMark.die();
						break;
					}
				}
				if (mShoot.checkCollisionsAndSetNewOptions(circle)) {
					mSoundPool.play(mSoundIndexes[1], 1, 1, 1, 0, 1.0f);
					mCircles.add(mShoot);
					mMark.die();
					mShoot = null;
					break; //TODO: можно ли так?
				}
			}
		}

		mBallPool.update();
		mMark.update();
	}

	@Override
	public void render(Canvas canvas) {
		mCanvas = canvas;

		mCanvas.drawColor(Color.WHITE);

		mMark.draw(canvas);

		for (BitmapCircle circle : mCircles) {
			circle.draw(mCanvas, mPaint);
		}
		if (mShoot != null) {
			mShoot.draw(mCanvas, mPaint);
		}

		mBallPool.draw(canvas, mPaint);
		mGamePanel.draw(canvas);
		mResourceDisplay.draw(canvas, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (mShoot == null) {
				mShoot = mBallPool.getCircle(); //new BitmapCircle(mBall, getWidth() / 2, getHeight(), mRadius, Utils.rndColor());
				if (mShoot != null) {
					mShoot.setSpeed(Speed.getSpeedForShoot(new Rect(0, 0, getWidth(), getHeight())/*new Rectangle(0, 0, getWidth(), getHeight())*/, new DoublePoint(event.getX(), event.getY())));  //calculationSpeedForNewCircle(event.getX(), event.getY(), getWidth(), getHeight()));
					if (mShoot.getSpeed() == null) {
						mShoot.setSpeed(new Speed(0, 1.0));
					}
					mMark.setCoordinate((int)event.getX(), (int) event.getY());
					mGamePanel.decrementAvailableBalls();
				}
			}

			if (mCircles.size() == mMaximumNumberOfCircles) {
				ResourcesFragment.offsetAmounts(getContext(),
					mResourceDisplay.red.amount * mRating,
					mResourceDisplay.green.amount * mRating,
					mResourceDisplay.blue.amount * mRating,
					mResourceDisplay.cyan.amount * mRating,
					mResourceDisplay.magenta.amount * mRating,
					mResourceDisplay.yellow.amount * mRating
				);
				((RKMainActivity)mContext).runFragment(new MenuFragment());
			}
		}
		return true;
	}
	/* МЕТОДЫ */
}