package ru.andreykrutskikh.lab3_app;

public class CurrencyRate {
    private String currencyCode; // Код валюты (например, USD)
    private String currencyName; // Название валюты (например, Доллар США)
    private double rate;         // Курс валюты

    public CurrencyRate(String currencyCode, String currencyName, double rate) {
        this.currencyCode = currencyCode;
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public double getRate() {
        return rate;
    }
}