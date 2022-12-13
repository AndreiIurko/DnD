package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentClassBinding
import com.andreyyurko.dnd.ui.spellslist.SpellsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ClassFragment : Fragment(R.layout.fragment_class) {

    private val viewBinding by viewBinding(FragmentClassBinding::bind)
    private lateinit var viewModel: ClassViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ClassViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewBinding.chooseClassButton.setOnClickListener {
            setupPopupMenu(requireContext())
        }
        viewBinding.submitButton.setOnClickListener {
            findNavController().popBackStack(R.id.charactersListFragment, false)
        }
        viewBinding.cancelButton.setOnClickListener {
            viewModel.deleteCharacter()
            findNavController().popBackStack(R.id.charactersListFragment, false)
        }
    }

    private fun setupPopupMenu(context: Context) {
        val parent = LinearLayout(context)

        parent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        parent.orientation = LinearLayout.VERTICAL
        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val classChoiceList = PopupWindow(parent, wid, high, focus)
        for (classChoice in viewModel.baseCAN.showOptions(viewModel.character.characterInfo, "class")) {
            val classNameTextView = TextView(context)
            classNameTextView.isClickable = true
            classNameTextView.textSize = resources.displayMetrics.density * 10
            classNameTextView.text = classChoice.split("_").first()
            parent.addView(classNameTextView)
            classNameTextView.setOnClickListener {
                viewModel.makeChoice(classChoice)
                viewBinding.chooseClassTextView.text = classChoice.split("_").first()

                classChoiceList.dismiss()
                viewBinding.arrowUpImageView.visibility = View.GONE
                viewBinding.arrowDropImageView.visibility = View.VISIBLE
            }
        }
        classChoiceList.showAtLocation(view, Gravity.NO_GRAVITY, viewBinding.chooseClassButton.x.toInt(), viewBinding.chooseClassButton.y.toInt() + 200)
        viewBinding.arrowDropImageView.visibility = View.GONE
        viewBinding.arrowUpImageView.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.abilitiesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = ClassAdapter()
        viewModel.adapter = adapter
        recyclerView.adapter = adapter
    }
}
