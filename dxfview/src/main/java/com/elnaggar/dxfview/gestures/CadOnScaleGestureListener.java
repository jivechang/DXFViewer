package com.elnaggar.dxfview.gestures;

import android.view.ScaleGestureDetector;

import com.elnaggar.dxfview.views.DXFView;


public class CadOnScaleGestureListener extends
		ScaleGestureDetector.SimpleOnScaleGestureListener {

	private float scaleFactor = 1;

	private DXFView cadSurfaceView;

	public CadOnScaleGestureListener(DXFView DXFView) {
		this.cadSurfaceView = DXFView;
	}

	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		scaleFactor *= detector.getScaleFactor();
		scaleFactor = Math.max(0.01f,Math.min(scaleFactor, 2.0f));
		cadSurfaceView.scale(scaleFactor, scaleFactor);
		return false;
	}

	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {

		return true;
	}

	@Override
	public void onScaleEnd(ScaleGestureDetector detector) {

	}

}
