package com.elnaggar.dxfview.models;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;

public class Circle extends Shape {

	private int strokColor;
	private Paint strokPaint;
	private Point centerPoint;
	private double radius;

	@Override
	protected void draw(Canvas canvas) {
		strokPaint = new Paint();
		strokPaint.setColor(strokColor);
		strokPaint.setStyle(Style.STROKE);
		strokPaint.setStrokeWidth(10);
		strokPaint.setAntiAlias(true);
		float centerPointX = (float) getCenterPoint().x;
		float centerPointY = (float) getCenterPoint().y;
		canvas.drawCircle(centerPointX, centerPointY, (float) getRadius(),
				strokPaint);
	}

	public void setStrokColor(int strokColor) {
		this.strokColor = strokColor;
	}

	public int getStrokColor() {
		return strokColor;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Point getCenterPoint() {
		return centerPoint;
	}

	public void setCenterPoint(Point centerPoint) {
		this.centerPoint = centerPoint;
	}

	@Override
	protected void pan(int x, int y) {
		centerPoint.x += x;
		centerPoint.y += y;

	}
}
