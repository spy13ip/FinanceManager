package com.spy13.financemanager;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Common {
    private static final String TAG = "FinanceManager";

    public static void log(Object obj, String msg){
        Log.d(TAG, obj.getClass().getSimpleName() + ":" + msg);
    }

    public static String inputStreamToString(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            total.append(line);
            total.append("\n");
        }
        return total.toString();
    }

}
