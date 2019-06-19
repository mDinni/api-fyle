package com.fyle.resources;

import com.fyle.dao.BranchesDao;
import com.fyle.data.Branches;
import com.fyle.data.ComplexPrincipal;

import io.dropwizard.auth.Auth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/branches")
@Api("/branches")
@PermitAll
public class BranchesResource {
    private final BranchesDao dao;

    public BranchesResource(BranchesDao dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Takes two parameters 'bank_name' and 'city', returns branches details",
            response = Branches.class
    )

    public List<Branches> get(@Auth ComplexPrincipal user, @QueryParam("bank_name") String bankName, @QueryParam("city") String city, @QueryParam("limit") int limit, @QueryParam("offset") int offset) {
        if (offset < 0) offset = 0;
        if (limit == 0) limit = 2;

        bankName = bankName.replace("%20", " ");
        return dao.read(bankName, city, limit, offset);
    }
}
