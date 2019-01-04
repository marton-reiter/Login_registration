package com.example.lehne.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class adatbazis extends SQLiteOpenHelper {




    private static final String DATABASE_NAME = "reg.db";
    private static final String TABLE_NAME = "regs";

    private static final String KEY_ID = "ID";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NEV = "nev";
    private static final String KEY_TELO = "telo";


    public adatbazis(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user TEXT, password TEXT, nev TEXT, telo TEXT)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }




    public boolean addUser(String user, String passw, String nev, String telo){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user);
        values.put(KEY_PASSWORD, passw);
        values.put(KEY_NEV, nev);
        values.put(KEY_TELO, telo);


        long eredmeny = db.insert(TABLE_NAME,
                null,
                values);

        db.close();
        if (eredmeny == -1)
        {
            return false;           //sikertelen adatfelvétel
        }else
            return true;            //sikeres adatfelvétel


    }

    public Cursor adatLekerdezes()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor eredmeny = db.rawQuery("Select * from " + TABLE_NAME,null);
        return eredmeny;
    }
}
