package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentClassBinding
import com.andreyyurko.dnd.ui.addcharacterfragments.AbilityAdapter
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.utils.setupBasicPopUpMenu
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassFragment : BaseFragment(R.layout.fragment_class) {

    private val viewBinding by viewBinding(FragmentClassBinding::bind)
    private lateinit var viewModel: ClassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ClassViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.chosenClass?.let {
            viewBinding.chooseClassTextView.text = it.split('_')[0]
            viewBinding.levelText.text = viewModel.chosenLevel.toString()
            viewBinding.levelUpButton.alpha = 1F
            viewBinding.levelUpButton.isEnabled = true
        }

        viewBinding.chooseClassButton.setOnClickListener {
            setupClassPopupMenu(requireContext())
        }

        viewBinding.chooseLevelButton.setOnClickListener {
            setupLevelPopupMenu(requireContext())
            viewBinding.levelArrowImageView.setImageDrawable(
                AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_drop_up_24)
            )
        }

        viewBinding.levelUpButton.setOnClickListener {
            viewModel.levelUp()
            viewBinding.levelText.text = viewModel.chosenLevel.toString()
        }

        viewBinding.submitButton.setOnClickListener {
            viewModel.updateCharacter()
            if (viewModel.isNeededToChooseKnownSpells())
                findNavController().navigate(R.id.spellsFragment)
            else {
                viewModel.saveChangesInCharacter()
                findNavController().popBackStack(R.id.charactersListFragment, false)
            }
        }
        viewBinding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupClassPopupMenu(context: Context) {
        val (classChoiceList, parent) = setupBasicPopUpMenu(context)
        for (classChoice in viewModel.baseCAN.showOptions("class")) {
            val classNameTextView = TextView(context)
            classNameTextView.isClickable = true
            classNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.chooseClassTextView.textSize)
            classNameTextView.text = classChoice.split("_").first()
            parent.addView(classNameTextView)
            classNameTextView.setOnClickListener {
                viewModel.chosenClass = classChoice
                viewModel.makeChoice(classChoice)
                viewBinding.levelUpButton.alpha = 1F
                viewBinding.levelUpButton.isEnabled = true
                viewBinding.chooseClassTextView.text = classChoice.split("_").first()
                classChoiceList.dismiss()
            }
        }
        classChoiceList.showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            viewBinding.chooseClassButton.x.toInt(),
            viewBinding.chooseClassButton.y.toInt() + viewBinding.chooseClassButton.height
        )
        viewBinding.arrowDropImageView.visibility = View.GONE
        viewBinding.arrowUpImageView.visibility = View.VISIBLE

        classChoiceList.setOnDismissListener {
            viewBinding.arrowUpImageView.visibility = View.GONE
            viewBinding.arrowDropImageView.visibility = View.VISIBLE
        }
    }

    //TODO: deal with code duplicate
    private fun setupLevelPopupMenu(context: Context) {
        val (levelChoiceList, parent) = setupBasicPopUpMenu(context)

        for (level in 1..20) {
            val levelTextView = TextView(context)
            levelTextView.isClickable = true
            levelTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.levelText.textSize)
            levelTextView.text = level.toString()
            levelTextView.width = viewBinding.chooseLevelButton.width
            levelTextView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            parent.addView(levelTextView)
            // TODO: make choice after level change as well
            levelTextView.setOnClickListener {
                viewModel.chosenLevel = level
                viewModel.chosenClass?.let {
                    viewModel.makeChoice(it)
                    viewBinding.levelUpButton.alpha = 1F
                    viewBinding.levelUpButton.isEnabled = true
                }
                viewBinding.levelText.text = level.toString()
                levelChoiceList.dismiss()
                viewBinding.levelArrowImageView.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_arrow_drop_down_24)
                )
            }
        }

        levelChoiceList.showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            viewBinding.chooseLevelButton.x.toInt(),
            viewBinding.chooseLevelButton.y.toInt() + viewBinding.chooseLevelButton.height
        )

    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.abilitiesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = AbilityAdapter()
        viewModel.adapter = adapter
        recyclerView.adapter = adapter
        viewModel.showAllClassAbilities()
    }
}
