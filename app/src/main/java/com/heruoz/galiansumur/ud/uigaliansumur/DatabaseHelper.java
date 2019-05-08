package com.heruoz.galiansumur.ud.uigaliansumur;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dataipal.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_DATAIPAL_TABLE =
                "CREATE TABLE " + DatabaseContact.TransaksiEntry.TABLE_NAME+" ("+
                        DatabaseContact.TransaksiEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_TGL + " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_JAM + " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_AIR + " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_OZONE+ " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_BAKTERI+ " TEXT NOT NULL,"+
                        DatabaseContact.TransaksiEntry.COLOUMN_HASIL+ " TEXT NOT NULL"+
                        ")";
        sqLiteDatabase.execSQL(SQL_CREATE_DATAIPAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if( newVersion > oldVersion){
            db.execSQL(" DROP TABLE IF EXISTS " +
                    DatabaseContact.TransaksiEntry.TABLE_NAME);
            onCreate(db);
        }
    }

//    public Cursor LihatData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor lihatdata = db.rawQuery("select email_user,password_user from "+DatabaseContact.TransaksiEntry.TABLE_NAME,null);
//        return lihatdata;
//    }
}

