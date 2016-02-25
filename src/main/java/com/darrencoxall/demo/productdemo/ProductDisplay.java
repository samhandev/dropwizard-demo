package com.darrencoxall.demo.productdemo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class ProductDisplay {

    private Product product;

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPrice() {
        Currency currency = Currency.getInstance(product.getCurrencyCode());
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setCurrency(currency);
        return format.format(product.getPrice());
    }

    public Boolean getInStock() {
        return product.getStock() > 0;
    }
}
