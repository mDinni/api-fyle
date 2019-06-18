package com.fyle.dao;

import com.fyle.data.Banks;
import com.fyle.mappers.BanksMapper;
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@RegisterRowMapper(BanksMapper.class)
public interface BanksDao {

    // READ an Event
    @SqlQuery("SELECT * FROM banks WHERE id=(SELECT bank_id FROM branches WHERE ifsc=:ifsc) ORDER BY id LIMIT :limit OFFSET :offset")
    public Banks read(@Bind("ifsc") String ifsc, @Bind("limit") int limit, @Bind("offset") int offset);

}
