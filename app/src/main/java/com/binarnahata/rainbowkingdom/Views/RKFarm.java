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
import com.binarnahata.rainbowkingdom.Models.GameMode.GameMode;
import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.GamePanel.BallPool;
import com.binarnahata.rainbowkingdom.Models.GamePanel.BottomPanel;
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
	private GameState mGameState;
	private int[] mSoundIndexes;
	private SoundPool mSoundPool;
	private GameLoop mGameLoopThread;
	private ArrayList<RKCircle> mCircles;
	private RKCircle mShoot;
	private Canvas mCanvas;

	private int mRadius;
	private int mDiameter;
	private Rect mRectField;
	private Bitmap mBall;
	private BallPool mBallPool;
	private ResourceDisplay mResourceDisplay;
	private Volume mVolume;
	private Mark mMark;
	private TopPanel mTopPanel;
	private Bitmap mLineH;
	private BottomPanel mBottomPanel;
	private Rect mForRect;
	private Bitmap mFon;
	private Rect mForTouch;
	public static int sMaxSpeed;
	private String mGameOverText;
	private Point mGameOverPosition;
	private String mTapText;
	private Point mTapPosition;

	private interface GameState {
		void onTouch(MotionEvent event);
	}

	private class PlayState implements GameState {
		@Override
		public void onTouch(MotionEvent event) {
			shoot(event);
		}
	}

	private class OverState implements GameState {
		@Override
		public void onTouch(MotionEvent event) {
			endGame(event);
		}
	}
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
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
		mSoundIndexes[2] = mSoundPool.load(context, R.raw.glass_cling, 1);

		mDB = AchievementDatabaseHandler.getInstance(context);

		mGameState = new PlayState();
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
		mForRect = new Rect(0, 0, getWidth(), getHeight());

		mRadius = getWidth() < getHeight() ? getWidth()/20 : getHeight()/20;
		sMaxSpeed = mRadius >> 2;
		mDiameter = mRadius << 1;
		mForTouch = new Rect(0, 0, getWidth(), getHeight()-mDiameter*2);
		mRectField = new Rect(mRadius, mRadius+mDiameter, getWidth()-mRadius, mForTouch.bottom);

		mFon = BitmapFactory.decodeResource(getResources(), R.drawable.fon);
		mBall = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		mLineH = BitmapFactory.decodeResource(getResources(), R.drawable.line_h);
		mTopPanel = new TopPanel(mPaint, new Rect(0, 0, getWidth(), mDiameter), mBall, mLineH);
		mBottomPanel = new BottomPanel(new Rect(0, getHeight()-mDiameter, getWidth(), getHeight()),
			mLineH,
			BitmapFactory.decodeResource(getResources(), R.drawable.line_v),
			BitmapFactory.decodeResource(getResources(), R.drawable.corner_bl),
			BitmapFactory.decodeResource(getResources(), R.drawable.corner_br),
			mGameMode.number - START_NUMBER_OF_BALLS_ON_THE_FIELD);

		initCircles();

		mBallPool = new BallPool(mBall, mDiameter, new Vector3(getWidth()/2, getHeight()-mDiameter));
		mResourceDisplay = new ResourceDisplay(mBall, mRadius, mBottomPanel.mRectLeft);

		mMark = new Mark(mPaint);

		mVolume = Volume.getInstance(getContext());

		mGameLoopThread.setRunning(true);
		mGameLoopThread.start();

		Rect r = new Rect();
		mGameOverText = "Game Over";
		mPaint.setTextSize(mDiameter*2);
		mPaint.getTextBounds(mGameOverText, 0, mGameOverText.length(), r);
		mGameOverPosition = new Point((int) (getWidth() / 2f - r.width() / 2f - r.left),
			(int) (getHeight() / 2f + r.height() / 2f - r.bottom - mDiameter));
		mTapText = "Tap to back.";
		mPaint.setTextSize(mDiameter);
		mPaint.getTextBounds(mTapText, 0, mTapText.length(), r);
		mTapPosition = new Point((int) (getWidth() / 2f - r.width() / 2f - r.left),
			(int) (getHeight() / 2f + r.height() / 2f - r.bottom + mDiameter));
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
						mBottomPanel.incrementAvailableBalls();

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
						mCircles.remove(circle);
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
						if (mBottomPanel.getAvailableBalls() == 0) {
							gameOver();
							mGameState = new OverState();
						}
						break;
					}
				}
			}
		}

		mBallPool.update();

		mMark.update();
	}

	private void gameOver() {
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
	}

	private void endGame(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			((RKMainActivity)mContext).runFragment(new MenuFragment());
		}
	}

	@Override
	public void render(Canvas canvas) {
		mCanvas = canvas;

		mCanvas.drawColor(Color.WHITE);
		mCanvas.drawBitmap(mFon, null, mForRect, null);

		mBallPool.draw(canvas, mPaint);

		if (mMark != null) {
			mMark.draw(canvas);
		}

		for (RKCircle rkCircle : mCircles) {
			rkCircle.draw(mCanvas, mPaint);
		}

		if (mShoot != null) {
			mShoot.draw(mCanvas, mPaint);
		}

		//if (mTopPanel != null) {
		mTopPanel.draw(canvas);
		//}

		//mGamePanel.draw(canvas);
		mBottomPanel.draw(canvas);

		mResourceDisplay.draw(canvas, mPaint);

		if (mGameState instanceof OverState) {
			drawGameOver();
		}
	}

	private void drawGameOver() {
		mPaint.setColor(Color.WHITE);
		mPaint.setTextSize(mDiameter * 2);
		mCanvas.drawText(mGameOverText, mGameOverPosition.x, mGameOverPosition.y, mPaint);
		mPaint.setTextSize(mDiameter);
		mCanvas.drawText(mTapText, mTapPosition.x, mTapPosition.y, mPaint);
	}

	private void shoot(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			if (mForTouch.contains(x, y)) {
				if (mShoot == null) {
					mShoot = mBallPool.getCircle();
					if (mShoot != null) {
						Vector3 speed = Vector3.sub(new Vector3(event.getX(), event.getY()), mShoot.getPosition());
						speed.normalize();
						mShoot.setSpeed(speed, sMaxSpeed);
						mMark.setCoordinate((int)event.getX(), (int) event.getY());
						mBottomPanel.decrementAvailableBalls();
					}
					else {
						// not reload yet
						youCantShoot(x, y);
					}
				}
				else {
					// wait
					youCantShoot(x, y);
				}
			}
		}
	}

	private void youCantShoot(int x, int y) {
		mSoundPool.play(mSoundIndexes[2],
			mVolume.getEffectsVolume(), mVolume.getEffectsVolume(),
			1, 0, 1.0f);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGameState.onTouch(event);
		return true;
	}
	/* МЕТОДЫ */
}