package com.elnaggar.dxfview.models;

import android.graphics.Canvas;

public abstract class Shape {

	private int thickness;

	protected abstract void draw(Canvas canvas);

	protected abstract void pan(int xDelta, int yDelta);

	protected int getThickness() {
		return thickness;
	}

	protected void setThickness(int thickness) {
		this.thickness = thickness;
	}
}
