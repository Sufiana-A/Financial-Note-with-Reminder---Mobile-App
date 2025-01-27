package com.example.financialnotesnotifapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class FinancialNoteAdapter extends RecyclerView.Adapter<FinancialNoteAdapter.ViewHolder> {

    private List<FinancialNote> notes;

    public void setNotes(List<FinancialNote> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_financial_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FinancialNote note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes != null ? notes.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvKeterangan, tvJumlahUang, tvTanggal;

        ViewHolder(View itemView) {
            super(itemView);
            tvKeterangan = itemView.findViewById(R.id.tvKeterangan);
            tvJumlahUang = itemView.findViewById(R.id.tvJumlahUang);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
        }

        void bind(FinancialNote note) {
            tvKeterangan.setText(note.getKeterangan());
            tvJumlahUang.setText(
                    NumberFormat.getCurrencyInstance(new Locale("id", "ID"))
                            .format(note.getJumlahUang()));
            tvTanggal.setText(note.getTanggal());
        }
    }
}
