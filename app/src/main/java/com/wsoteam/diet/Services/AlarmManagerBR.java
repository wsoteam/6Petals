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
    public static String TAG_FOR_TEXT = "AlarmManagerBR_TAG_FOR_TEXT";
    public static String TAG_FOR_ID = "AlarmManagerBR_TAG_FOR_ID";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LOL", "dorou");
        Toast.makeText(context, "LOL", Toast.LENGTH_SHORT).show();
        createNotification(context, intent.getStringExtra(TAG_FOR_TEXT), intent.getLongExtra(TAG_FOR_ID, 0));
    }

    private void createNotification(Context context, String textOfNotification, long id){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification_flasher)
                .setContentTitle(textOfNotification)
                /*.setVibrate(new long[] {1000, 1000, 1000, 1000, 1000})*/;
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify((int) id, notification);
    }
}
