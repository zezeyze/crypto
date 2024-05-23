package com.zeynepdogru.cryptoapp.Push

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.huawei.hms.push.HmsMessageService
import com.huawei.hms.push.RemoteMessage
import com.zeynepdogru.cryptoapp.R
import com.zeynepdogru.cryptoapp.view.MainActivity

class MyPushService : HmsMessageService() {
    private val CHANNEL_ID = "my_notification_channel"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.i("PushKit", "Push mesajı alındı: ${remoteMessage.data}")
        showNotification(remoteMessage)
    }

    private fun showNotification(remoteMessage: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)  // Bildirim simgesi
            .setContentTitle(remoteMessage.notification?.title ?: "Yeni Bildirim")  // Başlık
            .setContentText(remoteMessage.notification?.body ?: "Mesaj içeriği")  // İçerik
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(0, notification)
        }
    }
}