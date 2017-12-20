package com.lukas_tamz.braintrainer.db.dao;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.lukas_tamz.braintrainer.db.DatabaseHelper;

/**
 * Created by ldockal on 12/17/2017.
 */

public abstract class BaseDao {

    protected SQLiteDatabase database;
    private Context context;
    private DatabaseHelper databaseHelper;




    public BaseDao(Context context) {
        this.context = context;
        this.databaseHelper = DatabaseHelper.getHelper(context);
        open();
    }

    public void open() throws SQLException {
        if (databaseHelper == null)
            databaseHelper = DatabaseHelper.getHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public void deleteAllTables() {
        databaseHelper.deleteAllTables(database);
    }
}
