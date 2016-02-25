package com.darrencoxall.demo.productdemo;

import com.google.common.collect.Lists;
import io.dropwizard.jersey.params.LongParam;
import org.skife.jdbi.cglib.core.Local;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.Locale;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductDAO productData;

    public ProductResource(ProductDAO productData) {
        this.productData = productData;
    }

    @GET
    @Path("{id}")
    public Product getProduct(@PathParam("id") LongParam id) {
        return productData.findProduct(id.get());
    }

    @GET
    public List<Product> getProducts() {
        return Lists.newArrayList(productData.findAllProducts());
    }

    @PUT
    @Path("{id}")
    public Response createProduct(Product product, @PathParam("id") LongParam id) {
        product.setId(id.get());
        if (productData.insert(product) > 0) {
            return Response.noContent()
                    .location(UriBuilder.fromPath("/products/" + product.getId()).build())
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    public Response removeProduct(@PathParam("id") LongParam id) {
        if (productData.delete(id.get()) > 0) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
