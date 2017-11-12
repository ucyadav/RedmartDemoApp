package com.umeshcydv.redmartdemoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by umeshcydv on 12/11/17.
 */

public class PricingInfo {

    @SerializedName("on_sale")
    private Number onSale;

    private Number price;

    @SerializedName("promo_price")
    private Number promoPrice;

    private Number savings;

    @SerializedName("savings_amount")
    private Number savingAmmount;

    @SerializedName("savings_text")
    private String savintText;


    public Number getOnSale() {
        return onSale;
    }

    public void setOnSale(Number onSale) {
        this.onSale = onSale;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Number promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Number getSavings() {
        return savings;
    }

    public void setSavings(Number savings) {
        this.savings = savings;
    }

    public Number getSavingAmmount() {
        return savingAmmount;
    }

    public void setSavingAmmount(Number savingAmmount) {
        this.savingAmmount = savingAmmount;
    }

    public String getSavintText() {
        return savintText;
    }

    public void setSavintText(String savintText) {
        this.savintText = savintText;
    }
}
