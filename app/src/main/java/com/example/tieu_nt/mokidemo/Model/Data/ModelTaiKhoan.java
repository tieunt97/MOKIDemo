package com.example.tieu_nt.mokidemo.Model.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tieu_nt.mokidemo.Model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 5/23/2018.
 */

public class ModelTaiKhoan {
    private static ModelTaiKhoan modelTaiKhoan;
    private SQLiteDatabase database;

    private ModelTaiKhoan(){

    }

    public static ModelTaiKhoan getIntance(){
        if(modelTaiKhoan == null){
            modelTaiKhoan = new ModelTaiKhoan();
        }

        return modelTaiKhoan;
    }

    public void ketNoiSQLite(Context context){
        DatabaseMoki databaseMoki = new DatabaseMoki(context);
        database = databaseMoki.getWritableDatabase();
    }

    public boolean xoaTaiKhoan(){
        int kiemTra = database.delete(DatabaseMoki.TB_TAIKHOAN, null, null);
        if (kiemTra > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean capNhatTaiKhoan(String soDT, String matKhau){
        ContentValues values = new ContentValues();
        values.put(DatabaseMoki.TB_TAIKHOAN_MATKHAU, matKhau);

        int id = database.update(DatabaseMoki.TB_TAIKHOAN, values, DatabaseMoki.TB_TAIKHOAN_SDT + " = " + soDT, null);
        if (id > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean themTaiKhoan(TaiKhoan taiKhoan){
        ContentValues values = new ContentValues();
        values.put(DatabaseMoki.TB_TAIKHOAN_SDT, taiKhoan.getSoDT());
        values.put(DatabaseMoki.TB_TAIKHOAN_MATKHAU, taiKhoan.getMatKhau());

        long id = database.insert(DatabaseMoki.TB_TAIKHOAN, null, values);
        if (id > 0){
            return true;
        }else{
            return false;
        }

    }

    public TaiKhoan layTaiKhoan(){
        TaiKhoan taiKhoan = new TaiKhoan();

        String sql = "SELECT * FROM " + DatabaseMoki.TB_TAIKHOAN;
        Cursor cursor =  database.rawQuery(sql, null);
        while (cursor.moveToNext()){
            taiKhoan.setSoDT(cursor.getString(cursor.getColumnIndex(DatabaseMoki.TB_TAIKHOAN_SDT)));
            taiKhoan.setMatKhau(cursor.getString(cursor.getColumnIndex(DatabaseMoki.TB_TAIKHOAN_MATKHAU)));
        }

        return taiKhoan;
    }
}
