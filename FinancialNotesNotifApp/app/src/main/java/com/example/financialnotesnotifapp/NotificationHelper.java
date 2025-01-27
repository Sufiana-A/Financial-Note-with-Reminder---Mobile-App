package com.example.financialnotesnotifapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    private static final String CHANNEL_ID = "reminder_channel";
    private static final String CHANNEL_NAME = "Reminder Notifications";
    private static final String CHANNEL_DESCRIPTION = "Channel for financial note reminders";

    public static void showNotification(Context context, String title, String message) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager == null) {
            return; // Jika manager null, tidak ada yang perlu dilakukan
        }

        // Pastikan channel hanya dibuat sekali (untuk Android O ke atas)
        NotificationChannel channel = manager.getNotificationChannel(CHANNEL_ID);
        if (channel == null) {
            channel = new NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription(CHANNEL_DESCRIPTION);
            manager.createNotificationChannel(channel);
        }

        // Intent untuk membuka MainActivity saat notifikasi diklik
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Bangun notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification) // Pastikan drawable ini ada
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        // ID notifikasi unik berbasis timestamp
        int notificationId = (int) System.currentTimeMillis();
        manager.notify(notificationId, builder.build());
    }
}
