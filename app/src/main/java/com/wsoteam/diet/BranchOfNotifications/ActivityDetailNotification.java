package com.wsoteam.diet.BranchOfNotifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wsoteam.diet.POJOForDB.ObjectForNotification;
import com.wsoteam.diet.R;
import com.wsoteam.diet.Services.AlarmManagerBR;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ActivityDetailNotification extends AppCompatActivity {
    private EditText edtText, edtDate, edtTime, edtRepeat;
    private Button btnSave;
    private ObjectForNotification objectForNotification;
    final static String TAG_OF_ACTIVITY = "ActivityDetailNotification";
    private int idOfSelectedItem = 0;
    private ArrayList<ObjectForNotification> notificationArrayList;
    private long tempId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(ActivityDetailNotification.this, AlarmManagerBR.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ActivityDetailNotification.this,
                (int) tempId, intent, 0);
        pendingIntent.cancel();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

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

        idOfSelectedItem = getIntent().getIntExtra(TAG_OF_ACTIVITY, 0);


        if (idOfSelectedItem == -1) {
            objectForNotification = new ObjectForNotification();

            tempId = cal.getTimeInMillis();

            edtDate.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH))
                    + "." + String.valueOf(cal.get(Calendar.MONTH))
                    + "." + String.valueOf(cal.get(Calendar.YEAR)));

            //TODO write 0 until one number

            edtTime.setText(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(cal.get(Calendar.MINUTE)));

            edtRepeat.setText("Однократно");


        } else {
            notificationArrayList = (ArrayList<ObjectForNotification>) ObjectForNotification.listAll(ObjectForNotification.class);
            objectForNotification = notificationArrayList.get(idOfSelectedItem);

            tempId = objectForNotification.getOwnId();
            edtRepeat.setText(objectForNotification.getRepeat());
            edtTime.setText(String.valueOf(objectForNotification.getHour()) + ":" + String.valueOf(objectForNotification.getMinute()));
            edtDate.setText(String.valueOf(objectForNotification.getDay()) + "."
                    + String.valueOf(objectForNotification.getMonth()) + "."
                    + String.valueOf(objectForNotification.getYear()));
            edtText.setText(objectForNotification.getText());
        }

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

        edtRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createRepeatCountAlertDialog();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtText.getText().toString().equals("") || edtText.getText().toString().equals(" ")) {
                    Toast.makeText(ActivityDetailNotification.this, "Вы забыли про текст напоминания", Toast.LENGTH_SHORT).show();
                } else {
                    saveObject();
                }
            }
        });


    }


    private void saveObject() {
        try {
            String[] date = edtDate.getText().toString().split("\\.");
            String[] time = edtTime.getText().toString().split(":");
            objectForNotification.setText(edtText.getText().toString());
            objectForNotification.setDay(Integer.parseInt(date[0]));
            objectForNotification.setMonth(Integer.parseInt(date[1]));
            objectForNotification.setYear(Integer.parseInt(date[2]));
            objectForNotification.setMinute(Integer.parseInt(time[1]));
            objectForNotification.setHour(Integer.parseInt(time[0]));
            objectForNotification.setRepeat(edtRepeat.getText().toString());
            objectForNotification.setOwnId(tempId);

            if (idOfSelectedItem == -1) {
                objectForNotification.save();

                finish();
            } else {
                ObjectForNotification.deleteAll(ObjectForNotification.class);
                notificationArrayList.remove(idOfSelectedItem);
                objectForNotification.save();
                for (int i = 0; i < notificationArrayList.size(); i++) {
                    notificationArrayList.get(i).save();
                }
                finish();
            }
            createAlarmSchedule(objectForNotification);

        } catch (Exception e) {
            Toast.makeText(this, "Что-то пошло не так, попробуйте позже", Toast.LENGTH_SHORT).show();
            Log.e("Error", "Unknown error");
        }
    }


    //TODO reboot device
    private void createAlarmSchedule(ObjectForNotification objectForNotification) {
        final Calendar calendar = new GregorianCalendar();

        calendar.set(Calendar.YEAR, objectForNotification.getYear());
        calendar.set(Calendar.MONTH, objectForNotification.getMonth());
        calendar.set(Calendar.DAY_OF_MONTH, objectForNotification.getDay());
        calendar.set(Calendar.HOUR_OF_DAY, objectForNotification.getHour());
        calendar.set(Calendar.MINUTE, objectForNotification.getMinute());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(ActivityDetailNotification.this, AlarmManagerBR.class);
        intent.putStringArrayListExtra()
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ActivityDetailNotification.this,
                (int) objectForNotification.getOwnId(), intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000, pendingIntent);
    }

    private void createRepeatCountAlertDialog() {
        final RadioGroup rgRepeatCount;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final View view = View.inflate(this, R.layout.alert_dialog_repeat_count, null);
        rgRepeatCount = view.findViewById(R.id.rgRepeatCount);
        builder.setView(view);

        builder.setPositiveButton("ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RadioButton checkedRadioButton = view.findViewById(rgRepeatCount.getCheckedRadioButtonId());
                edtRepeat.setText(checkedRadioButton.getText());
            }
        });
        builder.setNegativeButton("отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
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
