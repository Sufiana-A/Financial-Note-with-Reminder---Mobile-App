package com.example.financialnotesnotifapp;

import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager2 viewPager;
    public FloatingActionButton fabAddNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fabAddNote = findViewById(R.id.fabAddNote);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // Mengatur nama tab sesuai posisi
            switch (position) {
                case 0:
                    tab.setText("Pemasukan");
                    break;
                case 1:
                    tab.setText("Pengeluaran");
                    break;
            }
        }).attach(); // Menghubungkan TabLayout dan ViewPager2

        fabAddNote.setOnClickListener(v -> {
            // Navigasi ke AddNoteActivity untuk menambahkan catatan baru
            startActivity(new Intent(MainActivity.this, AddNoteActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("MenuClick", "Menu item clicked: " + item.getItemId());
        if (item.getItemId() == R.id.action_settings) {
            Log.d("MenuClick", "Settings menu item clicked");
            Intent intent = new Intent(MainActivity.this, NotificationSettingsActivity.class);
            startActivity(intent); // Menambahkan intent untuk membuka NotificationSettingsActivity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
