package com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy;

import android.content.Context;
import android.util.Log;

import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Model.Data.ModelTaiKhoan;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;

/**
 * Created by tieu_nt on 5/23/2018.
 */

public class PresenterTaiKhoan {
    private ModelTaiKhoan modelTaiKhoan;
    private ModelKhachHang modelKhachHang;

    public PresenterTaiKhoan(Context context){
        modelTaiKhoan = ModelTaiKhoan.getIntance();
        modelTaiKhoan.ketNoiSQLite(context);
        modelKhachHang = ModelKhachHang.getInstance();
    }

    public boolean themTaiKhoan(TaiKhoan taiKhoan){
        boolean b = modelTaiKhoan.themTaiKhoan(taiKhoan);
        return b;
    }

    public boolean xoaTaiKhoan(){
        boolean b = modelTaiKhoan.xoaTaiKhoan();
        return b;
    }

    public boolean capNhatTaiKhoan(String soDT, String matKhau){
        return modelTaiKhoan.capNhatTaiKhoan(soDT, matKhau);
    }

    public TaiKhoan layTaiKhoan(){
        return modelTaiKhoan.layTaiKhoan();
    }

    public KhachHang layThongTinKhachHang(TaiKhoan taiKhoan){
        return modelKhachHang.layThongTinKhachHang("layThongTinKhachHang", taiKhoan);
    }
}
