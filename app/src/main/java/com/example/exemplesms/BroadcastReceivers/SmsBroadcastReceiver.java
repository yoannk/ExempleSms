package com.example.exemplesms.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.example.exemplesms.Utilities.Fonctions;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action.equals("android.provider.Telephony.SMS_RECEIVER")) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messageList = new SmsMessage[pdus.length];
                int pos = 0;
                for (Object msgPdu : pdus) {
                    messageList[pos] =
                            SmsMessage.createFromPdu((byte[]) msgPdu, bundle.getString("format"));
                    pos++;
                }

                if (messageList.length > 0) {
                    String messageBody = "";
                    String messagePhoneNb = "";

                    for (SmsMessage message : messageList) {
                        messageBody = message.getMessageBody();
                        messagePhoneNb = message.getDisplayOriginatingAddress();
                    }

                    if (!messageBody.isEmpty() && !messagePhoneNb.isEmpty()) {
                        Fonctions.getNotification(context, "SMS reçu de " + messagePhoneNb, messageBody);
                    }
                }
            }
        }
    }
}
