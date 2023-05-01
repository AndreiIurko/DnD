package com.andreyyurko.dnd.ui.showcharacterfragments.fragmentwithfilters

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.character.Filter
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.utils.setupBasicPopUpMenu

open class FragmentWithFilters : BaseFragment {
    constructor() : super()

    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    protected fun showFilters(filtersView: View, filtersButton: TextView, searchEditText: EditText) {
        filtersView.y = -100 * resources.displayMetrics.density + filtersButton.y
        filtersView.visibility = View.VISIBLE
        filtersView.animate()
            .translationY(filtersButton.y)
            .setListener(null)

        filtersButton.animate()
            .translationY(100 * resources.displayMetrics.density)
        filtersButton.text = "скрыть"
        searchEditText.isEnabled = false
    }

    protected fun closeFilters(filtersView: View, filtersButton: TextView, searchEditText: EditText) {
        filtersView.animate()
            .translationY(-2 * filtersView.height.toFloat() + filtersButton.y)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    filtersView.visibility = View.GONE
                }
            })
        filtersButton.animate()
            .translationY(0f)
        filtersButton.text = "фильтр"
        searchEditText.isEnabled = true
    }

    protected inline fun <reified T> setupFilter(button: TextView, set: MutableSet<T>) where T : Enum<T>, T : Filter {
        val (choiceList, parent) = setupBasicPopUpMenu(button.context)
        for (enumValue in enumValues<T>()) {
            val textView = TextView(context)
            textView.isClickable = true
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, button.textSize)
            textView.text = enumValue.shownName

            if (textView.text.length < 4) {
                textView.width = (50 * requireActivity().resources.displayMetrics.density).toInt()
                textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }

            if (set.contains(enumValue)) textView.setBackgroundColor(
                ContextCompat.getColor(
                    button.context,
                    R.color.on_primary
                )
            )
            else textView.setBackgroundColor(ContextCompat.getColor(button.context, R.color.background))

            parent.addView(textView)
            textView.setOnClickListener {
                if (set.contains(enumValue)) {
                    set.remove(enumValue)
                    textView.setBackgroundColor(ContextCompat.getColor(button.context, R.color.background))
                } else {
                    set.add(enumValue)
                    textView.setBackgroundColor(ContextCompat.getColor(button.context, R.color.on_primary))
                }
            }
        }
        val location = IntArray(2)
        button.getLocationOnScreen(location)
        choiceList.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + button.height)
    }

    protected fun setupStringFilter(button: TextView, set: MutableSet<String>, values: List<String>) {
        val (choiceList, parent) = setupBasicPopUpMenu(button.context)
        for (value in values) {
            val textView = TextView(context)
            textView.isClickable = true
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, button.textSize)
            textView.text = value

            if (textView.text.length < 4) {
                textView.width = (50 * requireActivity().resources.displayMetrics.density).toInt()
                textView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            }

            parent.addView(textView)

            if (set.contains(value)) textView.setBackgroundColor(
                ContextCompat.getColor(
                    button.context,
                    R.color.on_primary
                )
            )
            else textView.setBackgroundColor(ContextCompat.getColor(button.context, R.color.background))
            textView.setOnClickListener {
                if (set.contains(value)) {
                    set.remove(value)
                    textView.setBackgroundColor(ContextCompat.getColor(button.context, R.color.background))
                } else {
                    set.add(value)
                    textView.setBackgroundColor(ContextCompat.getColor(button.context, R.color.on_primary))
                }
            }
        }
        val location = IntArray(2)
        button.getLocationOnScreen(location)
        choiceList.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + button.height)
    }
}