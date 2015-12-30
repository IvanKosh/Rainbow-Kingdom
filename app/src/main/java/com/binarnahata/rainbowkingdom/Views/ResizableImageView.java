package com.binarnahata.rainbowkingdom.Views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * RainbowKingdom
 * Created on 29.12.15, 16:52
 *
 * @author bat
 * @version 0.1
 */
public class ResizableImageView extends ImageView {
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	private static final String TAG = ResizableImageView.class.getSimpleName();
	/* КОНСТАНТЫ И ПЕРЕМЕННЫЕ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* ГЕТТЕРЫ И СЕТТЕРЫ */
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	public ResizableImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	/* КОНСТРУКТОРЫ И ДЕСТРУКТОРЫ */
	/* МЕТОДЫ */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Drawable d = getDrawable();

		if (d != null) {
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
			setMeasuredDimension(width, height);
		}
		else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}
	/* МЕТОДЫ */
}