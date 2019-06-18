package com.fyle;

import com.fyle.dao.BanksDao;
import com.fyle.dao.BranchesDao;
import com.fyle.resources.BanksResource;
import com.fyle.resources.BranchesResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
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
        bootstrap.addBundle(new SwaggerBundle<FyleApiConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(FyleApiConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final FyleApiConfiguration configuration,
                    final Environment environment) {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final BanksDao banksDao = jdbi.onDemand(BanksDao.class);
        final BranchesDao branchesDao = jdbi.onDemand(BranchesDao.class);

        final BanksResource banksResource = new BanksResource(banksDao);
        final BranchesResource branchesResource = new BranchesResource(branchesDao);

        environment.jersey().register(banksResource);
        environment.jersey().register(branchesResource);
    }

}
