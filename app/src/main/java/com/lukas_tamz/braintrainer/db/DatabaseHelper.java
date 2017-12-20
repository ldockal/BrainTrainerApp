package com.lukas_tamz.braintrainer.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ldockal on 12/17/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "scoreDB";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_SCORE_TABLE = "CREATE TABLE IF NOT EXISTS " + GameScoreContract.ScoreEntry.TABLE_NAME + " (" +
            GameScoreContract.ScoreEntry._ID + " INTEGER PRIMARY KEY," +
            GameScoreContract.ScoreEntry.COLUMN_NAME_GAME_ID + " INTEGER, " +
            GameScoreContract.ScoreEntry.COLUMN_NAME_LEVEL + " INTEGER)";

    private static final String SQL_DELETE_SCORE_ENTRY =
            "DROP TABLE IF EXISTS " + GameScoreContract.ScoreEntry.TABLE_NAME;

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    public void deleteAllTables(SQLiteDatabase database) {
        database.execSQL(SQL_DELETE_SCORE_ENTRY);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteAllTables(db);
        onCreate(db);
    }
}
