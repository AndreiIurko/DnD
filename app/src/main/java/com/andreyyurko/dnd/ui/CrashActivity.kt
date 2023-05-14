package com.andreyyurko.dnd.ui


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.andreyyurko.dnd.R
import com.andreyyurko.dnd.databinding.ActivityCrashBinding
import by.kirich1409.viewbindingdelegate.viewBinding
import java.io.FileOutputStream


class CrashActivity: AppCompatActivity(R.layout.activity_crash) {

    private val viewBinding by viewBinding(ActivityCrashBinding::bind)

    override fun onStart() {
        super.onStart()

        viewBinding.saveStackTraceTextView.setOnClickListener {
            createFile()
        }

        viewBinding.exitAppTextView.setOnClickListener {
            this.finishAffinity()
        }

        this.onBackPressedDispatcher.addCallback {
            this@CrashActivity.finishAffinity()
            handleOnBackPressed()
        }
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            // There are no request codes
            val uri: Uri? = result.data?.data

            try {
                val body = intent.getStringExtra("exception_value")
                contentResolver.openFileDescriptor(uri!!, "w")?.use {
                    FileOutputStream(it.fileDescriptor).use { stream ->
                        stream.write(
                            body!!.toByteArray()
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun createFile() {
        val intent = Intent()
            .addCategory(Intent.CATEGORY_OPENABLE)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TITLE, "crash_log.txt")
            .setAction(Intent.ACTION_CREATE_DOCUMENT)
        resultLauncher.launch(intent)
    }
}