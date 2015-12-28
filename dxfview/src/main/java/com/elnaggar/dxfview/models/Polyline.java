package com.elnaggar.dxfview.models;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Polyline extends Shape {

	private float[] points;
	private Paint strokPaint;
	private int color;
	private boolean isClosed;

	@Override
	protected void draw(Canvas canvas) {
		strokPaint = new Paint();
		strokPaint.setColor(color);
		strokPaint.setAntiAlias(true);
		strokPaint.setDither(true);
		strokPaint.setStyle(Paint.Style.STROKE);
		strokPaint.setStrokeWidth(10);
		Path path = new Path();
		path.moveTo(points[0], points[1]);
		for (int i = 2; i < points.length; i += 2) {
			path.lineTo(points[i], points[i + 1]);
		}
		if (isClosed)
			path.close();
		canvas.drawPath(path, strokPaint);
	}

	public float[] getPoints() {
		return points;
	}

	public void setPoints(float[] points) {
		this.points = points;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	@Override
	protected void pan(int xDelta, int yDelta) {
		for (int i = 0; i < points.length; i += 2) {
			points[i] += xDelta;
			points[i + 1] += yDelta;
		}
	}

	public void setIsClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public boolean getIsClosed() {
		return isClosed;
	}
}
