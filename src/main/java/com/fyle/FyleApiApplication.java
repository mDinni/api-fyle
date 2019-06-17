package com.fyle;

import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;

public class FyleApiApplication extends Application<FyleApiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FyleApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "FyleApi";
    }

    @Override
    public void initialize(final Bootstrap<FyleApiConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FyleApiConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
    }

}
