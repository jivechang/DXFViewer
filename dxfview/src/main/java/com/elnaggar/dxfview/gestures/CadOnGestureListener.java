package com.elnaggar.dxfview.gestures;

import android.view.MotionEvent;

public class CadOnGestureListener extends
		android.view.GestureDetector.SimpleOnGestureListener {


	public CadOnGestureListener()
	{

	}

	@Override
	public boolean onDoubleTap(MotionEvent e) {
		return true;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {

		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return true;
	}

	
	@Override
	public boolean onSingleTapUp(MotionEvent e) {

		return true;
	}
	
	
}
