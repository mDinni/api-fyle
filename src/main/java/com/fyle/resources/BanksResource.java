package com.fyle.resources;

import com.fyle.dao.BanksDao;
import com.fyle.data.Banks;
import com.fyle.data.ComplexPrincipal;
import com.fyle.data.SimplePrincipal;
import io.dropwizard.auth.Auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/banks")
@Api("/banks")
public class BanksResource {
    private final BanksDao dao;

    public BanksResource(BanksDao dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Takes a parameter 'ifsc' and return the bank details",
            response = Banks.class
    )
    public Banks get(@Auth ComplexPrincipal user , @QueryParam("ifsc") String ifsc, @QueryParam("limit") int limit, @QueryParam("offset") int offset ) {
        if (offset < 0) offset = 0;
        if (limit < 1) limit = 1;
        return dao.read(ifsc, limit, offset);
    }


}
