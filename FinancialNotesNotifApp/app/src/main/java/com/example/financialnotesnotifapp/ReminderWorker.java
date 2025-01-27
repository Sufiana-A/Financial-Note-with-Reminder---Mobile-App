package com.example.financialnotesnotifapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class ReminderWorker extends Worker {

    public ReminderWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    @NonNull
    public Result doWork() {
        // Menampilkan notifikasi pengingat
        NotificationHelper.showNotification(
                getApplicationContext(),
                "Remember!",
                "Jangan lupa mencatat keuangan hari ini!"
        );
        return Result.success();
    }
}
