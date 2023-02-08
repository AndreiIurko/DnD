package com.andreyyurko.dnd.ui.showcharacterfragments.inventory

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.ItemRarity
import com.andreyyurko.dnd.data.characterData.ItemType
import com.andreyyurko.dnd.data.characterData.Source
import com.andreyyurko.dnd.databinding.FragmentCharacterInventoryBinding
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.setupBasicPopUpMenu
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class CharacterInventoryFragment : Fragment(R.layout.fragment_character_inventory) {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    lateinit var viewModel: CharacterInventoryViewModel

    private val viewBinding by viewBinding(FragmentCharacterInventoryBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterInventoryViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewBinding.searchButton.setOnClickListener {
            viewModel.filters.substring = viewBinding.searchEditText.text.toString()
            (viewBinding.inventoryRecyclerView.adapter as InventoryAdapter).apply {
                itemsList = viewModel.showItems()
                notifyDataSetChanged()
            }
        }

        viewBinding.filtersButton.setOnClickListener {
            if (viewBinding.filtersView.visibility == View.GONE) {
                showFilters()
            }
            else closeFilters()
        }

        viewBinding.rarityButton.setOnClickListener {
            setupRarityFilter(requireContext())
        }

        viewBinding.typeButton.setOnClickListener {
            setupTypeFilter(requireContext())
        }

        viewBinding.sourceButton.setOnClickListener {
            setupSourceFilter(requireContext())
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.inventoryRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val adapter = InventoryAdapter(viewModel.inventoryHandler, viewModel.characterViewModel, viewBinding.popUpBackground)
        recyclerView.adapter = adapter

        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }!!)
        recyclerView.addItemDecoration(itemDecorator)

        adapter.apply {
            itemsList = viewModel.showItems()
            notifyDataSetChanged()
        }
    }

    private fun showFilters() {
        viewBinding.filtersView.y = -100 * resources.displayMetrics.density
        viewBinding.filtersView.visibility = View.VISIBLE
        viewBinding.filtersView.animate()
            .translationY(0f)
            .setListener(null)

        viewBinding.filtersButton.animate()
            .translationY(100 * resources.displayMetrics.density)
        viewBinding.filtersButton.text = "скрыть"
        viewBinding.searchEditText.isEnabled = false
    }

    private fun closeFilters() {
        viewBinding.filtersView.animate()
            .translationY(-viewBinding.filtersView.height.toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    viewBinding.filtersView.visibility = View.GONE
                }
            })
        viewBinding.filtersButton.animate()
            .translationY(0f)
        viewBinding.filtersButton.text = "фильтр"
        viewBinding.searchEditText.isEnabled = true
    }

    private fun setupRarityFilter(context: Context) {
        val (rarityChoiceList, parent) = setupBasicPopUpMenu(context)
        for (rarity in ItemRarity.values()) {
            val textView = TextView(context)
            textView.isClickable = true
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.rarityButton.textSize)
            textView.text = rarity.rarityName
            if (viewModel.filters.rarity.contains(rarity)) {
                textView.setBackgroundColor(resources.getColor(R.color.on_primary))
            }
            else {
                textView.setBackgroundColor(resources.getColor(R.color.background))
            }
            parent.addView(textView)
            textView.setOnClickListener {
                if (viewModel.filters.rarity.contains(rarity)) {
                    viewModel.filters.rarity.remove(rarity)
                    textView.setBackgroundColor(resources.getColor(R.color.background))
                }
                else {
                    viewModel.filters.rarity.add(rarity)
                    textView.setBackgroundColor(resources.getColor(R.color.on_primary))
                }
            }
        }
        val location = IntArray(2)
        viewBinding.rarityButton.getLocationOnScreen(location)
        rarityChoiceList.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + viewBinding.rarityButton.height)
    }

    private fun setupTypeFilter(context: Context) {
        val (typeChoiceList, parent) = setupBasicPopUpMenu(context)
        for (type in ItemType.values()) {
            val textView = TextView(context)
            textView.isClickable = true
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.typeButton.textSize)
            textView.text = type.typeName
            if (viewModel.filters.type.contains(type)) {
                textView.setBackgroundColor(resources.getColor(R.color.on_primary))
            }
            else {
                textView.setBackgroundColor(resources.getColor(R.color.background))
            }
            parent.addView(textView)
            textView.setOnClickListener {
                if (viewModel.filters.type.contains(type)) {
                    viewModel.filters.type.remove(type)
                    textView.setBackgroundColor(resources.getColor(R.color.background))
                }
                else {
                    viewModel.filters.type.add(type)
                    textView.setBackgroundColor(resources.getColor(R.color.on_primary))
                }
            }
        }
        val location = IntArray(2)
        viewBinding.typeButton.getLocationOnScreen(location)
        typeChoiceList.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + viewBinding.typeButton.height)
    }

    private fun setupSourceFilter(context: Context) {
        val (sourceChoiceList, parent) = setupBasicPopUpMenu(context)
        for (source in Source.values()) {
            val textView = TextView(context)
            textView.isClickable = true
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.typeButton.textSize)
            textView.text = source.shortName
            if (viewModel.filters.source.contains(source)) {
                textView.setBackgroundColor(resources.getColor(R.color.on_primary))
            }
            else {
                textView.setBackgroundColor(resources.getColor(R.color.background))
            }
            parent.addView(textView)
            textView.setOnClickListener {
                if (viewModel.filters.source.contains(source)) {
                    viewModel.filters.source.remove(source)
                    textView.setBackgroundColor(resources.getColor(R.color.background))
                }
                else {
                    viewModel.filters.source.add(source)
                    textView.setBackgroundColor(resources.getColor(R.color.on_primary))
                }
            }
        }
        val location = IntArray(2)
        viewBinding.sourceButton.getLocationOnScreen(location)
        sourceChoiceList.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] + viewBinding.sourceButton.height)
    }
}