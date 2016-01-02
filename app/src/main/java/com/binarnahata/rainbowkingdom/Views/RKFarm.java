package com.binarnahata.rainbowkingdom.Views;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;
import com.binarnahata.rainbowkingdom.Libs.DataBase.AchievementDatabaseHandler;
import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Models.GameMode.GameMode;
import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.Refactoring.RKCircle;
import com.binarnahata.rainbowkingdom.Models.Volume;
import com.binarnahata.rainbowkingdom.R;
import com.binarnahata.rainbowkingdom.Libs.Utils;

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
	//private ArrayList<OldBitmapCircle> mCircles;
	private ArrayList<RKCircle> mNewCircles;
	//private OldBitmapCircle mShoot;
	private Canvas mCanvas;
	//private GamePanel mGamePanel;

	private int mRadius;
	private int mDiameter;
	private Rect mRectField;
	private Bitmap mBall;
	//private BallPool mBallPool;
	//private ResourceDisplay mResourceDisplay;
	//private Mark mMark;
	private Volume mVolume;
	private Bitmap mFon;
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	@TargetApi(21)
	public RKFarm(Context context, GameMode gameMode) {
		super(context);
		mContext = context;
		mGameLoopThread = new GameLoop(getHolder(), this);
		//mCircles = new ArrayList<>();
		mNewCircles = new ArrayList<>();
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
		mFon = BitmapFactory.decodeResource(getResources(), R.drawable.rk_fon);
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
		mRectField = new Rect(mRadius, mRadius, getWidth()-mRadius, getHeight()-mDiameter*2);

		mBall = BitmapFactory.decodeResource(getResources(), R.drawable.ball);

		/*OldBitmapCircle circle = new OldBitmapCircle(mBall, Utils.rndInt(mRectField.left, mRectField.right),
			Utils.rndInt(mRectField.top, mRectField.bottom), mRadius, Color.RED);
		circle.setSpeed(new Speed(Utils.rndInt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndInt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new OldBitmapCircle(mBall, Utils.rndInt(mRectField.left, mRectField.right),
			Utils.rndInt(mRectField.top, mRectField.bottom), mRadius, Color.GREEN);
		circle.setSpeed(new Speed(Utils.rndInt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndInt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new OldBitmapCircle(mBall, Utils.rndInt(mRectField.left, mRectField.right),
			Utils.rndInt(mRectField.top, mRectField.bottom), mRadius, Color.BLUE);
		circle.setSpeed(new Speed(Utils.rndInt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndInt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);*/

		initCircles();

		/*mGamePanel = new GamePanel(new Rect(0, getHeight()-mDiameter, getWidth(), getHeight()),//mRectField,
			BitmapFactory.decodeResource(getResources(), R.drawable.game_panel_fon),
			BitmapFactory.decodeResource(getResources(), R.drawable.for_left),
			BitmapFactory.decodeResource(getResources(), R.drawable.for_right),
			mGameMode.number - START_NUMBER_OF_BALLS_ON_THE_FIELD);*/

		/*mBallPool = new BallPool(mBall, mDiameter, new Point(getWidth()/2, getHeight()-mDiameter));
		mResourceDisplay = new ResourceDisplay(mBall, mRadius, mGamePanel.mRectLeft);

		mMark = new Mark(mPaint);*/

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
			mNewCircles.add(circle);
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
		for (RKCircle circle : mNewCircles) {
			circle.move();
			circle.checkBounds(mRectField);
		}

		// разрешение коллизии между шарами
		RKCircle circle2;
		for (RKCircle circle1 : mNewCircles) {
			for (int i = mNewCircles.indexOf(circle1) + 1; i < mNewCircles.size(); i++) {
				circle2 = mNewCircles.get(i);

				Vector3 delta = new Vector3(circle1.getX() - circle2.getX(), circle1.getY() - circle2.getY());
				double distanceBetweenCircles = delta.length();//Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

				if (distanceBetweenCircles <= mDiameter) {
					double p1 = delta.x * delta.y / Math.pow(distanceBetweenCircles, 2);
					double p2 = Math.pow(delta.x / distanceBetweenCircles, 2);
					double p3 = Math.pow(delta.y / distanceBetweenCircles, 2);

					Vector3 d = Vector3.sub(circle1.getSpeed(), circle2.getSpeed());
					Vector3 speedBalance = new Vector3(
						Vector3.dot(d, new Vector3(p2, p1)),
						Vector3.dot(d, new Vector3(p1, p3))
					);
					// изменение направления движения
					circle1.getSpeed().sub(speedBalance);
					circle2.getSpeed().add(speedBalance);

					// при соударении шары всегда "проникают" друг в друга, поэтому раздвигаем их
					p3 = (mDiameter - distanceBetweenCircles) / 2;
					delta.div(distanceBetweenCircles);
					delta.mul(p3);
					circle1.getPosition().add(delta);
					circle2.getPosition().sub(delta);
				}
			}
		}


		/*for (SimpleCircle circle : mCircles) {
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
			//mShoot.moveStaticStep();
			mShoot.checkBounds(mRectField);

			for (SimpleCircle circle : mCircles) {
				if (Color.canMerge(circle.getColor(), mShoot.getColor())) {
					OldBitmapCircle tempCircle = mShoot.checkCollisionsAndMerge(circle);
					if (tempCircle != null) {
						mSoundPool.play(mSoundIndexes[0],
							mVolume.getEffectsVolume(), mVolume.getEffectsVolume(),
							1, 0, 1.0f);
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
					mSoundPool.play(mSoundIndexes[1],
						mVolume.getEffectsVolume(), mVolume.getEffectsVolume(),
						1, 0, 1.0f);
					mCircles.add(mShoot);
					mMark.die();
					mShoot = null;
					break; //TODO: можно ли так?
				}
			}
		}

		if (mBallPool != null) {
			mBallPool.update();
		}
		if (mMark != null) {
			mMark.update();
		}*/
	}

	@Override
	public void render(Canvas canvas) {
		mCanvas = canvas;

		mCanvas.drawColor(Color.WHITE);

		mCanvas.drawBitmap(mFon, null,
			new Rect(0, 0, getWidth(), getHeight()),
			null);

		for (RKCircle rkCircle : mNewCircles) {
			rkCircle.draw(mCanvas, mPaint);
		}

		/*mMark.draw(canvas);

		for (OldBitmapCircle circle : mCircles) {
			circle.draw(mCanvas, mPaint);
		}
		if (mShoot != null) {
			mShoot.draw(mCanvas, mPaint);
		}

		mBallPool.draw(canvas, mPaint);
		mGamePanel.draw(canvas);
		mResourceDisplay.draw(canvas, mPaint);*/
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			/*if (mShoot == null) {
				mShoot = mBallPool.getCircle(); //new OldBitmapCircle(mBall, getWidth() / 2, getHeight(), mRadius, Utils.rndColor());
				if (mShoot != null) {
					mShoot.setSpeed(Speed.getSpeedForShoot(new Rect(0, 0, getWidth(), getHeight())*//*new Rectangle(0, 0, getWidth(), getHeight())*//*, new Point((int) event.getX(), (int) event.getY())));  //calculationSpeedForNewCircle(event.getX(), event.getY(), getWidth(), getHeight()));
					if (mShoot.getSpeed() == null) {
						mShoot.setSpeed(new Speed(0, 1.0));
					}
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
				for (OldBitmapCircle circle : mCircles) {
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
			}*/
		}
		return true;
	}
	/* МЕТОДЫ */
}