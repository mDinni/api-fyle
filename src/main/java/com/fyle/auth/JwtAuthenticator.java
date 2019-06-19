package com.fyle.auth;

import com.fyle.data.SimplePrincipal;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.JwtContext;

import java.util.Optional;

public class JwtAuthenticator implements Authenticator<JwtContext, SimplePrincipal> {

    private final String jwtSecretSubject;

    public JwtAuthenticator(String jwtSubject) {
        this.jwtSecretSubject = jwtSubject;
    }

    @Override
    public Optional<SimplePrincipal> authenticate(JwtContext jwtContext) throws AuthenticationException {

        try {
            final String subject = jwtContext.getJwtClaims().getSubject();
            if (jwtSecretSubject.equals(subject))
                return Optional.of(new SimplePrincipal("ADMIN"));
        } catch (Exception e) {
            throws AuthenticationException ;
        }

        return Optional.empty();
    }
}
