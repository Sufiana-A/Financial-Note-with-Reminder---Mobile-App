package com.example.financialnotesnotifapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class IncomeFragment extends Fragment {

    private FinancialNoteAdapter adapter;
    private FinancialNoteRepository repository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewIncome);
        adapter = new FinancialNoteAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        repository = new FinancialNoteRepository(requireActivity().getApplication());
        repository.getNotesByType("pemasukan").observe(getViewLifecycleOwner(), new Observer<List<FinancialNote>>() {
            @Override
            public void onChanged(List<FinancialNote> notes) {
                adapter.setNotes(notes);
            }
        });

        return view;
    }
}
