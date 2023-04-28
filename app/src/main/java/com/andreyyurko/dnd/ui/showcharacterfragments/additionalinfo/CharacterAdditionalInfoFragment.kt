package com.andreyyurko.dnd.ui.showcharacterfragments.additionalinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterAdditionalInfoBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterAdditionalInfoFragment : BaseFragment(R.layout.fragment_character_additional_info) {

    private val viewBinding by viewBinding(FragmentCharacterAdditionalInfoBinding::bind)

    private lateinit var viewModel: CharacterAdditionalInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterAdditionalInfoViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLanguagesLinearLayout()
        setupResistancesLinearLayout()
        setupRecyclerView()
    }

    private fun setupLanguagesLinearLayout() {
        val linearLayout = viewBinding.languagesLinearLayout
        linearLayout.removeAllViews()
        for (language in viewModel.getLanguages()) {
            val textView = TextView(linearLayout.context)
            textView.text = language.languageName
            textView.textSize = 18F
            textView.setPadding(6)
            linearLayout.addView(textView)
        }
    }

    private fun setupResistancesLinearLayout() {
        val linearLayout = viewBinding.resistancesLinearLayout
        val listOfResistances = viewModel.getResistances()
        if (listOfResistances.isEmpty()) {
            viewBinding.resistancesTextView.visibility = View.GONE
            return
        }
        linearLayout.removeAllViews()

        for (resistance in listOfResistances) {
            val textView = TextView(linearLayout.context)
            textView.text = resistance.typeName
            textView.textSize = linearLayout.context.resources.displayMetrics.density * 18
            textView.setPadding(linearLayout.context.resources.displayMetrics.density.toInt() * 6)
            linearLayout.addView(textView)
        }
    }

    private fun setupRecyclerView() {
        val items = viewModel.getAdditionalInfoItems()
        val linearLayout = viewBinding.additionalInfoLinearLayout
        linearLayout.removeAllViews()
        for (item in items) {
            val itemView =
                LayoutInflater.from(linearLayout.context)
                    .inflate(R.layout.view_additional_info_list_item, linearLayout, false)
            val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
            val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

            nameTextView.text = item.first
            descriptionTextView.text = item.second

            linearLayout.addView(itemView)
        }
    }
}