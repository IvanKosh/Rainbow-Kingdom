package com.binarnahata.rainbowkingdom.Views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Libs.DoublePoint;
import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;
import com.binarnahata.rainbowkingdom.Models.GamePanel;
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
	public static final int MAXIMUM_NUMBER_OF_CIRCLES = 20;
	private final Paint mPaint;
	private final Context mContext;
	private GameLoop mGameLoopThread;
	private ArrayList<SimpleCircle> mCircles;
	private SimpleCircle mShoot;
	private Canvas mCanvas;
	private GamePanel mGamePanel;

	private int mRadius;
	private int mDiameter;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public RKFarm(Context context) {
		super(context);
		mContext = context;
		mGameLoopThread = new GameLoop(getHolder(), this);
		mCircles = new ArrayList<>();
		mPaint = new Paint();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mGameLoopThread.setRunning(true);
		mGameLoopThread.start();

		mRadius = getWidth() < getHeight() ? getWidth()/20 : getHeight()/20;
		mDiameter = mRadius << 1;

		SimpleCircle circle = new SimpleCircle(Utils.rndInt(0, getWidth()),
			Utils.rndInt(0, getHeight()), mRadius, Color.RED);
		circle.setSpeed(new Speed(Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new SimpleCircle(Utils.rndInt(0, getWidth()),
			Utils.rndInt(0, getHeight()), mRadius, Color.GREEN);
		circle.setSpeed(new Speed(Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new SimpleCircle(Utils.rndInt(0, getWidth()),
			Utils.rndInt(0, getHeight()), mRadius, Color.BLUE);
		circle.setSpeed(new Speed(Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED), Utils.rndFlt(-Speed.MAXIMUM_SPEED, Speed.MAXIMUM_SPEED)));
		mCircles.add(circle);

		mGamePanel = new GamePanel(
			BitmapFactory.decodeResource(getResources(), R.drawable.game_panel_fon),
			BitmapFactory.decodeResource(getResources(), R.drawable.gun));
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
			circle.checkBounds(getWidth(), getHeight());
		}

		// разрешение коллизии между шарами
		for (SimpleCircle circle : mCircles) {
			for (int i = mCircles.indexOf(circle) + 1; i < mCircles.size(); i++) {
				circle.checkCollisionsAndSetNewOptions(mCircles.get(i));
			}
		}

		// движение выстрела и
		if (mShoot != null) {
			mShoot.moveOneStep();
			mShoot.checkBounds(getWidth(), getHeight());

			for (SimpleCircle circle : mCircles) {
				if (Color.canMerge(circle.getColor(), mShoot.getColor())) {
					SimpleCircle tempCircle = mShoot.checkCollisionsAndMerge(circle);
					if (tempCircle != null) {
						mCircles.add(tempCircle);
						mCircles.remove(circle);
						mShoot = null;
						break;
					}
				}
				if (mShoot.checkCollisionsAndSetNewOptions(circle)) {
					mCircles.add(mShoot);
					mShoot = null;
					break; //TODO: можно ли так?
				}
			}
		}
	}

	@Override
	public void render(Canvas canvas) {
		mCanvas = canvas;

		mCanvas.drawColor(Color.WHITE);

		for (SimpleCircle circle : mCircles) {
			mPaint.setColor(circle.getColor());
			mCanvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), mPaint);
		}
		if (mShoot != null) {
			mPaint.setColor(mShoot.getColor());
			mCanvas.drawCircle(mShoot.getX(), mShoot.getY(), mShoot.getRadius(), mPaint);
		}

		mGamePanel.draw(canvas, new Rect(0, getHeight()-mDiameter, getWidth(), getHeight()));
	}

	@Override
	public void drawCircle(SimpleCircle circle) {
		mPaint.setColor(circle.getColor());
		mCanvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), mPaint);
	}

	@Override
	public void drawImage(Canvas canvas) {

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			if (mShoot == null) {
				mShoot = new SimpleCircle(getWidth() / 2, getHeight(), mRadius, Utils.rndColor());
				mShoot.setSpeed(Speed.getSpeedForShoot(new Rect(0, 0, getWidth(), getHeight())/*new Rectangle(0, 0, getWidth(), getHeight())*/, new DoublePoint(event.getX(), event.getY())));  //calculationSpeedForNewCircle(event.getX(), event.getY(), getWidth(), getHeight()));
				if (mShoot.getSpeed() == null) {
					mShoot = null;
				}
			}
		}

		if (mCircles.size() > MAXIMUM_NUMBER_OF_CIRCLES) {
			((RKMainActivity)mContext).runFragment(new MenuFragment());
		}
		return true;
	}
	/* МЕТОДЫ */
}