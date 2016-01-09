package com.binarnahata.rainbowkingdom.Models.GamePanel;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.binarnahata.rainbowkingdom.Libs.Math.Vector3;
import com.binarnahata.rainbowkingdom.Models.Components.Color;
import com.binarnahata.rainbowkingdom.Models.Circles.BitmapCircle;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 02.01.16, 14:57
 *
 * @author bat
 * @version 0.1
 */
public class TopPanel {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = TopPanel.class.getSimpleName();
	public static final int NUMBER_OF_HELP_BALL = 7;
	private final Paint mPaint;
	private final Rect mRect;
	private final ArrayList<BitmapCircle> mBitmapCircles = new ArrayList<>();
	private final Bitmap mBitmapCircle;
	private final Bitmap mBitmapLine;
	private final Rect mLineRect;

	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public TopPanel(Paint paint, Rect rect, Bitmap bitmapCircle, Bitmap bitmapLine) {
		mPaint = paint;
		mRect = rect;
		mBitmapCircle = bitmapCircle;
		mBitmapLine = bitmapLine;
		double r = rect.height() / 2;
		double step = rect.width() / (NUMBER_OF_HELP_BALL + 1);
		int[] colors = {Color.MAGENTA, Color.RED, Color.YELLOW,
			Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA};
		BitmapCircle circle;
		for (int i = 0; i < NUMBER_OF_HELP_BALL; i++) {
			circle = new BitmapCircle(new Vector3(step + i * step, r), r, colors[i], mBitmapCircle);
			mBitmapCircles.add(circle);
		}
		int border = rect.height() / 10;
		mLineRect = new Rect(0, rect.bottom, rect.width(), rect.bottom + border);//new Rect(0, rect.height(), rect.width(), 10);
	}

	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	public void draw(Canvas canvas) {
		/*mPaint.setColor(Color.GRAY);
		canvas.drawRect(mRect, mPaint);*/
		for (BitmapCircle circle : mBitmapCircles) {
			circle.draw(canvas, mPaint);
		}
		canvas.drawBitmap(mBitmapLine, null, mLineRect, null);
	}
	/* МЕТОДЫ */
}