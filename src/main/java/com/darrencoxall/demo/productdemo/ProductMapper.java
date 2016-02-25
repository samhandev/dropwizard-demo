package com.darrencoxall.demo.productdemo;

import org.skife.jdbi.v2.BeanMapper;

public class ProductMapper extends BeanMapper<Product> {
    public ProductMapper() {
        super(Product.class);
    }
}
