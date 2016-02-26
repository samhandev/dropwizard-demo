package com.darrencoxall.demo.productdemo;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.Locale;

public class DemoApplication extends Application<ApplicationConfiguration> {

    private final static String APPLICATION_NAME = "product-demo";

    public static void main(String[] args) throws Exception {
        Application<? extends Configuration> application = new DemoApplication();
        if (args == null || args.length == 0) {
            args = new String[] { "server", ClassLoader.getSystemResource("./config.yml").getFile() };
        }
        application.run(args);
    }

    @Override
    public void initialize(final Bootstrap<ApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(new TemplateConfigBundle());
    }

    @Override
    public void run(ApplicationConfiguration applicationConfiguration, Environment environment) throws Exception {
        DBIFactory dbiFactory = new DBIFactory();
        DBI dbi = dbiFactory.build(environment, applicationConfiguration.getDatabase(), "productdata");

        final ProductDAO productDAO = dbi.onDemand(ProductDAO.class);
        final DatabaseManager databaseManager = new DatabaseManager(productDAO);

        environment.lifecycle().manage(databaseManager);
        environment.healthChecks().register("product_data", databaseManager);
        environment.jersey().register(new ProductResource(productDAO));
        environment.jersey().register(new CategoryResource());

        // I like my json to use underscores
        environment.getObjectMapper().setPropertyNamingStrategy(
                PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES
        );

        Locale.setDefault(Locale.UK);
    }

    @Override
    public String getName() {
        return APPLICATION_NAME;
    }

}
