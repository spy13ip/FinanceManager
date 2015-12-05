package com.spy13.financemanager.views.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.R;
import com.spy13.financemanager.views.fragment.PurseFragment;

public class PurseActivity extends InjectionActivityBase {
    private int purseId;

    public static Intent createIntent(Intent intent, int purseId){
        intent.putExtra("purseId", purseId);
        return intent;
    }

    private void initArguments() {
        purseId = getIntent().getIntExtra("purseId", 0);
    }

    public PurseActivity() {
        Common.log(this, "PurseActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Common.log(this, "onCreate");
        super.onCreate(savedInstanceState);
        initArguments();
        setContentView(R.layout.purse_activity);
        if (savedInstanceState == null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.purseActivity_Purse, PurseFragment.createInstance(purseId));
            transaction.commit();
        }
    }

}
