package com.andreyyurko.dnd.utils

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import androidx.core.animation.doOnEnd

@SuppressLint("ClickableViewAccessibility")
fun onPressAnimation(view: View) {
    val fadeOut = ObjectAnimator.ofFloat(view, "alpha", view.alpha, .3f)
    fadeOut.duration = 200
    view.setOnTouchListener { v, event ->
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                fadeOut.start()
            }
            MotionEvent.ACTION_UP -> {
                if (!(event.x > view.x && event.y > view.y && event.x < view.x + view.width && event.y < view.y + view.height)) {
                    val fadeIn = ObjectAnimator.ofFloat(view, "alpha", view.alpha, 1f)
                    fadeIn.duration = 100
                    fadeIn.start()
                    return@setOnTouchListener true
                }
                if (fadeOut.isRunning) {
                    fadeOut.doOnEnd {
                        startFadeIn(view)
                    }
                }
                else {
                    startFadeIn(view)
                }
            }
        }
        true
    }
}

private fun startFadeIn(view:View) {
    val fadeIn = ObjectAnimator.ofFloat(view, "alpha", view.alpha, 0.7f)
    fadeIn.duration = 100
    fadeIn.start()
    fadeIn.doOnEnd {
        view.performClick()
    }
}