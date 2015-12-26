package com.spy13.financemanager.dao;

import com.spy13.financemanager.domain.entity.Currency;

import java.util.List;

public interface ICurrencyDao {
    List<Currency> get() throws DaoException;
    Currency getById(int id) throws DaoException;
    void update(Currency currency) throws DaoException;
    Currency create(Currency currency) throws DaoException;
}
