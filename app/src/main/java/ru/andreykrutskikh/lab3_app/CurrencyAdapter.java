package ru.andreykrutskikh.lab3_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private List<CurrencyRate> currencyRates;

    public void setCurrencyRates(List<CurrencyRate> currencyRates) {
        this.currencyRates = currencyRates;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        CurrencyRate rate = currencyRates.get(position);
        holder.currencyCode.setText(rate.getCurrencyCode()); // Код валюты (например, USD)
        holder.currencyName.setText(rate.getCurrencyName()); // Название валюты (например, Доллар США)
        holder.rate.setText(String.valueOf(rate.getRate())); // Курс валюты
    }

    @Override
    public int getItemCount() {
        return currencyRates != null ? currencyRates.size() : 0;
    }

    static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        TextView currencyCode; // Код валюты
        TextView currencyName; // Название валюты
        TextView rate;         // Курс валюты

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyCode = itemView.findViewById(R.id.currencyCode);
            currencyName = itemView.findViewById(R.id.currencyName);
            rate = itemView.findViewById(R.id.rate);
        }
    }
}