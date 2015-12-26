package com.spy13.financemanager.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.spy13.financemanager.dao.ICurrencyDao;
import com.spy13.financemanager.domain.entity.Currency;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class CurrencyDao implements ICurrencyDao {
    private Dao<Currency, Integer> currencyDao;

    @Inject
    public CurrencyDao(Dao<Currency, Integer> currencyDao) {

        this.currencyDao = currencyDao;
    }

    @Override
    public List<Currency> get() {
        try {
            return currencyDao.queryForAll();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Currency getById(int id) {
        try {
            return currencyDao.queryForId(id);
        } catch (SQLException e) {
            return null;
        }
    }
}
