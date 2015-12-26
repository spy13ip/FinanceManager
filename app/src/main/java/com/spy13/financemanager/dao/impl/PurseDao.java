package com.spy13.financemanager.dao.impl;

import com.j256.ormlite.dao.Dao;
import com.spy13.financemanager.dao.IPurseDao;
import com.spy13.financemanager.domain.entity.Purse;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

public class PurseDao implements IPurseDao {
    private Dao<Purse, Integer> purseDao;

    @Inject
    public PurseDao(Dao<Purse, Integer> purseDao) {
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
