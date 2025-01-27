package com.example.financialnotesnotifapp;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;

public class FinancialNoteRepository {

    private final FinancialNoteDao financialNoteDao;

    public FinancialNoteRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        financialNoteDao = db.financialNoteDao();
    }

    public void insert(FinancialNote note) {
        if (!note.getTipe().equals("pemasukan") && !note.getTipe().equals("pengeluaran")) {
            Log.e("FinancialNoteRepository", "Invalid type: " + note.getTipe());
            return;
        }
        Log.d("FinancialNoteRepository", "Inserting: " + note);
        AppDatabase.databaseWriteExecutor.execute(() -> financialNoteDao.insert(note));
    }

    public LiveData<Double> getTotal(String tanggal) {
        return financialNoteDao.getTotalByDate(tanggal);
    }

    public LiveData<List<FinancialNote>> getNotes(String tanggal) {
        return financialNoteDao.getNotesByDate(tanggal);
    }

    public LiveData<Double> getTotalByType(String tanggal, String tipe) {
        return financialNoteDao.getTotalByType(tanggal, tipe);
    }

    public LiveData<List<FinancialNote>> getNotesByDateAndType(String tanggal, String tipe) {
        return financialNoteDao.getNotesByDateAndType(tanggal, tipe);
    }

    public LiveData<List<FinancialNote>> getNotesByType(String tipe) {
        return financialNoteDao.getNotesByType(tipe);
    }
}
