package com.spy13.financemanager.models.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "FinanceManager";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table currency (_id integer primary key autoincrement, code text, name text)");
        db.execSQL("insert into currency (code, name) values('EUR', 'Euro')");
        db.execSQL("insert into currency (code, name) values('RUB', 'Russian Ruble')");
        db.execSQL("create table purse (_id integer primary key autoincrement, currency_id integer, name text)");
        db.execSQL("insert into purse (currency_id, name) values (1, 'Cash')");
        db.execSQL("insert into purse (currency_id, name) values (2, 'Card')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
