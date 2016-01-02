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
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Libs.DataBase.AchievementDatabaseHandler;
import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Models.Circles.DrawableCircle;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;
import com.binarnahata.rainbowkingdom.Models.GameMode.GameMode;
import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.GamePanel.BallPool;
import com.binarnahata.rainbowkingdom.Models.GamePanel.GamePanel;
import com.binarnahata.rainbowkingdom.Models.GamePanel.ResourceDisplay;
import com.binarnahata.rainbowkingdom.Models.GamePanel.TopPanel;
import com.binarnahata.rainbowkingdom.Models.Mark;
import com.binarnahata.rainbowkingdom.Models.Circles.RKCircle;
import com.binarnahata.rainbowkingdom.Models.Resources.Resources;
import com.binarnahata.rainbowkingdom.Models.Volume;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.Libs.Utils;
import com.binarnahata.rainbowkingdom.RKMainActivity;

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
	private final GameMode mGameMode;
	private final AchievementDatabaseHandler mDB;
	private int[] mSoundIndexes;
	private SoundPool mSoundPool;
	private GameLoop mGameLoopThread;
	private ArrayList<RKCircle> mCircles;
	private RKCircle mShoot;
	private Canvas mCanvas;
	private GamePanel mGamePanel;

	private int mRadius;
	private int mDiameter;
	private Rect mRectField;
	private Bitmap mBall;
	private BallPool mBallPool;
	private ResourceDisplay mResourceDisplay;
	private Volume mVolume;
	private Mark mMark;
	private TopPanel mTopPanel;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@TargetApi(21)
	public RKFarm(Context context, GameMode gameMode) {
		super(context);
		mContext = context;
		mGameLoopThread = new GameLoop(getHolder(), this);
		mCircles = new ArrayList<>();
		mPaint = new Paint();
		mGameMode = gameMode;

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

		mDB = AchievementDatabaseHandler.getInstance(context);
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
		mRectField = new Rect(mRadius, mRadius+mDiameter, getWidth()-mRadius, getHeight()-mDiameter*2);

		mBall = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

		mTopPanel = new TopPanel(mPaint, new Rect(0, 0, getWidth(), mDiameter), mBall);

		initCircles();

		mGamePanel = new GamePanel(new Rect(0, getHeight()-mDiameter, getWidth(), getHeight()),//mRectField,
			BitmapFactory.decodeResource(getResources(), R.drawable.game_panel_fon),
			BitmapFactory.decodeResource(getResources(), R.drawable.for_left),
			BitmapFactory.decodeResource(getResources(), R.drawable.for_right),
			mGameMode.number - START_NUMBER_OF_BALLS_ON_THE_FIELD);

		mBallPool = new BallPool(mBall, mDiameter, new Vector3(getWidth()/2, getHeight()-mDiameter));
		mResourceDisplay = new ResourceDisplay(mBall, mRadius, mGamePanel.mRectLeft);

		mMark = new Mark(mPaint);

		mVolume = Volume.getInstance(getContext());
	}

	private void initCircles() {
		RKCircle circle;
		for (int i = 0; i < START_NUMBER_OF_BALLS_ON_THE_FIELD; i++) {
			circle = new RKCircle(
				new Vector3(Utils.rndInt(mRectField.left, mRectField.right),
					Utils.rndInt(mRectField.top, mRectField.bottom)),
				mRadius,
				Color.getRandom(), mBall);
			circle.setSpeed(Vector3.getRandomNormal());
			mCircles.add(circle);
		}
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
		// движение шаров
		for (RKCircle circle : mCircles) {
			circle.move();
			circle.checkBounds(mRectField);
		}

		// разрешение коллизии между шарами
		for (RKCircle circle : mCircles) {
			for (int i = mCircles.indexOf(circle) + 1; i < mCircles.size(); i++) {
				RKCircle.solveCollision(circle, mCircles.get(i));
			}
		}

		// движение и коллизия выстрела
		if (mShoot != null) {
			mShoot.move();
			mShoot.checkBounds(mRectField);

			for (RKCircle circle : mCircles) {
				if (Color.canMerge(circle.getColor(), mShoot.getColor())) {
					RKCircle tempCircle = RKCircle.merge(circle, mShoot);
					if (tempCircle != null) {
						mSoundPool.play(mSoundIndexes[0],
							mVolume.getEffectsVolume(), mVolume.getEffectsVolume(),
							1, 0, 1.0f);
						mCircles.add(tempCircle);
						mGamePanel.incrementAvailableBalls();

						if (tempCircle.getColor() == circle.getColor()) {
							switch (tempCircle.getColor()) {
								case Color.RED:
									mResourceDisplay.setRed();
									break;
								case Color.GREEN:
									mResourceDisplay.setGreen();
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

						mMark.die();
						mShoot = null;
						break;
					}
				}
				else {
					if (RKCircle.solveCollision(circle, mShoot)) {
						mSoundPool.play(mSoundIndexes[1],
							mVolume.getEffectsVolume(), mVolume.getEffectsVolume(),
							1, 0, 1.0f);
						mCircles.add(mShoot);
						mMark.die();
						mShoot = null;
						break;
					}
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

		if (mTopPanel != null) {
			mTopPanel.draw(canvas);
		}

		for (RKCircle rkCircle : mCircles) {
			rkCircle.draw(mCanvas, mPaint);
		}

		if (mMark != null) {
			mMark.draw(canvas);
		}

		mGamePanel.draw(canvas);

		if (mShoot != null) {
			mShoot.draw(mCanvas, mPaint);
		}

		mBallPool.draw(canvas, mPaint);

		mResourceDisplay.draw(canvas, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (mShoot == null) {
				mShoot = mBallPool.getCircle();
				if (mShoot != null) {
					/*mShoot.setSpeed(Speed.getSpeedForShoot(new Rect(0, 0, getWidth(), getHeight()) new Rectangle(0, 0, getWidth(), getHeight()), new Point((int) event.getX(), (int) event.getY())));
					if (mShoot.getSpeed() == null) {
						mShoot.setSpeed(new Vector3(0, -1));
					}*/
					Vector3 speed = Vector3.sub(mShoot.getPosition(), new Vector3(event.getX(), event.getY()));
					speed.normalize();
					mShoot.setSpeed(speed);
					mMark.setCoordinate((int)event.getX(), (int) event.getY());
					mGamePanel.decrementAvailableBalls();
				}
			}

			if (mCircles.size() == mGameMode.number) {
				Resources resources = Resources.getInstance(getContext());
				resources.offset(
					mResourceDisplay.red.amount * mGameMode.rating,
					mResourceDisplay.green.amount * mGameMode.rating,
					mResourceDisplay.blue.amount * mGameMode.rating,
					mResourceDisplay.cyan.amount * mGameMode.rating,
					mResourceDisplay.magenta.amount * mGameMode.rating,
					mResourceDisplay.yellow.amount * mGameMode.rating);
				// тип игры 1
				mDB.offsetAchievementProgress(mGameMode.type, 1);

				int r = mResourceDisplay.red.amount * mGameMode.rating;
				int g = mResourceDisplay.green.amount * mGameMode.rating;
				int b = mResourceDisplay.blue.amount * mGameMode.rating;
				int c = mResourceDisplay.cyan.amount * mGameMode.rating;
				int m = mResourceDisplay.magenta.amount * mGameMode.rating;
				int y = mResourceDisplay.yellow.amount * mGameMode.rating;

				// количество булек каждого цвета 6 по 0.5
				mDB.offsetAchievementProgress(resources.RED, r);
				mDB.offsetAchievementProgress(resources.GREEN, g);
				mDB.offsetAchievementProgress(resources.BLUE, b);
				mDB.offsetAchievementProgress(resources.CYAN, c);
				mDB.offsetAchievementProgress(resources.MAGENTA, m);
				mDB.offsetAchievementProgress(resources.YELLOW, y);
				// просто больше 10ти булек за игру 1
				if ((r + g + b + c + m + y) > 100) {
					mDB.offsetAchievementProgress("number100", 1);
				}

				// одна ачивка согласно наличия булек определенных цветов 1
				r = 0;
				g = 0;
				b = 0;
				c = 0;
				m = 0;
				y = 0;
				for (DrawableCircle circle : mCircles) {
					switch(circle.getColor()) {
						case Color.RED:
							r++;
							break;
						case Color.GREEN:
							g++;
							break;
						case Color.BLUE:
							b++;
							break;
						case Color.CYAN:
							c++;
							break;
						case Color.MAGENTA:
							m++;
							break;
						case Color.YELLOW:
							y++;
							break;
					}
				}
				String tag = "color_";
				if (r > 0) {
					tag += "r";
				}
				if (g > 0) {
					tag += "g";
				}
				if (b > 0) {
					tag += "b";
				}
				if (c > 0) {
					tag += "c";
				}
				if (m > 0) {
					tag += "m";
				}
				if (y > 0) {
					tag += "y";
				}
				mDB.offsetAchievementProgress(tag, 1);

				((RKMainActivity)mContext).runFragment(new MenuFragment());
			}
		}
		return true;
	}
	/* МЕТОДЫ */
}