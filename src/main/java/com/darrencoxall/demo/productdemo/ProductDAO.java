package com.darrencoxall.demo.productdemo;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Iterator;

public interface ProductDAO {

    @SqlQuery("SELECT * FROM products WHERE id = :id")
    @RegisterMapper(ProductMapper.class)
    Product findProduct(@Bind("id") Long id);

    @SqlQuery("SELECT * FROM products")
    @RegisterMapper(ProductMapper.class)
    Iterator<Product> findAllProducts();

    @SqlUpdate(
            "INSERT INTO products (id, name, description, price, currencyCode, stock) " +
            "VALUES (:id, :name, :description, :price, :currencyCode, :stock)"
    )
    Integer insert(@BindBean Product product);

    @SqlUpdate("DELETE FROM products WHERE id = :id")
    Integer delete(@Bind("id") Long id);

    @SqlUpdate(
            "CREATE TABLE IF NOT EXISTS products (" +
                    "id bigint NOT NULL PRIMARY KEY, " +
                    "name varchar(255) NOT NULL, " +
                    "description text, " +
                    "price decimal(10,2) NOT NULL, " +
                    "currencyCode varchar(255) NOT NULL, " +
                    "stock int NOT NULL" +
            ");"
    )
    void prepare();
}
