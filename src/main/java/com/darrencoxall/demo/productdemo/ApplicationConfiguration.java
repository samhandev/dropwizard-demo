package com.darrencoxall.demo.productdemo;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class ApplicationConfiguration extends Configuration {

    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDatabase() {
        return database;
    }

    public ApplicationConfiguration setDatabase(DataSourceFactory database) {
        this.database = database;
        return this;
    }

}
