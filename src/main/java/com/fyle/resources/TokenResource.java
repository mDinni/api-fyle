package com.fyle.resources;


import com.fyle.data.SimplePrincipal;
import com.google.common.base.Throwables;
import io.dropwizard.auth.Auth;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.jose4j.jws.AlgorithmIdentifiers.HMAC_SHA256;

@Path("/fyle")
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {

    private final byte[] TOKEN_SECRET;

    public TokenResource(byte[] tokenSecret)
    {
        this.TOKEN_SECRET = tokenSecret;
    }

    @GET
    @Path("/getToken")
    public Map<String, String> getToken(@Auth SimplePrincipal principal) {
        final JwtClaims claims = new JwtClaims();
        claims.setSubject("no-subject");
        claims.setExpirationTimeMinutesInTheFuture(7200);

        final JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setAlgorithmHeaderValue(HMAC_SHA256);
        jws.setKey(new HmacKey(TOKEN_SECRET));

        try {
            return singletonMap("token", jws.getCompactSerialization());
        }

        catch (JoseException e) { throw Throwables.propagate(e); }
    }
}
