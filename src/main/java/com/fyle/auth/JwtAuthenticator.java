package com.fyle.auth;

import com.fyle.data.ComplexPrincipal;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import org.jose4j.jwt.consumer.JwtContext;

import java.util.Optional;

public class JwtAuthenticator implements Authenticator<JwtContext, ComplexPrincipal> {

    private final String jwtSecretSubject;

    public JwtAuthenticator(String jwtSubject) {
        this.jwtSecretSubject = jwtSubject;
    }

    @Override
    public Optional<ComplexPrincipal> authenticate(JwtContext jwtContext) {

        try {
            final String subject = jwtContext.getJwtClaims().getSubject();
            if (jwtSecretSubject.equals(subject))
            {
                return Optional.of(new ComplexPrincipal("ADMIN"));
            }
        } catch (Exception e) {
            System.out.println("Error Occured: " + e.getMessage());
        }

        return Optional.empty();
    }
}
