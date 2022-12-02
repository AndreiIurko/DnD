package com.andreyyurko.dnd.ui.charactermain

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter


@AndroidEntryPoint
class CharacterMainFragment : Fragment(R.layout.fragment_character_main) {
    private val viewBinding by viewBinding(FragmentCharacterMainBinding::bind)
    private lateinit var viewModel : CharacterMainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterMainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.arrowBackImageButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.nameTextView.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.menuButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        viewBinding.settingsButton.applyInsetter {
            type(statusBars = true) { margin() }
        }

        setupAll()

        viewBinding.menuButton.setOnClickListener {
            setupPopupMenu(requireContext())
        }

        viewBinding.arrowBackImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAll() {
        viewBinding.nameTextView.text = viewModel.shownCharacter.name
        viewBinding.classTextView.text = viewModel.shownCharacter.characterInfo.characterClass
        viewBinding.levelTextView.text = viewModel.shownCharacter.characterInfo.level.toString()
        viewBinding.hpEditText.setText(viewModel.shownCharacter.characterInfo.hp.toString())
        viewBinding.proficiencyTextView.text = "+ ${viewModel.shownCharacter.characterInfo.proficiencyBonus}"
        viewBinding.speedTextView.text = "${viewModel.shownCharacter.characterInfo.speed}ft"
        viewBinding.initiativeTextView.text = "+ ${viewModel.shownCharacter.characterInfo.initiativeBonus}"
        viewBinding.acTextView.text = viewModel.shownCharacter.characterInfo.ac.toString()
    }

    private fun setupPopupMenu(context: Context) {
        val parent = LayoutInflater.from(context).inflate(R.layout.character_menu, null)

        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val characterMenu = PopupWindow(parent, wid, high, focus)

        val navController = (childFragmentManager.findFragmentById(R.id.mainFragmentNavigationHost) as NavHostFragment).navController
        parent.findViewById<LinearLayout>(R.id.actionsLinearLayout).setOnClickListener {
            navController.navigate(R.id.action_actionsFragment)
            characterMenu.dismiss()
        }
        parent.findViewById<LinearLayout>(R.id.abilitiesLinearLayout).setOnClickListener {
            navController.navigate(R.id.action_abilitiesFragment)
            characterMenu.dismiss()
        }
        parent.findViewById<LinearLayout>(R.id.skillsLinearLayout).setOnClickListener {
            navController.navigate(R.id.action_skillsFragment)
            characterMenu.dismiss()
        }
        parent.findViewById<LinearLayout>(R.id.inventoryLinearLayout).setOnClickListener {
            navController.navigate(R.id.action_inventoryFragment)
            characterMenu.dismiss()
        }
        parent.findViewById<LinearLayout>(R.id.spellsLinearLayout).setOnClickListener {
            navController.navigate(R.id.action_spellsFragment)
            characterMenu.dismiss()
        }
        parent.findViewById<LinearLayout>(R.id.additionalInfoLinearLayout).setOnClickListener {
            navController.navigate(R.id.action_additionalInfoFragment)
            characterMenu.dismiss()
        }

        characterMenu.animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
        characterMenu.showAtLocation(view, Gravity.CENTER, 0, 0)

        val fadeOut = ObjectAnimator.ofFloat(viewBinding.popUpBackground, "alpha", 0f, .5f)
        fadeOut.duration = 300
        fadeOut.start()

        characterMenu.setOnDismissListener {
            val fadeIn = ObjectAnimator.ofFloat(viewBinding.popUpBackground, "alpha", 0.5f, 0f)
            fadeIn.duration = 300
            fadeIn.start()
        }
    }
}