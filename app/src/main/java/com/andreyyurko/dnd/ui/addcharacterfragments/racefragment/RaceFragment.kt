package com.andreyyurko.dnd.ui.addcharacterfragments.racefragment

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentRaceBinding
import com.andreyyurko.dnd.ui.addcharacterfragments.AbilityAdapter
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.utils.setupBasicPopUpMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RaceFragment : BaseFragment(R.layout.fragment_race) {

    private val viewBinding by viewBinding(FragmentRaceBinding::bind)
    private lateinit var viewModel: RaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RaceViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewBinding.chooseRaceButton.setOnClickListener {
            setupRacePopUpMenu(requireContext())
        }

        viewBinding.submitButton.setOnClickListener {
            viewModel.updateCharacter()
            findNavController().navigate(R.id.classFragment)
        }
        viewBinding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.abilitiesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = AbilityAdapter()
        viewModel.adapter = adapter
        recyclerView.adapter = adapter
        viewModel.showAbilities()
    }

    private fun setupRacePopUpMenu(context: Context) {
        val (raceMenu, parent) = setupBasicPopUpMenu(context)

        for (raceChoice in viewModel.baseCAN.showOptions("race")) {
            val raceNameTextView = TextView(context)
            raceNameTextView.isClickable = true
            raceNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.chooseRaceTextView.textSize)
            raceNameTextView.text = raceChoice
            parent.addView(raceNameTextView)
            raceNameTextView.setOnClickListener {
                viewModel.chosenRace = raceChoice
                viewModel.makeChoice(raceChoice)
                viewBinding.chooseRaceTextView.text = raceChoice.split("_").first()
                raceMenu.dismiss()
            }
        }
        raceMenu.showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            viewBinding.chooseRaceButton.x.toInt(),
            viewBinding.chooseRaceButton.y.toInt() + viewBinding.chooseRaceButton.height
        )
        viewBinding.arrowDropImageView.visibility = View.GONE
        viewBinding.arrowUpImageView.visibility = View.VISIBLE

        raceMenu.setOnDismissListener {
            viewBinding.arrowUpImageView.visibility = View.GONE
            viewBinding.arrowDropImageView.visibility = View.VISIBLE
        }
    }

}