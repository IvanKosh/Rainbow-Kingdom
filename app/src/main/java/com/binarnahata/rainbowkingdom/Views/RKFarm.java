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
	public static final int MAXIMUM_SPEED = 1;
	public static final int MAXIMUM_NUMBER_OF_CIRCLES = 20;
	public static final int START_COUNT_CIRCLES = 0;
	private final Paint mPaint;
	private final Context mContext;
	private GameLoop mGameLoopThread;
	private ArrayList<SimpleCircle> mCircles;
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
		Log.d(TAG, "create");
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

		Log.d(TAG, String.valueOf(getHeight()));
		Log.d(TAG, String.valueOf(getWidth()));

		// TODO: delete
		for (int i = 0; i < START_COUNT_CIRCLES; i++) {
			SimpleCircle circle = new SimpleCircle(Utils.rndInt(0, getWidth()),
				Utils.rndInt(0, getHeight()), mRadius, Utils.rndColor());
			circle.setSpeed(new Speed(Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED), Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED)));
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
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void update() {
		for (SimpleCircle circle : mCircles) {
			circle.moveOneStep();
			checkBounds(circle);
		}

		for (SimpleCircle circle1 : mCircles) {
			SimpleCircle circle2;
			for (int i = mCircles.indexOf(circle1) + 1; i < mCircles.size(); i++) {
				circle2 = mCircles.get(i);

				checkCirclesCollisionsAndSetNewOptions(circle1, circle2);
			}
		}
	}

	private void checkCirclesCollisionsAndSetNewOptions(SimpleCircle circle1, SimpleCircle circle2) {
		double dx = circle1.getX()-circle2.getX();
		double dy = circle1.getY()-circle2.getY();
		double distanceBetweenCircles = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
		if (distanceBetweenCircles < mDiameter) { // если происходит столкновение
			// вычисление новых параметров
			double p1 = dx * dy / Math.pow(distanceBetweenCircles, 2);
			double p2 = Math.pow(dx / distanceBetweenCircles, 2);
			double p3 = Math.pow(dy / distanceBetweenCircles, 2);

			double d1 = circle1.getSpeed().getVectorY() * p1
				+ circle1.getSpeed().getVectorX() * p2
				- circle2.getSpeed().getVectorY() * p1
				- circle2.getSpeed().getVectorX() * p2;
			double d2 = circle1.getSpeed().getVectorX() * p1
				+ circle1.getSpeed().getVectorY() * p3
				- circle2.getSpeed().getVectorX() * p1
				- circle2.getSpeed().getVectorY() * p3;

			// изменение направления движения
			circle1.setSpeed(new Speed(circle1.getSpeed().getVectorX() - d1, circle1.getSpeed().getVectorY() - d2));
			circle2.setSpeed(new Speed(circle2.getSpeed().getVectorX() + d1, circle2.getSpeed().getVectorY() + d2));

			// при соударении шары всегда "проникают" друг в друга, поэтому раздвигаем их
			p3 = (mDiameter - distanceBetweenCircles) / 2; //при соударении шары всегда "проникают" друг в друга, поэтому раздвигаем их
			p1 = p3 * (dx / distanceBetweenCircles);
			p2 = p3 * (dy / distanceBetweenCircles);
			circle1.setX((float) (circle1.getX() + p1));
			circle1.setY((float) (circle1.getY() + p2));
			circle2.setX((float) (circle2.getX() - p1));
			circle2.setY((float) (circle2.getY() - p2));
		}
	}

	private void checkBounds(SimpleCircle circle) {
		if (circle.getX() < 0) {
			// нужно увеличивать X
			circle.getSpeed().toRight();
		}
		if (getWidth() < circle.getX()) {
			// нужно уменьшать X
			circle.getSpeed().toLeft();
		}

		if (circle.getY() < 0) {
			// нужно увеличивать Y
			circle.getSpeed().toDown();
		}
		if (getHeight() < circle.getY()) {
			// нужно уменьшать Y
			circle.getSpeed().toUp();
		}
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
		Log.d(TAG, String.valueOf(event.getX()) + " " + String.valueOf(event.getY()));

		if (event.getAction() == MotionEvent.ACTION_UP) {
			/*SimpleCircle circle = new SimpleCircle((int)event.getX(),
				(int)event.getY(), mRadius, Utils.rndColor());
			circle.setSpeed(new Speed(Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED), Utils.rndFlt(-MAXIMUM_SPEED, MAXIMUM_SPEED)));*/
			SimpleCircle circle = new SimpleCircle(getWidth()/2, getHeight(), mRadius, Utils.rndColor());
			circle.setSpeed(calculationSpeedForNewCircle(event.getX(), event.getY(), getWidth(), getHeight()));
			if (circle.getSpeed() != null) {
				mCircles.add(circle);
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

		return new Speed(MAXIMUM_SPEED*(toX-t)/(intersection.x-t), MAXIMUM_SPEED*(toY-h)/(intersection.y-h));

		/*int sX = w / 2;
		int sY = h;
		double x = -sX - (sY*(toX-sX)/(toY-sY));
		double y = 0;

		if (x < 0) {
			x = 0;
			Log.d(TAG, "x < 0");
		}
		if (w < x) {
			x = w;
			Log.d(TAG, "x > w");
		}
		y = sY + (x-sX)*(toY-sY)/(toX-sX);

		Log.d(TAG, "new");
		return new Speed(MAXIMUM_SPEED*(sX-toX)/(sX-x), MAXIMUM_SPEED*(sY-toY)/(sY-y));*/
	}
	/* МЕТОДЫ */
}