package com.spy13.financemanager.di.module;

import android.app.Application;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.spy13.financemanager.models.data.DBHelper;
import com.spy13.financemanager.models.entities.Currency;
import com.spy13.financemanager.models.entities.Purse;
import com.spy13.financemanager.models.services.CurrencyService;
import com.spy13.financemanager.models.services.ICurrencyService;
import com.spy13.financemanager.models.services.IPurseService;
import com.spy13.financemanager.models.services.PurseService;

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
    public Dao<Currency, Integer> provideCurrencyDao (ConnectionSource connectionSource) {
        try {
            return DaoManager.createDao(connectionSource, Currency.class);
        } catch (SQLException e) {
            return null;
        }
    }

    @Provides
    @Singleton
    public Dao<Purse, Integer> providePurseDao (ConnectionSource connectionSource) {
        try {
            return DaoManager.createDao(connectionSource, Purse.class);
        } catch (SQLException e) {
            return null;
        }
    }

    @Provides
    @Singleton
    public ICurrencyService provideCurrencyService(Dao<Currency, Integer> currencyDao) {
        return new CurrencyService(currencyDao);
    }

    @Provides
    @Singleton
    public IPurseService providePurseService(Dao<Purse, Integer> purseDao) {
        return new PurseService(purseDao);
    }
}
