package com.andreyyurko.dnd.ui.showcharacterfragments.chooseequipment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.data.inventory.InventoryItemInfo
import com.andreyyurko.dnd.databinding.FragmentCharacterChooseEquipmentBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterChooseEquipmentFragment :
    BaseFragment(R.layout.fragment_character_choose_equipment) {

    private val viewBinding by viewBinding(FragmentCharacterChooseEquipmentBinding::bind)

    private lateinit var viewModel: CharacterChooseEquipmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CharacterChooseEquipmentViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.weaponsButton.setOnClickListener {
            setupButton(
                viewBinding.weaponsRecyclerView,
                viewBinding.weaponsArrowImage,
                viewModel.getWeaponItems()
            )
        }

        viewBinding.armorButton.setOnClickListener {
            setupButton(
                viewBinding.armorRecyclerView,
                viewBinding.armorArrowImage,
                viewModel.getArmorItems()
            )
        }

        viewBinding.magicWeaponsButton.setOnClickListener {
            setupButton(
                viewBinding.magicWeaponsRecyclerView,
                viewBinding.magicWeaponsArrowImage,
                viewModel.getMagicWeapons()
            )
        }

        viewBinding.magicArtifactsButton.setOnClickListener {
            setupButton(
                viewBinding.magicArtifactsRecyclerView,
                viewBinding.magicArtifactsArrowImage,
                viewModel.getArtifacts()
            )
        }

        viewBinding.consumablesButton.setOnClickListener {
            setupButton(
                viewBinding.consumablesRecyclerView,
                viewBinding.consumablesArrowImage,
                viewModel.getConsumables()
            )
        }

        viewBinding.equippedButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setupRecyclerView(recyclerView: RecyclerView, list: List<InventoryItemInfo>) {
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val adapter =
            ChooseEquipmentAdapter(viewModel.inventoryHandler, viewModel.characterViewModel)
        recyclerView.adapter = adapter

        adapter.apply {
            itemsListWithoutCopies = list
            itemsList = createListWithCopies()
            notifyDataSetChanged()
        }
    }

    private fun setupButton(
        recyclerView: RecyclerView,
        image: ImageView,
        list: List<InventoryItemInfo>
    ) {
        if (recyclerView.visibility == View.GONE) {
            setupRecyclerView(recyclerView, list)
            recyclerView.visibility = View.VISIBLE
            image.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_up_24
                )
            )
        } else {
            recyclerView.visibility = View.GONE
            image.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_keyboard_arrow_down_24
                )
            )
        }
    }

}