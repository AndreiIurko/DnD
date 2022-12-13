package com.andreyyurko.dnd.ui.characterinventory

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.andreyyurko.dnd.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.data.characters.Armor
import com.andreyyurko.dnd.databinding.FragmentCharacterInventoryBinding
import com.andreyyurko.dnd.utils.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterInventoryFragment : Fragment(R.layout.fragment_character_inventory) {

    @Inject
    lateinit var characterViewModel: CharacterViewModel

    private val viewBinding by viewBinding(FragmentCharacterInventoryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (characterViewModel.shownCharacter.characterInfo.currentState.armor == Armor.NoArmor) {
            characterViewModel.shownCharacter.characterInfo.currentState.armor = Armor.StuddedLeather
            characterViewModel.updateCharacterInfo()
        }
        else {
            characterViewModel.shownCharacter.characterInfo.currentState.armor = Armor.NoArmor
            characterViewModel.updateCharacterInfo()
        }
        Log.d("Priority", characterViewModel.shownCharacter.characterInfo.currentState.armor.armorName)
    }
}