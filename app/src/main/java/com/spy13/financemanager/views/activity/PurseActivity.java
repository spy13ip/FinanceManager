package com.spy13.financemanager.views.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.R;
import com.spy13.financemanager.views.fragment.PurseFragment;

public class PurseActivity extends Activity {
    private int purseId;

    public static Intent args(Intent intent, int purseId){
        intent.putExtra("purseId", purseId);
        return intent;
    }

    private void init() {
        purseId = getIntent().getIntExtra("purseId", 0);
    }

    public PurseActivity() {
        Common.log(this, "PurseActivity");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Common.log(this, "onCreate");
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.purse_activity);
        if (savedInstanceState == null) {
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.purseActivity_Purse, PurseFragment.newInstance(purseId));
            transaction.commit();
        }
    }
}
