package com.binarnahata.rainbowkingdom.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * RainbowKingdom
 * Created on 30.11.15, 18:50
 *
 * @author bat
 * @version 0.1
 */
public class AndroidCanvasView extends View implements BH_CanvasView {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private Canvas mCanvas;
	private Paint mPaint;
	private int mWidth;
	private int mHeight;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public AndroidCanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initWidthAndHeight(context);
		//mGameManager = new GameManager(this, mWidth, mHeight);
		initPaint();
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mCanvas = canvas;
		//mGameManager.onDraw();
		mPaint.setColor(Color.BLUE);
		mCanvas.drawCircle(rndInt(0, mWidth), rndInt(0, mHeight), mWidth < mHeight ? mWidth / 10 : mHeight / 10, mPaint);
	}

	private void initWidthAndHeight(Context context) {
		WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		Point point = new Point();
		display.getSize(point);

		mWidth = point.x;
		mHeight = point.y;
	}

	private void initPaint() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.FILL);
	}

	static int rndInt(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}
	/* МЕТОДЫ */
}