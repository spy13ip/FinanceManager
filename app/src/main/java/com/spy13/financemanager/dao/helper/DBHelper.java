package com.spy13.financemanager.dao.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.RawRes;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.R;

import java.io.IOException;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "FinanceManager";
    private static final int VERSION = 1;

    private final Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            for (String item : sql(R.raw.create))
                db.execSQL(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String[] sql(@RawRes int id) {
        try {
            return Common.inputStreamToString(context.getResources().openRawResource(id)).split(";\n+?");
        } catch (IOException e) {
            return null;
        }
    }
}
