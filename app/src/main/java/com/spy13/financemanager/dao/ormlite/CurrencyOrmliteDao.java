package com.spy13.financemanager.dao.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.spy13.financemanager.domain.entity.Currency;

import java.sql.SQLException;

public class CurrencyOrmliteDao extends BaseDaoImpl<Currency, Integer> {
    public CurrencyOrmliteDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Currency.class);
    }
}
