package com.spy13.financemanager.dao;

import com.spy13.financemanager.domain.entity.Purse;

import java.util.List;

public interface IPurseDao {
    List<Purse> get() throws DaoException;
    Purse getById(int id) throws DaoException;
    void update(Purse purse) throws DaoException;
    Purse create(Purse purse) throws DaoException;
}
