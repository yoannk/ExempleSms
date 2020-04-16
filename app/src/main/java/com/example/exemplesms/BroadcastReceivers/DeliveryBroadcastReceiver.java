package com.example.exemplesms.BroadcastReceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DeliveryBroadcastReceiver  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals("SMS_DELIVERED")) {
            int code = getResultCode();

            switch (code) {
                case Activity.RESULT_OK:
                    Toast.makeText(context, "SMS Delivered", Toast.LENGTH_SHORT).show();
                    break;
                case Activity.RESULT_CANCELED:
                    Toast.makeText(context, "Erreur de reception", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context, "Erreur autre", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
