package com.andreyyurko.dnd.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.andreyyurko.dnd.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@AndroidEntryPoint
class CharacterSavingService : Service() {

    @Inject
    lateinit var charactersHolder: CharactersHolder

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        charactersHolder.saveCharactersForService()

        val CHANNELID = "Foreground Service ID"
        val channel = NotificationChannel(
            CHANNELID,
            CHANNELID,
            NotificationManager.IMPORTANCE_LOW
        )

        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        val notification = Notification.Builder(this, CHANNELID)
            .setContentText(getText(R.string.saving_characters_notification_title))
            .setContentTitle(getText(R.string.saving_characters_notification_message))
            .setSmallIcon(R.drawable.dragon_icon)

        startForeground(1001, notification.build())

        scope.launch {
            charactersHolder.savingCharactersState.collect {
                if (it == CharactersHolder.SavingCharactersState.Completed) {
                    Log.d("work", "stopped!")
                    stopSelf()
                }
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
