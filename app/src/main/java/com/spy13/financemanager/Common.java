package com.spy13.financemanager;

import android.util.Log;

public class Common {
    private static final String TAG = "FinanceManager";

    public static void log(Object obj, String msg){
        Log.d(TAG, obj.getClass().getSimpleName() + ":" + msg);
    }
}
