package com.spy13.financemanager.dao.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.spy13.financemanager.domain.entity.Purse;

import java.sql.SQLException;

public class PurseOrmliteDao extends BaseDaoImpl<Purse, Integer> {
    public PurseOrmliteDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Purse.class);
    }
}
