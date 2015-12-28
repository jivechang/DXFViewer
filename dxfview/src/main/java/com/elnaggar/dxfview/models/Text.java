package com.elnaggar.dxfview.models;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Text extends Shape {

	private String text;
	private int color;
	private Paint strokPaint;
	private Point alignPosition;

	@Override
	protected void draw(Canvas canvas) {
		strokPaint = new Paint();
		strokPaint.setColor(color);
		strokPaint.setAntiAlias(true);
		strokPaint.setTextSize(60);
		float alignPositionX = (float) alignPosition.x;// - (float) xMinValue;
		float alignPositionY = (float) alignPosition.y;// - (float) yMinValue;
		canvas.save();
		canvas.scale(1, -1);
		canvas.translate(0, -canvas.getHeight());
		canvas.drawText(text, alignPositionX, alignPositionY, strokPaint);
		// canvas.translate(0, canvas.getHeight());
		// canvas.scale(xScaleFactor, yScaleFactor);
		// canvas.scale(1, -1);
		 canvas.restore();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public Paint getStrokPaint() {
		return strokPaint;
	}

	public void setStrokPaint(Paint strokPaint) {
		this.strokPaint = strokPaint;
	}

	public void setAlignPosition(Point alignPosition) {
		this.alignPosition = alignPosition;
	}

	public Point getAlignPosition() {
		return alignPosition;
	}

	@Override
	protected void pan(int xDelta, int yDelta) {
		alignPosition.x += xDelta;
		alignPosition.y += yDelta;
	}

}
