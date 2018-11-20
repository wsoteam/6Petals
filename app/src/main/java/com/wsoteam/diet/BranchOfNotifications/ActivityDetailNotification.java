package com.wsoteam.diet.BranchOfNotifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wsoteam.diet.R;
import com.wsoteam.diet.Services.AlarmManagerBR;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ActivityDetailNotification extends AppCompatActivity {
    private EditText edtText, edtDate, edtTime, edtRepeat;
    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

        Date date = new Date();

        edtText = findViewById(R.id.edtActivityDetailNotificationTextOfNotification);
        edtDate = findViewById(R.id.edtActivityDetailNotificationDateOfNotification);
        edtTime = findViewById(R.id.edtActivityDetailNotificationTimeOfNotification);
        edtRepeat = findViewById(R.id.edtActivityDetailNotificationRepeatOfNotification);
        btnSave = findViewById(R.id.btnActivityDetailNotificationSaveNotification);

        final Calendar calendar = new GregorianCalendar();

        Calendar cal = Calendar.getInstance();

        calendar.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, cal.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 54);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDateAlertDialog();
            }
        });

        edtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTimeAlertDialog();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ActivityDetailNotification.this, "LOL", Toast.LENGTH_SHORT).show();
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(ActivityDetailNotification.this, AlarmManagerBR.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(ActivityDetailNotification.this,
                        1, intent, 0);
                alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1000, pendingIntent);

            }
        });
    }

    private void createTimeAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final TimePicker timePicker = new TimePicker(this);
        timePicker.setIs24HourView(true);
        builder.setView(timePicker);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edtTime.setText(String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute()));
            }
        });
        builder.setNeutralButton("отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    private void createDateAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final DatePicker datePicker = new DatePicker(this);
        builder.setView(datePicker);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                edtDate.setText(String.valueOf(datePicker.getDayOfMonth()) + "."
                        + String.valueOf(datePicker.getMonth() + 1) + "."
                        + String.valueOf(datePicker.getYear()));
            }
        });
        builder.setNeutralButton("отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
