package com.spy13.financemanager.models.services;

import com.spy13.financemanager.models.entities.Currency;

import java.util.List;

public interface ICurrencyService {
    List<Currency> get();
    Currency getById(int id);
}
