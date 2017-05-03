package edu.auburn.iselab.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.iselab.model.Task;

import static edu.auburn.iselab.db.IseDbHelper.TABLE_NAME;

/**
 * Author: Gary
 * Time: 4/27/17
 */

public class TaskDao {
    private Context ctx;
    private IseDbHelper dbHelper;
    public TaskDao(Context ctx) {
        this.ctx = ctx;
        dbHelper = new IseDbHelper(ctx);
    }
//    String sql = "create table if not exists " + TABLE_NAME +
//            " (id integer primary key autoincrement, " +
//            "lever double, " +
//            "load double, " +
//            "moment double, " +
//            "multiplier double, " +
//            "repetitions integer, " +
//            "damage double, " +
//            "cum double, " +
//            "date timestamp, " +
//            ")";
    public void addTask(Task task){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lever", task.getLever());
        contentValues.put("load", task.getLoad());
        contentValues.put("moment", task.getMoment());
        contentValues.put("multiplier", task.getMultiplier());
        contentValues.put("repetitions", task.getRepetitions());
        contentValues.put("damage", task.getDamage());
        contentValues.put("cum", task.getCumLoad());
        contentValues.put("date", task.getDate()+"");
        db.insertOrThrow(TABLE_NAME, null, contentValues);
//        db.execSQL("INSERT INTO "+TABLE_NAME+ " (lever, load, moment, multiplier, repetitions, damage, cum, date) VALUES('ravitamada', 'datetime()'");
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        Log.i("task", task.toString());
        Toast.makeText(ctx, "success"+ "\n"+task.toString(), Toast.LENGTH_SHORT).show();
    }
    public List<Task> getTasks(){
        ArrayList<Task> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        Task t ;
        while (cursor.moveToNext()){
            t = new Task();
            t.setId(cursor.getInt(cursor.getColumnIndex("id")));
            t.setLever(cursor.getDouble(cursor.getColumnIndex("lever")));
            t.setLoad(cursor.getDouble(cursor.getColumnIndex("load")));
            t.setMoment(cursor.getDouble(cursor.getColumnIndex("moment")));
            t.setMultiplier(cursor.getDouble(cursor.getColumnIndex("multiplier")));
            t.setRepetitions(cursor.getInt(cursor.getColumnIndex("repetitions")));
            t.setDamage(cursor.getDouble(cursor.getColumnIndex("damage")));
            t.setCumLoad(cursor.getDouble(cursor.getColumnIndex("cum")));
            t.setDate(Long.parseLong(cursor.getString(cursor.getColumnIndex("date"))));
            list.add(t);
        }
        db.close();
        return list;
    }

    public void delete(int tid) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "delete from "+TABLE_NAME+ " where id = ?";
        Object[] objs = new Object[] { tid };
        db.execSQL(sql, objs);
        db.close();
    }

    public double calculateTotalTrauma(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select damage from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(sql, null);
        double total = 0;
        while (cursor.moveToNext()){
            total += cursor.getDouble(cursor.getColumnIndex("damage"));
        }
        db.close();
        return total;
    }
}


