package com.spy13.financemanager.models.services;

import com.j256.ormlite.dao.Dao;
import com.spy13.financemanager.models.entities.Currency;
import com.spy13.financemanager.models.entities.Purse;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class PurseService implements IPurseService {
    private Dao<Purse, Integer> purseDao;

    @Inject
    public PurseService(Dao<Purse, Integer> purseDao) {
        this.purseDao = purseDao;
    }

    @Override
    public List<Purse> get() {
        try {
            return purseDao.queryForAll();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Purse getById(int id) {
        try {
            return purseDao.queryForId(id);
        } catch (SQLException e) {
            return null;
        }
    }
}
