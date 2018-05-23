package com.example.tieu_nt.mokidemo.Model.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tieu_nt on 5/23/2018.
 */

public class DatabaseMoki extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SQLITEMOKI";

    public static final String TB_TAIKHOAN = "TAIKHOAN";
    public static final String TB_TAIKHOAN_SDT = "SDT";
    public static final String TB_TAIKHOAN_MATKHAU = "MATKHAU";

    private static final String CREATE_TB_TAIKHOAN= "CREATE TABLE " + TB_TAIKHOAN + " (" + TB_TAIKHOAN_SDT + " TEXT PRIMARY KEY , "
            + TB_TAIKHOAN_MATKHAU + " TEXT);";

    private static final String DELETE_TB_TAIKHOAN = "DROP TABLE IF EXISTS "  + TB_TAIKHOAN;

    public DatabaseMoki(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_TAIKHOAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DELETE_TB_TAIKHOAN);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
