package com.andreyyurko.dnd.ui.showcharacterfragments.main

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.activity.OnBackPressedCallback
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.view.children
import androidx.lifecycle.Observer
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterMainBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import com.andreyyurko.dnd.utils.CharacterViewModel
import com.andreyyurko.dnd.utils.PhotoPicker
import com.andreyyurko.dnd.utils.onPressAnimation
import com.google.android.material.checkbox.MaterialCheckBox
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applyInsetter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.inject.Inject


@AndroidEntryPoint
class CharacterMainFragment : BaseFragment(R.layout.fragment_character_main) {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    private val viewBinding by viewBinding(FragmentCharacterMainBinding::bind)
    private val destinationList: MutableList<Int> = mutableListOf(R.id.action_abilitiesFragment)

    private val singlePhotoPickerLauncher = registerForActivityResult(PhotoPicker()) { imageUri: Uri? ->
        imageUri?.let(characterViewModel::setImageUri)
    }

    private fun pickPhoto() = singlePhotoPickerLauncher.launch(Unit)

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

        viewBinding.spellSlotsLinearLayout.applyInsetter {
            type(statusBars = true) { margin() }
        }

        characterViewModel.shownCharacter.image?.let {
            viewBinding.iconImageButton.setImageBitmap(it)
        }
        setupSlots()

        viewBinding.spellSlotsButton.setOnClickListener {
            if (viewBinding.spellSlotsLinearLayout.translationX == 0f) {
                ObjectAnimator.ofFloat(viewBinding.spellSlotsLinearLayout, "translationX",
                    viewBinding.spellSlotsLinearLayout.translationX, -252*resources.displayMetrics.density).start()
                ObjectAnimator.ofFloat(viewBinding.spellSlotsButton, "translationX",
                    viewBinding.spellSlotsButton.translationX, -2f).start()
            }
            else {
                ObjectAnimator.ofFloat(viewBinding.spellSlotsLinearLayout, "translationX",
                    viewBinding.spellSlotsLinearLayout.translationX, 0f).start()
                ObjectAnimator.ofFloat(viewBinding.spellSlotsButton, "translationX",
                    viewBinding.spellSlotsButton.translationX, 250*resources.displayMetrics.density).start()
            }
        }

        viewBinding.iconImageButton.setOnClickListener {
            pickPhoto()
            viewLifecycleOwner.lifecycle.coroutineScope.launch {
                characterViewModel.imageState.collectLatest { imageState ->
                    imageState.imageBitmap?.let {
                        val out = ByteArrayOutputStream()
                        it.asAndroidBitmap().compress(Bitmap.CompressFormat.JPEG, 20, out)
                        val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(out.toByteArray()))
                        characterViewModel.saveBitmap(bitmap)
                        viewBinding.iconImageButton.setImageBitmap(bitmap)
                    }
                }
            }
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
            characterViewModel.changeShownCharacter()
            findNavController().navigate(R.id.action_characterMainFragment_to_character_creation_nav_graph)
            //TODO("navigate to changing character stats, abilities, etc.")
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
        if (characterViewModel.shownCharacter.characterInfo.currentState.hp == null) {
            characterViewModel.shownCharacter.characterInfo.currentState.hp = characterViewModel.shownCharacter.characterInfo.hp
        }
        viewBinding.hpEditText.setText(characterViewModel.shownCharacter.characterInfo.currentState.hp.toString())
        viewBinding.hpEditText.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                viewBinding.hpEditText.clearFocus()
                characterViewModel.shownCharacter.characterInfo.currentState.hp = viewBinding.hpEditText.text.toString().toInt()
                characterViewModel.updateCharacterInfo()
            }
            false
        }

        viewBinding.maxHpTextView.text = "/" + characterViewModel.shownCharacter.characterInfo.hp.toString()
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
        parent.setBackgroundColor(context.getColor(R.color.background))

        val focus = true
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val characterMenu = PopupWindow(parent, wid, high, focus)

        val navController =
            (childFragmentManager.findFragmentById(R.id.mainFragmentNavigationHost) as NavHostFragment).navController

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (destinationList.size >= 2) {
                        destinationList.removeLast()
                        navController.navigate(destinationList.last())
                    } else {
                        findNavController().popBackStack()
                    }
                }
            }
        )

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
        setupMenuButton(
            parent.findViewById(R.id.equippedInventoryLinearLayout),
            R.id.action_equpmentFragment,
            navController,
            characterMenu
        )
        setupMenuButton(
            parent.findViewById(R.id.notesLinearLayout),
            R.id.action_notesFragment,
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
                destinationList.add(destId)
                characterMenu.dismiss()
            }
        }
    }

    private fun setupSlots() {
        for (i in 0 until viewBinding.spellSlotsLinearLayout.childCount) {
            setupLinearLayout(viewBinding.spellSlotsLinearLayout.getChildAt(i) as LinearLayout, i+1)
        }
    }

    private fun setupLinearLayout(linearLayout: LinearLayout, level: Int) {
        for (i in 1 until linearLayout.childCount) {
            val checkBox: MaterialCheckBox = linearLayout.getChildAt(i) as MaterialCheckBox
            val charges = characterViewModel.shownCharacter.characterInfo.currentState.charges["Ячейки_$level"]
            if (charges == null) {
                checkBox.isEnabled = false
                continue
            }
            if (charges.maximum < i) {
                checkBox.isEnabled = false
                continue
            }

            checkBox.isChecked = charges.current >= i

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    charges.current += 1
                    characterViewModel.updateCharacterInfo()
                }
                else {
                    charges.current -= 1
                    characterViewModel.updateCharacterInfo()
                }
            }
        }
    }
}