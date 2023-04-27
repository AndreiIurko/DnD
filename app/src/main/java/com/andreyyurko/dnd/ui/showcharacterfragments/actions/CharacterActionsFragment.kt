package com.andreyyurko.dnd.ui.showcharacterfragments.actions

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.characterData.ActionType
import com.andreyyurko.dnd.databinding.FragmentCharacterActionsBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActionsFragment : BaseFragment(R.layout.fragment_character_actions) {

    private val viewBinding by viewBinding(FragmentCharacterActionsBinding::bind)

    private lateinit var viewModel: CharacterActionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterActionsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.mainActionsButton.setOnClickListener {
            if (viewBinding.mainActionsRecyclerView.visibility == View.GONE) {
                setupRecyclerView(viewBinding.mainActionsRecyclerView, ActionType.Action)
                viewBinding.mainActionsRecyclerView.visibility = View.VISIBLE
                viewBinding.mainActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_up_24)
                )
            } else {
                viewBinding.mainActionsRecyclerView.visibility = View.GONE
                viewBinding.mainActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_down_24)
                )
            }
        }

        viewBinding.bonusActionsButton.setOnClickListener {
            if (viewBinding.bonusActionsRecyclerView.visibility == View.GONE) {
                setupRecyclerView(viewBinding.bonusActionsRecyclerView, ActionType.Bonus)
                viewBinding.bonusActionsRecyclerView.visibility = View.VISIBLE
                viewBinding.bonusActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_up_24)
                )
            } else {
                viewBinding.bonusActionsRecyclerView.visibility = View.GONE
                viewBinding.bonusActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_down_24)
                )
            }
        }

        viewBinding.reactionsButton.setOnClickListener {
            if (viewBinding.reactionsRecyclerView.visibility == View.GONE) {
                setupRecyclerView(viewBinding.reactionsRecyclerView, ActionType.Reaction)
                viewBinding.reactionsRecyclerView.visibility = View.VISIBLE
                viewBinding.reactionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_up_24)
                )
            } else {
                viewBinding.reactionsRecyclerView.visibility = View.GONE
                viewBinding.reactionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_down_24)
                )
            }
        }

        viewBinding.partOfActionButton.setOnClickListener {
            if (viewBinding.partOfActionRecyclerView.visibility == View.GONE) {
                setupRecyclerView(viewBinding.partOfActionRecyclerView, ActionType.PartOfAction)
                viewBinding.partOfActionRecyclerView.visibility = View.VISIBLE
                viewBinding.partOfActionArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_up_24)
                )
            } else {
                viewBinding.partOfActionRecyclerView.visibility = View.GONE
                viewBinding.partOfActionArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_down_24)
                )
            }
        }

        viewBinding.longActionsButton.setOnClickListener {
            if (viewBinding.longActionsRecyclerView.visibility == View.GONE) {
                setupRecyclerView(viewBinding.longActionsRecyclerView, ActionType.Long)
                viewBinding.longActionsRecyclerView.visibility = View.VISIBLE
                viewBinding.longActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_up_24)
                )
            } else {
                viewBinding.longActionsRecyclerView.visibility = View.GONE
                viewBinding.longActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_down_24)
                )
            }
        }

        viewBinding.additionalActionButton.setOnClickListener {
            if (viewBinding.additionalActionsRecyclerView.visibility == View.GONE) {
                setupRecyclerView(viewBinding.additionalActionsRecyclerView, ActionType.Additional)
                viewBinding.additionalActionsRecyclerView.visibility = View.VISIBLE
                viewBinding.additionalActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_up_24)
                )
            } else {
                viewBinding.additionalActionsRecyclerView.visibility = View.GONE
                viewBinding.additionalActionsArrowImage.setImageDrawable(
                    AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_keyboard_arrow_down_24)
                )
            }
        }
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, actionType: ActionType) {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = ActionsAdapter(viewModel.characterViewModel)
        recyclerView.adapter = adapter

        adapter.apply {
            actionsList = viewModel.getActionList(actionType)
            notifyDataSetChanged()
        }
    }
}