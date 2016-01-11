package com.spy13.financemanager.dao.impl;

import com.spy13.financemanager.dao.DaoException;
import com.spy13.financemanager.dao.IPurseDao;
import com.spy13.financemanager.dao.ormlite.PurseOrmliteDao;
import com.spy13.financemanager.domain.entity.Purse;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class PurseDao implements IPurseDao {
    private PurseOrmliteDao purseDao;

    @Inject
    public PurseDao(PurseOrmliteDao purseDao) {
        this.purseDao = purseDao;
    }

    @Override
    public List<Purse> get() throws DaoException {
        try {
            return purseDao.queryForAll();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Purse getById(int id) throws DaoException {
        try {
            return purseDao.queryForId(id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Purse purse) throws DaoException {
        try {
            if (purseDao.update(purse) != 1)
                throw DaoException.rowNumberIsNot1();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    @Override
    public Purse create(Purse purse) throws DaoException {
        try {
            if (purseDao.create(purse) != 1)
                throw DaoException.rowNumberIsNot1();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return purse;
    }
}
