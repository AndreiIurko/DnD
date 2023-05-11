package com.andreyyurko.dnd.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.ActivityMainBinding
import com.andreyyurko.dnd.utils.CharacterSavingService
import com.andreyyurko.dnd.utils.CharactersHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var charactersHolder: CharactersHolder

    private val viewBinding by viewBinding(ActivityMainBinding::bind)
    private lateinit var viewModel: MainViewModel

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.initCharacterHolder()
        // Set up an OnPreDrawListener to the root view.
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check whether the initial data is ready.
                    return if (viewModel.isReady) {
                        // The content is ready. Start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content isn't ready. Suspend.
                        false
                    }

                }
            }
        )

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            // Create your custom animation.
            val fadeOut = ObjectAnimator.ofFloat(
                splashScreenView.view,
                "alpha",
                1f,
                0f
            )
            fadeOut.duration = 400L

            // Call SplashScreenView.remove at the end of your custom animation.
            fadeOut.doOnEnd {
                splashScreenView.remove()
            }

            // Run your animation.
            fadeOut.start()
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    override fun onResume() {
        scope.launch {
            saveCharacters()
        }
        super.onResume()
    }

    override fun onPause() {
        scope.cancel()
        super.onPause()
    }

    override fun onStop() {
        val serviceIntent = Intent(this, CharacterSavingService::class.java)
        startService(serviceIntent)
        super.onStop()
    }

    override fun onDestroy() {
        val serviceIntent = Intent(this, CharacterSavingService::class.java)
        startForegroundService(serviceIntent)
        super.onDestroy()
    }

    private suspend fun saveCharacters() {
        // every 3 minutes save characters
        while (true) {
            delay(3 * 60 * 1000)
            charactersHolder.saveCharacters()
        }
    }
}