package com.example.abhishek.rajasthane_paryatanam;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

/**
 * Created by ABHISHEK on 12/02/2017.
 */

public class MyBroadCastReciever extends BroadcastReceiver {
    static int countPowerOff=1;
    private Activity activity=null;

    public MyBroadCastReciever()
    {

    }
    public MyBroadCastReciever(Activity activity)
    {
        this.activity=activity;
    }
    @Override
    public void onReceive(Context context, Intent intent) {

        //Toast.makeText(context, "power button clicked", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            //Take count of the screen off position
            countPowerOff++;
            //Toast.makeText(context, "ACTION_SCREEN_OFF", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            //Take count of the screen on position
            if(countPowerOff==3)
            {
                //Toast.makeText(context, "ACTION_SCREEN_ON", Toast.LENGTH_SHORT).show();
                //String phoneNumber = "9973090548";
                String phoneNumbers[] = {"7504443543", "7992339302", "9006931611"};
                String message = "Hello I'm Abhishek, I'm in Trouble!";
                SmsManager smsManager = SmsManager.getDefault();
                for(String number: phoneNumbers )
                smsManager.sendTextMessage(number, null, message, null, null);

                /*Intent i =new Intent(activity,RegisterActivity.class);
                activity.startActivity(i);*/
            }
        }
    }

}