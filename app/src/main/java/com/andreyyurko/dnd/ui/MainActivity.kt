package com.andreyyurko.dnd.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.ActivityMainBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.utils.SpellsFavoritesHolder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewBinding by viewBinding(ActivityMainBinding::bind)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}