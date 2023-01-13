package com.andreyyurko.dnd.ui.addcharacterfragments.classfragment

import android.content.Context
import android.os.Bundle
import android.util.TypedValue
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
            setupClassPopupMenu(requireContext())
        }

        viewBinding.chooseLevelButton.setOnClickListener {
            setupLevelPopupMenu(requireContext())
        }

        viewBinding.submitButton.setOnClickListener {
            viewModel.updateCharacter()
            findNavController().popBackStack(R.id.charactersListFragment, false)
        }
        viewBinding.cancelButton.setOnClickListener {
            viewModel.deleteCharacter()
            findNavController().popBackStack(R.id.charactersListFragment, false)
        }
    }

    private fun setupClassPopupMenu(context: Context) {
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
            classNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.chooseClassTextView.textSize)
            classNameTextView.text = classChoice.split("_").first()
            parent.addView(classNameTextView)
            classNameTextView.setOnClickListener {
                viewModel.chosenClass = classChoice
                viewModel.makeChoice(classChoice)
                viewBinding.chooseClassTextView.text = classChoice.split("_").first()
                classChoiceList.dismiss()
            }
        }
        classChoiceList.showAtLocation(view, Gravity.NO_GRAVITY, viewBinding.chooseClassButton.x.toInt(), viewBinding.chooseClassButton.y.toInt() + viewBinding.chooseClassButton.height)
        viewBinding.arrowDropImageView.visibility = View.GONE
        viewBinding.arrowUpImageView.visibility = View.VISIBLE

        classChoiceList.setOnDismissListener {
            viewBinding.arrowUpImageView.visibility = View.GONE
            viewBinding.arrowDropImageView.visibility = View.VISIBLE
        }
    }

    //TODO: deal with code duplicate
    private fun setupLevelPopupMenu(context: Context) {
        val parent = LinearLayout(context)
        parent.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        parent.orientation = LinearLayout.VERTICAL
        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val levelChoiceList = PopupWindow(parent, wid, high, focus)

        for (level in 1..20) {
            val levelTextView = TextView(context)
            levelTextView.isClickable = true
            levelTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewBinding.levelText.textSize)
            levelTextView.text = level.toString()
            parent.addView(levelTextView)
            // TODO: make choice after level change as well
            levelTextView.setOnClickListener {
                viewModel.chosenLevel = level
                viewModel.chosenClass?.let {
                    viewModel.makeChoice(it)
                }
                viewBinding.levelText.text = level.toString()
                levelChoiceList.dismiss()
            }
        }

        levelChoiceList.showAtLocation(view, Gravity.NO_GRAVITY, viewBinding.chooseLevelButton.x.toInt(), viewBinding.chooseLevelButton.y.toInt() + viewBinding.chooseLevelButton.height)

    }

    private fun setupRecyclerView() {
        val recyclerView = viewBinding.abilitiesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = ClassAdapter()
        viewModel.adapter = adapter
        recyclerView.adapter = adapter
    }
}
