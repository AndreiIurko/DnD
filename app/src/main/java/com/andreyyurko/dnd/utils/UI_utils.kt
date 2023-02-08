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
import android.widget.TextView
import androidx.core.animation.doOnEnd
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

// triggerView - button beneath which we create menu
// textView - text inside button
// listOfOptions - list of ability names
// optionName - name of list of options in can
// can - parent CharacterAbilityNode
// adapter - we need to update info in RecyclerView after make choice, so we add adapter
fun createPopUpMenu(triggerView: View, textView: TextView, listOfOptions: List<String>, optionName: String, can: CharacterAbilityNode, adapter: AbilityAdapter): PopupWindow {
    // parent - linear layout inside popup menu
    val (popupChoiceList, parent) = setupBasicPopUpMenu(triggerView.context)
    // for every ability we need to add it inside linear layout
    for (choice in listOfOptions) {
        // configure textView
        val choiceTextView = TextView(triggerView.context)
        choiceTextView.isClickable = true
        choiceTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textView.textSize)
        choiceTextView.text = choice
        // add textView in linear layout
        parent.addView(choiceTextView)
        // on press, we need to make choice, close menu and tell adapter that we need reload all abilities
        // because we are automatically making choice if option list size == 1 we can insert more when 1 ability
        // so, we need to call notifyDataSetChanged
        choiceTextView.setOnClickListener {
            can.makeChoice(optionName, choice)
            popupChoiceList.dismiss()
            adapter.notifyDataSetChanged()
        }
    }
    // configure location of popup menu and show it
    val location = IntArray(2)
    triggerView.getLocationOnScreen(location)
    popupChoiceList.showAtLocation(triggerView, Gravity.NO_GRAVITY, location[0], location[1] + textView.height + 40)
    return popupChoiceList
}

// popupWindow - our menu
// parent - Linear layout, root View of popUp window
fun setupBasicPopUpMenu(context: Context): Pair<PopupWindow, LinearLayout> {
    val parent = LinearLayout(context)

    parent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
    parent.orientation = LinearLayout.VERTICAL

    val focus = true
    val wid = LinearLayout.LayoutParams.WRAP_CONTENT
    val high = LinearLayout.LayoutParams.WRAP_CONTENT
    val popupWindow = PopupWindow(parent, wid, high, focus)
    popupWindow.elevation = 3 * context.resources.displayMetrics.density
    return Pair(popupWindow, parent)
}