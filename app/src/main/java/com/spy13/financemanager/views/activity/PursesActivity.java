package com.spy13.financemanager.views.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.R;
import com.spy13.financemanager.models.entities.Purse;
import com.spy13.financemanager.views.ShowPurseListener;
import com.spy13.financemanager.views.fragment.PurseListFragment;

public class PursesActivity extends InjectionActivityBase implements ShowPurseListener {

    public PursesActivity() {
        Common.log(this, "PursesActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Common.log(this, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purses_activity);
        PurseListFragment purseListFragment;
        if (savedInstanceState == null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.pursesActivity_purseList, purseListFragment = PurseListFragment.createInstance());
            transaction.commit();
        }
        else {
            purseListFragment = (PurseListFragment)getFragmentManager().findFragmentById(R.id.purseListFragment);
        }
        purseListFragment.setShowPurseListener(this);
    }

    @Override
    public void showPurse(Purse purse) {
        Common.log(this, "showPurse");
        Intent intent = new Intent(this, PurseActivity.class);
        PurseActivity.createIntent(intent, purse.getId());
        startActivity(intent);
    }
}
