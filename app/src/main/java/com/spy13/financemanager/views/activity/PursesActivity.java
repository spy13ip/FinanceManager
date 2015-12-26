package com.spy13.financemanager.views.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.R;
import com.spy13.financemanager.domain.entity.Purse;
import com.spy13.financemanager.views.ShowPurseListener;
import com.spy13.financemanager.views.fragment.PurseListFragment;

public class PursesActivity extends InjectionActivityBase implements ShowPurseListener {
    //Fragments
    private static final String PURSE_LIST_FRAGMENT_TAG = "PurseListFragment";
    private PurseListFragment purseListFragment;

    public PursesActivity() {
        Common.log(this, "PursesActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Common.log(this, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purses_activity);
        if (savedInstanceState == null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.pursesActivity_purseList, PurseListFragment.createInstance(), PURSE_LIST_FRAGMENT_TAG);
            transaction.commit();
        }
        getFragmentManager().executePendingTransactions();
        purseListFragment = (PurseListFragment)getFragmentManager().findFragmentByTag(PURSE_LIST_FRAGMENT_TAG);
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
