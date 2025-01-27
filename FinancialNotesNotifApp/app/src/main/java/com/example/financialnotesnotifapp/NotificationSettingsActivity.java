package com.example.financialnotesnotifapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class NotificationSettingsActivity extends AppCompatActivity {

    private TimePicker timePicker;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        timePicker = findViewById(R.id.timePicker);

        findViewById(R.id.btnSetReminder).setOnClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            long delayInMillis = calculateDelayInMillis(hour, minute);

            if (delayInMillis > 0) {
                OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(ReminderWorker.class)
                        .setInitialDelay(delayInMillis, TimeUnit.MILLISECONDS)
                        .build();

                WorkManager.getInstance(getApplicationContext()).enqueue(workRequest);

                Toast.makeText(this, "Reminder set for: " + hour + ":" + String.format("%02d", minute), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid time selected. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Calculates the delay in milliseconds for the given time (hour and minute).
     * If the selected time is in the past, schedules for the next day.
     *
     * @param hour   The hour of the day (24-hour format).
     * @param minute The minute of the hour.
     * @return Delay in milliseconds.
     */
    private long calculateDelayInMillis(int hour, int minute) {
        Calendar now = Calendar.getInstance();
        Calendar selectedTime = Calendar.getInstance();
        selectedTime.set(Calendar.HOUR_OF_DAY, hour);
        selectedTime.set(Calendar.MINUTE, minute);
        selectedTime.set(Calendar.SECOND, 0);
        selectedTime.set(Calendar.MILLISECOND, 0);

        if (selectedTime.before(now)) {
            selectedTime.add(Calendar.DAY_OF_YEAR, 1);
        }

        return selectedTime.getTimeInMillis() - now.getTimeInMillis();
    }
}
