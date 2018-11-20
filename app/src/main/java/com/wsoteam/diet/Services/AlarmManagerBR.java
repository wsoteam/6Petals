package com.wsoteam.diet.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmManagerBR extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LOL", "dorou");
        Toast.makeText(context, "LOL", Toast.LENGTH_SHORT).show();
    }
}
