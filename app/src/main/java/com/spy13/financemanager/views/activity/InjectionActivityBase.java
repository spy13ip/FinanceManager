package com.spy13.financemanager.views.activity;

import android.app.Activity;

import com.spy13.financemanager.InjectorProvider;
import com.spy13.financemanager.di.component.DIComponent;

public abstract class InjectionActivityBase extends Activity implements InjectorProvider {

    @Override
    public DIComponent getInjector() {
        return ((InjectorProvider)getApplication()).getInjector();
    }
}
