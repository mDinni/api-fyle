package com.fyle.auth;

import com.fyle.data.SimplePrincipal;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Optional;

public class MyAuthenticator implements Authenticator<BasicCredentials, SimplePrincipal> {

    private final String apiValidUser;
    private final String apiPassword;

    public MyAuthenticator(String user, String password) {
        this.apiPassword = password;
        this.apiValidUser = user;
    }

    @Override
    public Optional<SimplePrincipal> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {

        if (apiPassword.equals(basicCredentials.getPassword()) && apiValidUser.equals(basicCredentials.getUsername()))
            return Optional.of(new SimplePrincipal(basicCredentials.getUsername()));
        else
            throw new AuthenticationException("It's not your fault");
    }
}
