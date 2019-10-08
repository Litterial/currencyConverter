package com.example.demo.Model;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class CurrencyModel {


    @NotNull
    @DecimalMin("0.01")
    @DecimalMax(value ="1000000.00",message = "Please select a value at or below 1 million.")
    private double money;

    @NotNull
    @NotEmpty(message = "Please select a currency.")
    private CurrencyEnum baseCurrency;
    @NotNull
    @NotEmpty(message = "Please select a currency")
    private CurrencyEnum exchangeCurrency;

    public CurrencyModel()
    {}

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public CurrencyEnum getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(CurrencyEnum baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public CurrencyEnum getExchangeCurrency() {
        return exchangeCurrency;
    }

    public void setExchangeCurrency(CurrencyEnum exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }








}
