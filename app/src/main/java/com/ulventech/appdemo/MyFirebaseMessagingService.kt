package com.ulventech.appdemo


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ulventech.appdemo.activities.MainActivity


//class MyFirebaseMessagingService:FirebaseMessagingService() {
//    private val channelId = "notification_channel"
//    private val channelName = "com.ulventech.appdemo"
//
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        Log.d("remote message","${remoteMessage.notification!!.title!!}, ${remoteMessage.notification!!.body!!}")
////        Log.d("remote data","${remoteMessage.data}")
//        if(remoteMessage.notification !=null){
//           generateNotification(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)
//       }
//
//    }
//
//    fun getRemoteView(title: String, message: String):RemoteViews{
//        Log.d("remoteView","jalan")
//        val remoteView = RemoteViews("com.ulventech.appdemo",R.layout.notification)
//        remoteView.setTextViewText(R.id.title, title)
//        remoteView.setTextViewText(R.id.message,message)
//        remoteView.setImageViewResource(R.id.notification_image,R.drawable.ic_baseline_notifications_24)
//        return remoteView
//    }
//    fun generateNotification(title:String,message:String){
//        Log.d("notif","jalan")
//        val intent = Intent(applicationContext,MainActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//
//        val pendingIntent = PendingIntent.getActivity(applicationContext,0,intent,PendingIntent.FLAG_ONE_SHOT)
//
//        var builder:NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
//            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
//            .setAutoCancel(true)
//                .setVibrate(longArrayOf(1000,1000,1000,1000))
//            .setOnlyAlertOnce(true)
//            .setContentIntent(pendingIntent)
//
//        builder = builder.setContent(getRemoteView(title,message))
//
//        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel = NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//        notificationManager.notify(0,builder.build())
//
//    }
//
//}

class MyFirebaseMessagingService : FirebaseMessagingService() {

    var NOTIFICATION_CHANNEL_ID = "com.ulventech.appdemo"
    val NOTIFICATION_ID = 100

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d("message","Message Received ...");

        if (remoteMessage.data.size > 0) {
            val title = remoteMessage.data["title"]
            val body = remoteMessage.data["body"]
            showNotification(applicationContext, title, body)
        } else {
            val title = remoteMessage.notification!!.title
            val body = remoteMessage.notification!!.body
            showNotification(applicationContext, title, body)
        }
    }



    fun showNotification(context: Context, title: String?, message: String?) {
        val intent: Intent
        intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notification: Notification

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Log.e("Notification", "Created in up to orio OS device");
            notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setOngoing(true)
                    .setSmallIcon(getNotificationIcon())
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(Notification.CATEGORY_SERVICE)
                    .setWhen(System.currentTimeMillis())
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle(title).build()

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, title, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.notify(NOTIFICATION_ID, notification)
        }

        else {
            notification = NotificationCompat.Builder(context)
                    .setSmallIcon(getNotificationIcon())
                    .setAutoCancel(true)
                    .setContentText(message)
                    .setContentIntent(pendingIntent)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentTitle(title).build()
            val notificationManager = context.getSystemService(
                    Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    private fun getNotificationIcon(): Int {
        val useWhiteIcon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        return if (useWhiteIcon) R.drawable.ic_baseline_notifications_24 else R.mipmap.ic_launcher
    }

}