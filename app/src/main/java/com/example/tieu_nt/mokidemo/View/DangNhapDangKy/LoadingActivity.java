package com.example.tieu_nt.mokidemo.View.DangNhapDangKy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy.PresenterTaiKhoan;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.TrangChuActivity;

/**
 * Created by tieu_nt on 5/23/2018.
 */

public class LoadingActivity extends AppCompatActivity{
    private PresenterTaiKhoan presenterTaiKhoan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loading);

        presenterTaiKhoan = new PresenterTaiKhoan(this);
        TaiKhoan taiKhoan = presenterTaiKhoan.layTaiKhoan();
        if(taiKhoan.getSoDT() != null){
            KhachHang khachHang = presenterTaiKhoan.layThongTinKhachHang(taiKhoan);
            DangNhap.getInstance().setKhachHang(khachHang);
            Intent iTrangChu = new Intent(this, TrangChuActivity.class);
            iTrangChu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(iTrangChu);
        }else{
            Intent intent = new Intent(this, DangNhapActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
