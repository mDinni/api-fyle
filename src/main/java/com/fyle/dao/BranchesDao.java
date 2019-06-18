package com.fyle.dao;

import com.fyle.data.Branches;
import com.fyle.mappers.BranchesMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterRowMapper(BranchesMapper.class)
public interface BranchesDao {
    // READ an Event
    @SqlQuery("SELECT * FROM branches WHERE bank_id=(SELECT id FROM banks WHERE name=:name) and city=:city ORDER BY ifsc LIMIT :limit OFFSET :offset")
    public List<Branches> read(@Bind("name") String bankName, @Bind("city") String city, @Bind("limit") int limit, @Bind("offset") int offset );
}
