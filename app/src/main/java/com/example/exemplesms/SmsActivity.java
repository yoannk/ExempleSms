package com.example.exemplesms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SmsActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        context = this;

        final EditText txtMessage = findViewById(R.id.txtMessage);
        final EditText txtPhoneNumber = findViewById(R.id.txtPhoneNumber);
        Button btnSensSms = findViewById(R.id.btnSensSms);

        btnSensSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager smsManager = SmsManager.getDefault();

                String smsMessage = txtMessage.getText().toString().trim();
                String phoneNumber = txtPhoneNumber.getText().toString().trim().replaceAll("\\D", "");

                Intent sendIntent = new Intent("SMS_SENT");
                Intent deliveredIntent = new Intent("SMS_DELIVERED");

                PendingIntent piSend = PendingIntent.getBroadcast(context, 0, sendIntent, 0);
                PendingIntent piDelivered = PendingIntent.getBroadcast(context, 0, deliveredIntent, 0);

                if (smsMessage.isEmpty()) {
                    Toast.makeText(context, "Veuillez saisir un message", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(context, "Veuillez saisir un numéro de téléphone", Toast.LENGTH_SHORT).show();
                    return;
                }

                smsManager.sendTextMessage(phoneNumber, null, smsMessage, piSend, piDelivered);
            }
        });
    }
}
