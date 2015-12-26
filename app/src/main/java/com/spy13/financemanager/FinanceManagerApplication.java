package com.spy13.financemanager;

import android.app.Application;

import com.spy13.financemanager.injection.DIComponent;
import com.spy13.financemanager.injection.DaggerDIComponent;
import com.spy13.financemanager.injection.InjectorProvider;
import com.spy13.financemanager.injection.DIModule;

public class FinanceManagerApplication extends Application implements InjectorProvider {
    private DIComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerDIComponent.builder().dIModule(new DIModule(this)).build();
    }

    @Override
    public DIComponent getInjector() {
        return injector;
    }
}
