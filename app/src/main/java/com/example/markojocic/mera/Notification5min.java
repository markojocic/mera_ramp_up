package com.example.tony.mera3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
//Creating instance of object Notification5min and setting title and text for notification in main activity
//Notification5min notification5min1 = new Notification5min(this);
//notification5min1.setNotification5min("text", "title");
public class Notification5min {

    Context context;

    String contentTitle;
    String contentText;

    public Notification5min(Context context){

        this.context = context;



    }
    public void setNotification5min(String contentText, String contentTitle)
    {
        NotificationManager notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        final int NOTIFY_ID = 1002;
        String name = "my_chanel";
        String id = "my_chanel1";
        String description = "first_chanel";

        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;


        // Create Notification for Oreo and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChanel = notifManager.getNotificationChannel(id);

            if (mChanel == null) {
                mChanel = new NotificationChannel(id, name, importance);
                mChanel.setDescription(description);
                notifManager.createNotificationChannel(mChanel);

            }

            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            builder.setContentTitle(contentTitle)
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)
                    .setContentText(contentText)
                    .setDefaults(Notification.DEFAULT_ALL);
        }

        //Create Notification for api below Oreo
        else{
            builder = new NotificationCompat.Builder(context);

            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            builder.setContentTitle(contentTitle)                           // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder) // required
                    .setContentText(contentText)  // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setPriority(Notification.PRIORITY_HIGH);

        }


        Notification notification = builder.build();
        notifManager.notify(NOTIFY_ID, notification);
    }



}
