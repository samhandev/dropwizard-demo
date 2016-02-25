package com.darrencoxall.demo.productdemo;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.lifecycle.Managed;

import java.util.Iterator;

public class DatabaseManager extends HealthCheck implements Managed {

    private static final String ERROR = "No products in database";

    private final ProductDAO dao;

    public DatabaseManager(ProductDAO dao) {
        this.dao = dao;
    }

    public void start() throws Exception {
        // Ensure we have the table ready
        dao.prepare();
    }

    public void stop() throws Exception {
        // Nothing to do here
    }

    @Override
    protected Result check() throws Exception {
        Iterator<Product> products = dao.findAllProducts();
        if (products.hasNext()) {
            return Result.healthy();
        }
        return Result.unhealthy(ERROR);
    }
}
