package com.andreyyurko.dnd.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.FragmentHomeBinding
import com.andreyyurko.dnd.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val viewBinding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.directoryButton.setOnClickListener {
            Toast.makeText(requireContext(), "Справочная будет в следующих версиях", Toast.LENGTH_LONG).show()
        }

        viewBinding.charactersListButton.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.charactersListFragment)
        }
    }
}