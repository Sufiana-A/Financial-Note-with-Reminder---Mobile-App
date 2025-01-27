package com.example.financialnotesnotifapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FinancialNoteDao {

    @Insert
    void insert(FinancialNote note);

    @Query("SELECT * FROM financial_notes WHERE tanggal LIKE :tanggal")
    LiveData<List<FinancialNote>> getNotesByDate(String tanggal);

    @Query("SELECT SUM(jumlahUang) FROM financial_notes WHERE tanggal LIKE :tanggal")
    LiveData<Double> getTotalByDate(String tanggal);

    // Query baru: Mendapatkan total berdasarkan tanggal dan tipe
    @Query("SELECT SUM(jumlahUang) FROM financial_notes WHERE tanggal LIKE :tanggal AND tipe = :tipe")
    LiveData<Double> getTotalByType(String tanggal, String tipe);

    // Query baru: Mendapatkan catatan berdasarkan tanggal dan tipe
    @Query("SELECT * FROM financial_notes WHERE tanggal LIKE :tanggal AND tipe = :tipe")
    LiveData<List<FinancialNote>> getNotesByDateAndType(String tanggal, String tipe);

    @Query("SELECT * FROM financial_notes WHERE tipe = :tipe")
    LiveData<List<FinancialNote>> getNotesByType(String tipe);
}
