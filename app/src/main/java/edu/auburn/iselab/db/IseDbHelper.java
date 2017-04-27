package edu.auburn.iselab.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: Gary
 * Time: 4/27/17
 */

public class IseDbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ise.db";
    public static final String TABLE_NAME = "task";

    public IseDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        private double lever;
    private double load;
    private double moment;
    private double multiplier;
    private int repetitions;
    private double damage;
    private double cumLoad;
    private long date;
         */
        String sql = "create table if not exists " + TABLE_NAME +
                " (id integer primary key autoincrement, " +
                "lever double, " +
                "load double, " +
                "moment double, " +
                "multiplier double, " +
                "repetitions integer, " +
                "damage double, " +
                "cum double, " +
                "date varchar(20) " +
                ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
