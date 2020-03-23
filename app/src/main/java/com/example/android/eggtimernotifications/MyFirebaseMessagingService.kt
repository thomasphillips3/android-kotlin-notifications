package com.example.android.eggtimernotifications

import android.app.Notification
import android.app.NotificationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.android.eggtimernotifications.util.sendNotification
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: ${remoteMessage?.from}")

        // TODO Step 3.5 check messages for data
        remoteMessage?.data?.let {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
        }

        // TODO Step 3.6 check messages for notification and call sendNotification
        remoteMessage?.notification?.let {
            Log.d(TAG, "Message notification body: ${it.body}")
            sendNotification(it.body!!)
        }
    }

    override fun onNewToken(token: String?) {
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) { }

    /**
     * Create and show a simple notification containing the received FCM message
     *
     * @param messageBody FCM message body received
     */
    private fun sendNotification(messageBody: String) {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(messageBody, applicationContext)
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}