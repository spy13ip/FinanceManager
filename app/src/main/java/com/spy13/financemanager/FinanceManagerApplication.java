package com.spy13.financemanager;

import android.app.Application;

import com.spy13.financemanager.di.component.DIComponent;
import com.spy13.financemanager.di.component.DaggerDIComponent;
import com.spy13.financemanager.di.module.DIModule;

public class FinanceManagerApplication extends Application {
    private DIComponent diComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        diComponent = DaggerDIComponent.builder().dIModule(new DIModule(this)).build();
    }

    public DIComponent getDiComponent() {
        return diComponent;
    }
}
