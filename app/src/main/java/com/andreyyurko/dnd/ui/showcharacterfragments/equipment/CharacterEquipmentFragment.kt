package com.andreyyurko.dnd.ui.showcharacterfragments.equipment

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentCharacterEquipmentBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterEquipmentFragment : BaseFragment(R.layout.fragment_character_equipment) {

    private val viewBinding by viewBinding(FragmentCharacterEquipmentBinding::bind)

    private lateinit var viewModel: CharacterEquipmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterEquipmentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstWeaponInfo = viewModel.getFirstWeaponInfo()

        viewBinding.firstWeaponName.text = firstWeaponInfo.weaponName
        viewBinding.firstWeaponDamage.text = firstWeaponInfo.toHitBonus + ", " + firstWeaponInfo.damage
        viewBinding.firstWeaponProperties.text = firstWeaponInfo.properties.joinToString()

        val secondWeaponInfo = viewModel.getSecondWeaponInfo()

        secondWeaponInfo?.let {
            viewBinding.secondWeaponName.text = it.weaponName
            viewBinding.secondWeaponDamage.text = secondWeaponInfo.toHitBonus + ", " + it.damage
            viewBinding.secondWeaponProperties.text = it.properties.joinToString()
        }


        val armorInfo = viewModel.getArmorInfo()
        if (armorInfo.armorName != "") {
            viewBinding.armorName.text = armorInfo.armorName
            viewBinding.armorAC.text = armorInfo.ac.toString()
            viewBinding.armorType.text = armorInfo.type
        }

        setupRecyclerView()

        viewBinding.allButton.setOnClickListener {
            findNavController().navigate(R.id.characterChooseEquipmentFragment)
        }
    }

    fun setupRecyclerView() {
        viewBinding.magicItemsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter = EquipmentAdapter(viewModel.inventoryHandler, viewModel.characterViewModel)
        viewBinding.magicItemsRecyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(viewBinding.magicItemsRecyclerView, false)

        adapter.apply {
            listOfItems = viewModel.getEquippedMagicalItems()
            notifyDataSetChanged()
        }
    }

}