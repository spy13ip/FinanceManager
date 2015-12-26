package com.spy13.financemanager.dao;

import com.spy13.financemanager.domain.entity.Currency;

import java.util.List;

public interface ICurrencyDao {
    List<Currency> get();
    Currency getById(int id);
}
