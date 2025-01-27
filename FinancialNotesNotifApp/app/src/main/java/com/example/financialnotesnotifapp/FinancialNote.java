package com.example.financialnotesnotifapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "financial_notes")
public class FinancialNote {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String keterangan;
    private String tanggal;
    private double jumlahUang;
    private String tipe; // Menyimpan "pemasukan" atau "pengeluaran"

    public FinancialNote(String keterangan, String tanggal, double jumlahUang, String tipe) {
        this.keterangan = keterangan;
        this.tanggal = tanggal;
        this.jumlahUang = jumlahUang;
        this.tipe = tipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public double getJumlahUang() {
        return jumlahUang;
    }

    public void setJumlahUang(double jumlahUang) {
        this.jumlahUang = jumlahUang;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
}
