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
        val coinNames = getCoinsWithPriceAboveThreshold(remoteMessage.dataOfMap, 0.2)
        if (coinNames.isNotEmpty()) {
            showNotification(coinNames)
        }
    }

    private fun getCoinsWithPriceAboveThreshold(data: Map<String, String>, threshold: Double): List<String> {
        val coins = mutableListOf<String>()
        data.forEach { (key, value) ->
            val price = value.toDoubleOrNull()
            if (price != null && price > threshold) {
                coins.add(key)
            }
        }
        return coins
    }

    private fun showNotification(coinNames: List<String>) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val contentText = "Fiyatı 0.2'den büyük olan coinler: ${coinNames.joinToString(", ")}"
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)  // Bildirim simgesi
            .setContentTitle("Kripto Para Fiyat Güncellemesi")  // Başlık
            .setContentText(contentText)  // İçerik
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(this)) {
            notify(0, notification)
        }
    }
}
