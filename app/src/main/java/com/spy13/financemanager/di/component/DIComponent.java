package com.spy13.financemanager.di.component;

import com.spy13.financemanager.di.module.DIModule;
import com.spy13.financemanager.views.fragment.PurseFragment;
import com.spy13.financemanager.views.fragment.PurseListFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = DIModule.class)
public interface DIComponent {
    void inject(PurseListFragment fragment);
    void inject(PurseFragment fragment);
}
