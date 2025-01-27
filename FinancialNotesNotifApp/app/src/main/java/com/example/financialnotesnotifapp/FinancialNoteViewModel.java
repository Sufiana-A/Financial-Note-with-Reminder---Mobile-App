package com.example.financialnotesnotifapp;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class FinancialNoteViewModel extends AndroidViewModel {

    private final FinancialNoteRepository repository;

    public FinancialNoteViewModel(Application application) {
        super(application);
        repository = new FinancialNoteRepository(application);
    }

    public void insert(FinancialNote note) {
        repository.insert(note);
    }

    // Pastikan untuk memanfaatkan metode berikut jika tidak ingin menghapusnya.
    public LiveData<Double> getTotal(String tanggal) {
        return repository.getTotal(tanggal);
    }

    public LiveData<List<FinancialNote>> getNotes(String tanggal) {
        return repository.getNotes(tanggal);
    }
}

