package com.andreyyurko.dnd.ui.showcharacterfragments.main

import android.animation.ObjectAnimator
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterMainBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.onPressAnimation
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import javax.inject.Inject


@AndroidEntryPoint
class CharacterMainFragment : BaseFragment(R.layout.fragment_character_main) {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    private val viewBinding by viewBinding(FragmentCharacterMainBinding::bind)

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

        val changeObserver = Observer<String> { state ->
            if (state == CharacterViewModel.DataState.Complete.stateName) setupAll()
        }
        characterViewModel.dataState.observe(viewLifecycleOwner, changeObserver)

        val backgroundObserver = Observer<Boolean> { isShown ->
            if (isShown) {
                val fadeOut = ObjectAnimator.ofFloat(viewBinding.popUpBackground, "alpha", 0f, .5f)
                fadeOut.duration = 300
                fadeOut.start()
            } else {
                val fadeIn = ObjectAnimator.ofFloat(viewBinding.popUpBackground, "alpha", 0.5f, 0f)
                fadeIn.duration = 300
                fadeIn.start()
            }
        }
        characterViewModel.backgroundIsShown.observe(viewLifecycleOwner, backgroundObserver)

        setupAll()

        viewBinding.menuButton.setOnClickListener {
            setupPopupMenu(requireContext())
        }

        viewBinding.settingsButton.setOnClickListener {
            //findNavController().navigate(R.id.abilitiesFragment)
            TODO("navigate to changing character stats, abilities, etc.")
        }

        onPressAnimation(viewBinding.arrowBackImageButton)
        viewBinding.arrowBackImageButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupAll() {
        viewBinding.nameTextView.text = characterViewModel.shownCharacter.name
        viewBinding.classTextView.text = characterViewModel.shownCharacter.characterInfo.characterClass.className
        viewBinding.levelTextView.text = characterViewModel.shownCharacter.characterInfo.level.toString()
        viewBinding.hpEditText.setText(characterViewModel.shownCharacter.characterInfo.hp.toString())
        viewBinding.proficiencyTextView.text =
            "+ ${characterViewModel.shownCharacter.characterInfo.proficiencyBonus}"
        viewBinding.speedTextView.text = "${characterViewModel.shownCharacter.characterInfo.speed}ft"
        viewBinding.initiativeTextView.text =
            if (characterViewModel.shownCharacter.characterInfo.initiativeBonus >= 0)
                "+ ${characterViewModel.shownCharacter.characterInfo.initiativeBonus}"
            else
                "- ${kotlin.math.abs(characterViewModel.shownCharacter.characterInfo.initiativeBonus)}"
        viewBinding.acTextView.text = characterViewModel.shownCharacter.characterInfo.ac.toString()
    }

    private fun setupPopupMenu(context: Context) {
        val parent = LayoutInflater.from(context).inflate(R.layout.character_menu, null)

        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val characterMenu = PopupWindow(parent, wid, high, focus)

        val navController =
            (childFragmentManager.findFragmentById(R.id.mainFragmentNavigationHost) as NavHostFragment).navController

        setupMenuButton(
            parent.findViewById(R.id.actionsLinearLayout),
            R.id.action_actionsFragment,
            navController,
            characterMenu
        )
        setupMenuButton(
            parent.findViewById(R.id.abilitiesLinearLayout),
            R.id.action_abilitiesFragment,
            navController,
            characterMenu
        )
        setupMenuButton(
            parent.findViewById(R.id.skillsLinearLayout),
            R.id.action_skillsFragment,
            navController,
            characterMenu
        )
        setupMenuButton(
            parent.findViewById(R.id.inventoryLinearLayout),
            R.id.action_inventoryFragment,
            navController,
            characterMenu
        )
        setupMenuButton(
            parent.findViewById(R.id.spellsLinearLayout),
            R.id.action_spellsFragment,
            navController,
            characterMenu
        )
        setupMenuButton(
            parent.findViewById(R.id.additionalInfoLinearLayout),
            R.id.action_additionalInfoFragment,
            navController,
            characterMenu
        )

        characterMenu.animationStyle = androidx.appcompat.R.style.Animation_AppCompat_Dialog
        characterMenu.showAtLocation(view, Gravity.CENTER, 0, 0)

        characterViewModel.showPopUpBackground()

        characterMenu.setOnDismissListener {
            characterViewModel.closePopUpBackground()
        }
    }

    private fun setupMenuButton(
        button: LinearLayout,
        destId: Int,
        navController: NavController,
        characterMenu: PopupWindow
    ) {
        button.apply {
            onPressAnimation(this)
            this.setOnClickListener {
                navController.navigate(destId)
                characterMenu.dismiss()
            }
        }
    }
}