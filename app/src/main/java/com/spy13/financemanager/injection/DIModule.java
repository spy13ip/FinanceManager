package com.spy13.financemanager.injection;

import android.app.Application;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.spy13.financemanager.dao.helper.DBHelper;
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
    public Dao<Currency, Integer> provideOrmCurrencyDao (ConnectionSource connectionSource) {
        try {
            return DaoManager.createDao(connectionSource, Currency.class);
        } catch (SQLException e) {
            return null;
        }
    }

    @Provides
    @Singleton
    public Dao<Purse, Integer> provideOrmPurseDao (ConnectionSource connectionSource) {
        try {
            return DaoManager.createDao(connectionSource, Purse.class);
        } catch (SQLException e) {
            return null;
        }
    }

    @Provides
    @Singleton
    public ICurrencyDao provideCurrencyDao(Dao<Currency, Integer> currencyDao) {
        return new CurrencyDao(currencyDao);
    }

    @Provides
    @Singleton
    public IPurseDao providePurseDao(Dao<Purse, Integer> purseDao) {
        return new PurseDao(purseDao);
    }
}
