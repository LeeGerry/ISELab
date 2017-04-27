package edu.auburn.iselab;

import android.app.Application;

import edu.auburn.iselab.db.IseDbHelper;

/**
 * Author: Gary
 * Time: 4/27/17
 */

public class IseApplication extends Application{
    IseDbHelper dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        initDb();
    }

    private void initDb() {
        dbHelper = new IseDbHelper(this);
    }
}
