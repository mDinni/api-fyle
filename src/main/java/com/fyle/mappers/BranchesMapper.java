package com.fyle.mappers;

import com.fyle.data.Branches;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BranchesMapper implements RowMapper<Branches> {
    @Override
    public Branches map(ResultSet rs, StatementContext ctx) throws SQLException {
        Branches branchPojo = new Branches.Builder()
                .withIfsc(rs.getString("ifsc"))
                .withBank_id(rs.getLong("bank_id"))
                .withBranch(rs.getString("branch"))
                .withAddress(rs.getString("address"))
                .withCity(rs.getString("city"))
                .withDistrict(rs.getString("district"))
                .withState(rs.getString("state"))
                .build();

        return branchPojo;
    }
}
