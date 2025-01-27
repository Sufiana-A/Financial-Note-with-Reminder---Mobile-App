package com.example.financialnotesnotifapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FinancialNote.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    // Menyediakan akses ke DAO
    public abstract FinancialNoteDao financialNoteDao();

    // Singleton instance dari database
    private static volatile AppDatabase INSTANCE;

    // Executor service untuk operasi database
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Metode untuk mendapatkan instance database
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "financial_note_database")
                            .fallbackToDestructiveMigration() // Tambahan opsional
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
