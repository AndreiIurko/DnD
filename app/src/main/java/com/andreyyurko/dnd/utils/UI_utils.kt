package com.andreyyurko.dnd.utils

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.character.CharacterAbilityNode
import com.andreyyurko.dnd.ui.addcharacterfragments.AbilityAdapter

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
                if (!(event.x > 0 && event.y > 0 && event.x < v.width && event.y < v.height)) {
                    val fadeIn = ObjectAnimator.ofFloat(v, "alpha", v.alpha, 1f)
                    fadeIn.duration = 100
                    fadeIn.start()
                    return@setOnTouchListener true
                }
                if (fadeOut.isRunning) {
                    fadeOut.doOnEnd {
                        startFadeIn(view)
                    }
                } else {
                    startFadeIn(view)
                }
            }
        }
        true
    }
}

private fun startFadeIn(view: View) {
    val fadeIn = ObjectAnimator.ofFloat(view, "alpha", view.alpha, 0.7f)
    fadeIn.duration = 100
    fadeIn.start()
    fadeIn.doOnEnd {
        view.performClick()
    }
}


// popupWindow - our menu
// parent - Linear layout, root View of popUp window
fun setupBasicPopUpMenu(context: Context, maxHeight: Int? = 200): Pair<PopupWindow, LinearLayout> {
    val parent = LinearLayout(context)
    parent.setBackgroundColor(context.getColor(R.color.background))

    parent.layoutParams =
        LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    parent.orientation = LinearLayout.VERTICAL

    val focus = true
    val wid = LinearLayout.LayoutParams.WRAP_CONTENT
    val high = if (maxHeight != null) (context.resources.displayMetrics.density * maxHeight).toInt() else LinearLayout.LayoutParams.WRAP_CONTENT
    val scrollView = ScrollView(context)
    scrollView.background = AppCompatResources.getDrawable(context, R.drawable.filled_border)

    scrollView.addView(parent)
    val popupWindow = PopupWindow(scrollView, wid, high, focus)
    popupWindow.elevation = 3 * context.resources.displayMetrics.density
    return Pair(popupWindow, parent)
}