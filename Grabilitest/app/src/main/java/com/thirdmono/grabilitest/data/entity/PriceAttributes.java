package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class PriceAttributes {

    @SerializedName("amount")
    private String amount;
    @SerializedName("currency")
    private String currency;

    public PriceAttributes() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
