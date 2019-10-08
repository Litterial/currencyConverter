package com.example.demo.Model;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class CurrencyModel {


    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
//    @DecimalMin(value = "0.01",message = "Please select a value greater than 0.")
//    @DecimalMax(value ="1000000.00",message = "Please select a value at or below 1 million.")
    private String money;

    @NotNull(message = "Cannot be null")
    private CurrencyEnum baseCurrency;

    @NotNull(message="Cannot be null")
    private CurrencyEnum exchangeCurrency;

    public CurrencyModel()
    {}

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
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
