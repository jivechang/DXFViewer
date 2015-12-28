package com.elnaggar.dxfview.models;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class Layer {

	private List<Shape> shapes;

	public Layer() {
		shapes = new ArrayList<Shape>();
	}

	public void draw(Canvas canvas) {
		for (Shape shape : shapes) {
			shape.draw(canvas);
		}
	}

	public void pan(int xDelta, int yDelta) {
		for (Shape shape : shapes) {
			shape.pan(xDelta, yDelta);
		}
	}

	public void addShape(Shape shape) {
		getShapes().add(shape);
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}
}
