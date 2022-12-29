package com.andreyyurko.dnd.utils

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.animation.doOnEnd
import com.andreyyurko.dnd.data.characters.CharacterAbilityNode

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

fun createPopUpMenu(rootView: View, triggerView: TextView, listOfOptions: List<String>, optionName: String, can: CharacterAbilityNode): PopupWindow {
    val parent = LinearLayout(rootView.context)
    parent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    parent.orientation = LinearLayout.VERTICAL
    val focus = true
    val wid = LinearLayout.LayoutParams.WRAP_CONTENT
    val high = LinearLayout.LayoutParams.WRAP_CONTENT
    val popupChoiceList = PopupWindow(parent, wid, high, focus)
    for (choice in listOfOptions) {
        val choiceTextView = TextView(rootView.context)
        choiceTextView.isClickable = true
        choiceTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, triggerView.textSize)
        choiceTextView.text = choice
        parent.addView(choiceTextView)
        choiceTextView.setOnClickListener {
            can.makeChoice(optionName, choice)
            triggerView.text = choice
            popupChoiceList.dismiss()
        }
    }

    val location = IntArray(2)
    triggerView.getLocationOnScreen(location)
    popupChoiceList.showAtLocation(rootView, Gravity.NO_GRAVITY, triggerView.x.toInt(), location[1] + triggerView.height + 40)
    return popupChoiceList
}