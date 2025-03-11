package ru.andreykrutskikh.lab3_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends ComponentActivity {
    private CurrencyViewModel viewModel;
    private CurrencyAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CurrencyAdapter();
        recyclerView.setAdapter(adapter);

        progressBar = findViewById(R.id.progressBar);

        viewModel = new ViewModelProvider(this).get(CurrencyViewModel.class);
        viewModel.getCurrencyRates().observe(this, currencyRates -> {
            adapter.setCurrencyRates(currencyRates);
            progressBar.setVisibility(View.GONE); // Скрыть ProgressBar после загрузки
        });

        viewModel.getError().observe(this, error -> {
            progressBar.setVisibility(View.GONE); // Скрыть ProgressBar в случае ошибки
            Snackbar.make(findViewById(android.R.id.content), error, Snackbar.LENGTH_SHORT).show();
        });

        findViewById(R.id.refreshButton).setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE); // Показать ProgressBar
            viewModel.loadCurrencyRates();
        });

        // Загрузить данные при запуске приложения
        progressBar.setVisibility(View.VISIBLE);
        viewModel.loadCurrencyRates();
    }
}