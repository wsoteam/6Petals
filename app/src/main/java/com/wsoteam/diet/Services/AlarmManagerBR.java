package com.wsoteam.diet.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.wsoteam.diet.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmManagerBR extends BroadcastReceiver {
    public static String TAG = "AlarmManagerBR";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LOL", "dorou");
        Toast.makeText(context, "LOL", Toast.LENGTH_SHORT).show();
        createNotification(context);
    }

    private void createNotification(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_flasher)
                .setContentTitle("Dorou")
                .setContentText("lol kek lol kek")
                /*.setVibrate(new long[] {1000, 1000, 1000, 1000, 1000})*/;
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
