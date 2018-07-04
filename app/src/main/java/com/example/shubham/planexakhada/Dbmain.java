package com.example.shubham.planexakhada;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by shubham on 12/8/2016.
 */

public class Dbmain extends SQLiteOpenHelper {

    private Context ct;
    private static String DB_NAME = "schedule";
    private static int DB_VERSION = 1;


    public Dbmain(Context ct) {
        super(ct, DB_NAME, null, DB_VERSION);
        this.ct = ct;
        Log.d("CHECK", "in constructor of DBMAIN, super called");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("CHECK", "onCreate: table created1");
        String mon = "Create table monday(name TEXT PRIMARY KEY, sets TEXT,reps TEXT, weights TEXT)";
        String tue = "Create table tuesday(name TEXT PRIMARY KEY, sets TEXT,reps TEXT, weights TEXT)";
        String wed = "Create table wednesday(name TEXT PRIMARY KEY, sets TEXT,reps TEXT, weights TEXT)";
        String thu = "Create table thursday(name TEXT PRIMARY KEY, sets TEXT,reps TEXT, weights TEXT)";
        String fri = "Create table friday(name TEXT PRIMARY KEY, sets TEXT,reps TEXT, weights TEXT)";
        String sat = "Create table saturday(name TEXT PRIMARY KEY, sets TEXT,reps TEXT, weights TEXT)";
        String sun = "Create table sunday(name TEXT PRIMARY KEY, sets TEXT,reps TEXT, weights TEXT)";
        sqLiteDatabase.execSQL(mon);
        sqLiteDatabase.execSQL(tue);
        sqLiteDatabase.execSQL(wed);
        sqLiteDatabase.execSQL(thu);
        sqLiteDatabase.execSQL(fri);
        sqLiteDatabase.execSQL(sat);
        sqLiteDatabase.execSQL(sun);
        Log.d("CHECK", "onCreate: table created2");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String mon = "Drop table monday";
        String tue = "Drop table tueday";
        String wed = "Drop table wednesday";
        String thu = "Drop table thursday";
        String fri = "Drop table friday";
        String sat = "Drop table saturday";
        String sun = "Drop table sunday";
        sqLiteDatabase.execSQL(mon);
        sqLiteDatabase.execSQL(tue);
        sqLiteDatabase.execSQL(wed);
        sqLiteDatabase.execSQL(thu);
        sqLiteDatabase.execSQL(fri);
        sqLiteDatabase.execSQL(sat);
        sqLiteDatabase.execSQL(sun);
        Log.d("CHECK", " in onUpgrade ");
        onCreate(sqLiteDatabase);
    }

    public void add(String dbname, String exname, String sets, String reps, String weight) {
        SQLiteDatabase sdb = super.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", exname);
        cv.put("sets", sets);
        cv.put("reps", reps);
        cv.put("weights", weight);
        long res = sdb.insert(dbname, null, cv);

        if (res != -1) {
            Log.d("CHECKDB", "rec inserted ");

        } else {
            Log.d("CHECKDB", "not inserted ");
            Toast.makeText(ct, "Something went wrong..You may have entered something wrong", Toast.LENGTH_LONG).show();
        }
        sdb.close();
    }

    public Cursor getData(String day) {
        SQLiteDatabase sdb = super.getReadableDatabase();
        Cursor cr = sdb.rawQuery("SELECT * FROM " + " " + day, null);
        if (cr.moveToFirst()) {
            Log.d("CHECKDB", "getData: returned cr");
            return cr;
        }
        Log.d("CHECK", "getData: returned null");
        return null;

    }

    public void delete(String dbname, String exname) {
        SQLiteDatabase database = this.getWritableDatabase();

        String where = "name='" + exname + "'";
        long res = database.delete(dbname, where, null);
        if (res != -1) {
            Log.d("CHECKDB", "rec deleted ");
            Toast.makeText(ct, "DELETED", Toast.LENGTH_LONG).show();
        } else {
            Log.d("CHECKDB", "not deleted ");
            Toast.makeText(ct, "Something went wrong..You may have entered something wrong", Toast.LENGTH_LONG).show();
        }
        database.close();
    }


}

