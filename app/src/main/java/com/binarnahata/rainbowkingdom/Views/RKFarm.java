package com.binarnahata.rainbowkingdom.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.binarnahata.rainbowkingdom.Controllers.GameLoop;
import com.binarnahata.rainbowkingdom.Models.Components.Speed;
import com.binarnahata.rainbowkingdom.Models.SimpleCircle;
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
	private final Paint mPaint;
	private GameLoop mGameLoopThread;
	private ArrayList<SimpleCircle> mCircles;
	private Canvas mCanvas;

	private int mRadius;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public RKFarm(Context context) {
		super(context);
		Log.d(TAG, "create");
		mGameLoopThread = new GameLoop(getHolder(), this);
		mCircles = new ArrayList<>();
		mPaint = new Paint();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mGameLoopThread.setRunning(true);
		mGameLoopThread.start();

		mRadius = getWidth() < getHeight() ? getWidth()/10 : getHeight()/10;

		Log.d(TAG, String.valueOf(getHeight()));
		Log.d(TAG, String.valueOf(getWidth()));

		// TODO: delete
		for (int i = 0; i < 3; i++) {
			SimpleCircle circle = new SimpleCircle(Utils.rndInt(0, getWidth()),
				Utils.rndInt(0, getHeight()), mRadius, Utils.rndColor());
			circle.setSpeed(new Speed(Utils.rndFlt(-1, 1), Utils.rndFlt(-1, 1)));
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
	}

	private void checkBounds(SimpleCircle circle) {
		if (circle.getX() < 0 || getWidth() < circle.getX()) {
			circle.getSpeed().toggleXDirection();
		}
		if (circle.getY() < 0 || getHeight() < circle.getY()) {
			circle.getSpeed().toggleYDirection();
		}
	}

	@Override
	public void render(Canvas canvas) {
		mCanvas = canvas;

		mCanvas.drawColor(Color.WHITE);

		for (SimpleCircle circle : mCircles) {
			String temp = new String();
			temp += String.valueOf(circle.getX());
			temp += " " + String.valueOf(circle.getY());
			temp += " " + String.valueOf(circle.getRadius());
			temp += " " + String.valueOf(circle.getColor());
			Log.d(TAG, temp);
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
		return true;
	}
	/* МЕТОДЫ */
}