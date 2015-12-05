package com.spy13.financemanager;

import android.app.Application;

import com.spy13.financemanager.di.component.DIComponent;
import com.spy13.financemanager.di.component.DaggerDIComponent;
import com.spy13.financemanager.di.module.DIModule;

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
