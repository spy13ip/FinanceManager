package com.spy13.financemanager.injection;

import android.app.Application;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.spy13.financemanager.dao.helper.DBHelper;
import com.spy13.financemanager.dao.ormlite.CurrencyOrmliteDao;
import com.spy13.financemanager.dao.ormlite.PurseOrmliteDao;
import com.spy13.financemanager.domain.entity.Currency;
import com.spy13.financemanager.domain.entity.Purse;
import com.spy13.financemanager.dao.impl.CurrencyDao;
import com.spy13.financemanager.dao.ICurrencyDao;
import com.spy13.financemanager.dao.IPurseDao;
import com.spy13.financemanager.dao.impl.PurseDao;

import java.sql.SQLException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DIModule {
    private final Application app;

    public DIModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public DBHelper provideDBHelper() {
        return new DBHelper(app);
    }

    @Provides
    @Singleton
    public ConnectionSource provideConnectionSource (DBHelper dbHelper) {
        return new AndroidConnectionSource(dbHelper);
    }

    @Provides
    @Singleton
    public CurrencyOrmliteDao provideCurrencyOrmliteDao (ConnectionSource connectionSource) {
        try {
            return new CurrencyOrmliteDao(connectionSource);
        } catch (SQLException e) {
            return null;
        }
    }

    @Provides
    @Singleton
    public PurseOrmliteDao providePurseDaoInternal (ConnectionSource connectionSource) {
        try {
            return new PurseOrmliteDao(connectionSource);
        } catch (SQLException e) {
            return null;
        }
    }

    @Provides
    @Singleton
    public ICurrencyDao provideCurrencyDao(CurrencyOrmliteDao currencyDao) {
        return new CurrencyDao(currencyDao);
    }

    @Provides
    @Singleton
    public IPurseDao providePurseDao(PurseOrmliteDao purseDao) {
        return new PurseDao(purseDao);
    }
}
