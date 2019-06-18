package com.fyle.mappers;

import com.fyle.data.Banks;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BanksMapper implements RowMapper<Banks> {

    @Override
    public Banks map(ResultSet rs, StatementContext ctx) throws SQLException {
        System.out.println(rs.getString("name"));
        Banks bankPojo = new Banks.Builder()
                .withName(rs.getString("name"))
                .withId(rs.getLong("id"))
                .build();

        return bankPojo;
    }
}
