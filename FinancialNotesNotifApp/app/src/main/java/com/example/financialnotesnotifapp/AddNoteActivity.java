package com.example.financialnotesnotifapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity {

    private EditText etKeterangan, etJumlahUang;
    private FinancialNoteViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        etKeterangan = findViewById(R.id.etKeterangan);
        etJumlahUang = findViewById(R.id.etJumlahUang);
        RadioGroup rgTipe = findViewById(R.id.rgTipe);

        viewModel = new FinancialNoteViewModel(getApplication());

        findViewById(R.id.btnSave).setOnClickListener(v -> {
            String keterangan = etKeterangan.getText().toString().trim();
            String jumlahUang = etJumlahUang.getText().toString().trim();

            int selectedTipeId = rgTipe.getCheckedRadioButtonId();
            String tipe = selectedTipeId == R.id.rbPemasukan ? "pemasukan" : "pengeluaran";

            if (keterangan.isEmpty() || jumlahUang.isEmpty() || selectedTipeId == -1) {
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double jumlah = Double.parseDouble(jumlahUang);

                // Menyimpan data catatan keuangan
                FinancialNote note = new FinancialNote(
                        keterangan,
                        new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()),
                        jumlah,
                        tipe
                );
                viewModel.insert(note);

                Toast.makeText(this, "Catatan berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                finish();
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Jumlah uang harus berupa angka yang valid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
