package ru.andreykrutskikh.lab3_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class CurrencyViewModel extends ViewModel {
    private MutableLiveData<List<CurrencyRate>> currencyRates = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<List<CurrencyRate>> getCurrencyRates() {
        return currencyRates;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void loadCurrencyRates() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.cbr.ru/") // Используйте HTTPS
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        CurrencyApi api = retrofit.create(CurrencyApi.class);
        Call<CurrencyResponse> call = api.getDailyRates(null); // null для получения последних данных

        call.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, Response<CurrencyResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CurrencyRate> rates = new ArrayList<>();
                    for (CurrencyResponse.Valute valute : response.body().getValutes()) {
                        rates.add(new CurrencyRate(
                                valute.getCharCode(),
                                valute.getName(),
                                Double.parseDouble(valute.getValue().replace(",", "."))
                        ));
                    }
                    currencyRates.postValue(rates);
                } else {
                    error.postValue("Failed to load data");
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable t) {
                error.postValue(t.getMessage());
            }
        });
    }
}