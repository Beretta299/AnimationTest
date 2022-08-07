package com.karas.vector.base.utils

import android.view.ScaleGestureDetector

open abstract class ZoomGestureListener : ScaleGestureDetector.OnScaleGestureListener{
    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {

    }
}