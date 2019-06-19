package com.fyle;

import com.fyle.auth.JwtAuthenticator;
import com.fyle.auth.MyAuthenticator;
import com.fyle.dao.BanksDao;
import com.fyle.dao.BranchesDao;
import com.fyle.data.ComplexPrincipal;
import com.fyle.data.SimplePrincipal;
import com.fyle.resources.BanksResource;
import com.fyle.resources.BranchesResource;
import com.fyle.resources.TokenResource;
import com.github.toastshaman.dropwizard.auth.jwt.JwtAuthFilter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.PolymorphicAuthDynamicFeature;
import io.dropwizard.auth.PolymorphicAuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.jdbi.v3.core.Jdbi;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.jwt.consumer.JwtContext;
import org.jose4j.keys.HmacKey;

import java.io.UnsupportedEncodingException;

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
    public void run(final FyleApiConfiguration configuration, final Environment environment)
            throws UnsupportedEncodingException {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
        final BanksDao banksDao = jdbi.onDemand(BanksDao.class);
        final BranchesDao branchesDao = jdbi.onDemand(BranchesDao.class);

        final BanksResource banksResource = new BanksResource(banksDao);
        final BranchesResource branchesResource = new BranchesResource(branchesDao);
        final TokenResource tokenResource = new TokenResource(configuration.getJwtTokenSecret());

        environment.jersey().register(banksResource);
        environment.jersey().register(branchesResource);
        environment.jersey().register(tokenResource);

        // Basic auth for token generation
        final AuthFilter<BasicCredentials, SimplePrincipal> basicFilter = new BasicCredentialAuthFilter.Builder<SimplePrincipal>()
                .setAuthenticator(new MyAuthenticator(configuration.getApiValidUser(), configuration.getApiPassword()))
                .setRealm("HelloWorldAgain").buildAuthFilter();

        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(SimplePrincipal.class));

        // JWT auth
        final byte[] key = configuration.getJwtTokenSecret();

        final JwtConsumer consumer = new JwtConsumerBuilder().setAllowedClockSkewInSeconds(30)
                .setRequireExpirationTime().setRequireSubject().setVerificationKey(new HmacKey(key))
                .setRelaxVerificationKeyValidation().build();

        final AuthFilter<JwtContext, ComplexPrincipal> jwtTokenFilter = new JwtAuthFilter.Builder<ComplexPrincipal>()
                .setJwtConsumer(consumer).setRealm("realm").setPrefix("Bearer")
                .setAuthenticator(new JwtAuthenticator(configuration.getSubject())).buildAuthFilter();

        final PolymorphicAuthDynamicFeature feature = new PolymorphicAuthDynamicFeature<>(
                ImmutableMap.of(SimplePrincipal.class, basicFilter, ComplexPrincipal.class, jwtTokenFilter));

        final AbstractBinder binder = new PolymorphicAuthValueFactoryProvider.Binder<>(
                ImmutableSet.of(SimplePrincipal.class, ComplexPrincipal.class));

        environment.jersey().register(feature);
        environment.jersey().register(binder);

    }

}
