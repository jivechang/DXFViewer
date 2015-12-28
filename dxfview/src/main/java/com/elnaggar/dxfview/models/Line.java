package com.elnaggar.dxfview.models;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;
	private int color;
	private Paint strokPaint;

	@Override
	protected void draw( Canvas canvas) {
		strokPaint = new Paint();
		strokPaint.setColor(color);
		strokPaint.setAntiAlias(true);
		float startPointX = (float) startPoint.x ;
		float startPointY = (float) startPoint.y;
		float endPointX = (float) endPoint.x ;
		float endPointY = (float) endPoint.y ;
		canvas.drawLine(startPointX, startPointY, endPointX, endPointY,
				strokPaint);
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	protected void pan(int xDelta, int yDelta) {
		startPoint.x += xDelta;
		startPoint.y += yDelta;
		endPoint.x += xDelta;
		endPoint.y += yDelta;
	}

}
