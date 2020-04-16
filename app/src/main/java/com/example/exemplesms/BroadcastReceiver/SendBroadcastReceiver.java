package com.example.exemplesms.BroadcastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SendBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (action.equals("SMS_SENT")) {
            int code = getResultCode();

            switch (code) {
                case Activity.RESULT_OK:
                    Toast.makeText(context, "SMS Sent", Toast.LENGTH_SHORT).show();
                    break;
                case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                    Toast.makeText(context, "Erreur d'envoi", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(context, "Erreur autre", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
