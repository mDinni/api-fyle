package com.fyle;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.UnsupportedEncodingException;

public class FyleApiConfiguration extends Configuration {
    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;

    @JsonProperty("jwtToken")
    private String jwtToken;

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtToken.getBytes("UTF-8");
    }

    @JsonProperty("jwtSubject")
    private String jwtSubject;

    public String getSubject() { return jwtSubject; }

    @JsonProperty("apiUser")
    private String apiValidUser;

    public String getApiValidUser() { return apiValidUser; }

    @JsonProperty("apiPassoword")
    private String apiPassword;

    public String getApiPassword() { return apiPassword; }
}
