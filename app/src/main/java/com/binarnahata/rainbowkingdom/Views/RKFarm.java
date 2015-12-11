package com.binarnahata.rainbowkingdom.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;
import com.binarnahata.rainbowkingdom.Fragments.MenuFragment;
import com.binarnahata.rainbowkingdom.Libs.DoublePoint;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;
import com.binarnahata.rainbowkingdom.Models.SimpleCircle;
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
	public static final int MAXIMUM_SPEED = 5;
	public static final int MAXIMUM_NUMBER_OF_CIRCLES = 20;
	private final Paint mPaint;
	private final Context mContext;
	private GameLoop mGameLoopThread;
	private ArrayList<SimpleCircle> mCircles;
	private SimpleCircle mShoot;
	private Canvas mCanvas;

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
		circle.setSpeed(new Speed(Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED), Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new SimpleCircle(Utils.rndInt(0, getWidth()),
			Utils.rndInt(0, getHeight()), mRadius, Color.GREEN);
		circle.setSpeed(new Speed(Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED), Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED)));
		mCircles.add(circle);

		circle = new SimpleCircle(Utils.rndInt(0, getWidth()),
			Utils.rndInt(0, getHeight()), mRadius, Color.BLUE);
		circle.setSpeed(new Speed(Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED), Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED)));
		mCircles.add(circle);
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
			Log.d(TAG, mShoot.centerToString());
			mShoot.moveOneStep();
			mShoot.checkBounds(getWidth(), getHeight());

			for (SimpleCircle circle : mCircles) {
				if (canMerge(circle.getColor(), mShoot.getColor())) {
					SimpleCircle tempCircle = checkCirclesCollisionsAndMerge(mShoot, circle);
					if (tempCircle != null) {
						mCircles.add(tempCircle);
						break;
					}
				}
				mShoot.checkCollisionsAndSetNewOptions(circle);
			}
		}
	}

	private boolean canMerge(int color1, int color2) {
		if (color1 == Color.RED || color1 == Color.BLUE || color1 == Color.GREEN) {
			if (color2 == Color.RED || color2 == Color.BLUE || color2 == Color.GREEN) {
				return true;
			}
		}
		return false;
	}

	private SimpleCircle checkCirclesCollisionsAndMerge(SimpleCircle circle1, SimpleCircle circle2) {
		double dx = circle1.getX()-circle2.getX();
		double dy = circle1.getY()-circle2.getY();
		double distanceBetweenCircles = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if (distanceBetweenCircles < mDiameter) { // если происходит столкновение
			SimpleCircle resultCircle = new SimpleCircle((int) ((circle1.getX() + circle2.getX()) / 2),
				(int) ((circle1.getY() + circle2.getY()) / 2), mRadius,
				getMergeColor(circle1.getColor(), circle2.getColor()));
			resultCircle.setSpeed(new Speed((circle1.getSpeed().getVectorX() + circle2.getSpeed().getVectorX()) / 2,
				(circle1.getSpeed().getVectorY() + circle2.getSpeed().getVectorY()) / 2));
			return resultCircle;
		}
		return null;
	}

	private int getMergeColor(int color1, int color2) {
		if (color1 == Color.RED) {
			if (color2 == Color.BLUE) {
				return Color.MAGENTA;
			}
			if (color2 == Color.GREEN) {
				return Color.YELLOW;
			}
			if (color2 == Color.RED) {
				return Color.RED;
			}
		}
		if (color1 == Color.BLUE) {
			if (color2 == Color.RED) {
				return Color.MAGENTA;
			}
			if (color2 == Color.GREEN) {
				return Color.CYAN;
			}
			if (color2 == Color.BLUE) {
				return Color.BLUE;
			}
		}
		if (color1 == Color.GREEN) {
			if (color2 == Color.RED) {
				return Color.YELLOW;
			}
			if (color2 == Color.BLUE) {
				return Color.CYAN;
			}
			if (color2 == Color.GREEN) {
				return Color.GREEN;
			}
		}
		return 0;
	}

	@Override
	public void render(Canvas canvas) {
		mCanvas = canvas;

		mCanvas.drawColor(Color.WHITE);

		for (SimpleCircle circle : mCircles) {
			/*String temp = new String();
			temp += String.valueOf(circle.getX());
			temp += " " + String.valueOf(circle.getY());
			temp += " " + String.valueOf(circle.getRadius());
			temp += " " + String.valueOf(circle.getColor());
			Log.d(TAG, temp);*/
			mPaint.setColor(circle.getColor());
			mCanvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), mPaint);
		}
		if (mShoot != null) {
			mPaint.setColor(mShoot.getColor());
			mCanvas.drawCircle(mShoot.getX(), mShoot.getY(), mShoot.getRadius(), mPaint);
		}
		/*for (SimpleCircle circle : mCircles) {
			Log.d(TAG, String.valueOf(circle.getX()) + " " + String.valueOf(circle.getY()));
		}*/
		/*mCanvas.drawColor(Color.BLACK);
		for (SimpleCircle circle : mCircles) {
			drawCircle(circle);
			Log.d(TAG, "drawCircle1");
		}*/
	}

	@Override
	public void drawCircle(SimpleCircle circle) {
		mPaint.setColor(circle.getColor());
		mCanvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			mShoot = new SimpleCircle(getWidth()/2, getHeight(), mRadius, Utils.rndColor());
			mShoot.setSpeed(calculationSpeedForNewCircle(event.getX(), event.getY(), getWidth(), getHeight()));
			if (mShoot.getSpeed() == null) {
				mShoot = null;
			}
		}

		if (mCircles.size() > MAXIMUM_NUMBER_OF_CIRCLES) {
			((RKMainActivity)mContext).runFragment(new MenuFragment());
		}
		return true;
	}

	private Speed calculationSpeedForNewCircle(float toX, float toY, int w, int h) {
		int t = w / 2;

		Utils.Ray ray = new Utils.Ray(new DoublePoint(w/2, h), new DoublePoint(toX, toY));

		DoublePoint intersection = Utils.rayIntersection(ray,
			new Utils.Segment(new DoublePoint(0, 0), new DoublePoint(0, h)));

		if (intersection == null) {
			intersection = Utils.rayIntersection(ray,
				new Utils.Segment(new DoublePoint(0, 0), new DoublePoint(w, 0)));
			if (intersection == null) {
				intersection = Utils.rayIntersection(ray,
					new Utils.Segment(new DoublePoint(w, 0), new DoublePoint(w, h)));
			}
		}

		if (intersection == null) {
			return null;
		}

		double distance = Utils.distanceBetweenTwoPoint(new DoublePoint(t, h), intersection);

		return new Speed(MAXIMUM_SPEED*(toX-t)/distance, MAXIMUM_SPEED*(h-toY)/distance);
	}
	/* МЕТОДЫ */
}