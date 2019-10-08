package com.example.demo.Model;

public enum CurrencyEnum {
    AUD("Australian dollar"),
    BGN("Bulgarian lev"),
    BRL	("Brazilian real"),
    CAD	("Canadian dollar"),
    CHF	("Swiss franc"),
    CNY	("Chinese yuan renminbi"),
    CZK	("Czech koruna"),
    DKK	("Danish krone"),
    GBP	("Pound sterling"),
    HKD	("Hong Kong dollar"),
    HRK	("Croatian kuna"),
    HUF	("Hungarian forint"),
    IDR	("Indonesian rupiah"),
    ILS	("Israeli shekel"),
    INR	("Indian rupee"),
    ISK	("Icelandic krona"),
    JPY	("Japanese yen"),
    KRW	("South Korean won"),
    MXN	("Mexican peso"),
    MYR	("Malaysian ringgit"),
    NOK	("Norwegian krone"),
    NZD	("New Zealand dollar"),
    PHP	("Philippine peso"),
    PLN	("Polish zloty"),
    RON	("Romanian leu"),
    RUB	("Russian rouble"),
    SEK	("Swedish krona"),
    SGD	("Singapore dollar"),
    THB	("Thai baht"),
    TRY	("Turkish lira"),
    USD	("US dollar"),
    ZAR	("South African rand");

    private String fullName;

    CurrencyEnum(String fullName){this.fullName=fullName;}
    public String getFullName() { return fullName; }

}
