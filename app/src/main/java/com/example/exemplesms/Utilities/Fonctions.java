package com.example.exemplesms.Utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;

import com.example.exemplesms.R;

public class Fonctions {

    public static void getNotification(Context context, String titre, String message) {
        Notification notification = new Notification.Builder(context)
                .setContentTitle(titre)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);
    }
}
