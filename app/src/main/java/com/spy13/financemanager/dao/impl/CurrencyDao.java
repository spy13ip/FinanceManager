package com.spy13.financemanager.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.spy13.financemanager.dao.DaoException;
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
    public List<Currency> get() throws DaoException {
        try {
            return currencyDao.queryForAll();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Currency getById(int id) throws DaoException {
        try {
            return currencyDao.queryForId(id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Currency currency) throws DaoException {
        try {
            if (currencyDao.update(currency) != 1)
                throw DaoException.rowNumberIsNot1();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Currency create(Currency currency) throws DaoException {
        try {
            if (currencyDao.create(currency) != 1)
                throw DaoException.rowNumberIsNot1();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return currency;
    }
}
