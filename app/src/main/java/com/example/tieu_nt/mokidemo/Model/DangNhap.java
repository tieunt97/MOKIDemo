package com.example.tieu_nt.mokidemo.Model;

/**
 * Created by tieu_nt on 5/27/2018.
 */

public class DangNhap {
    private static DangNhap dangNhap;
    private KhachHang khachHang;

    private DangNhap(){

    }

    public static DangNhap getInstance(){
        if(dangNhap == null)
            dangNhap = new DangNhap();

        return dangNhap;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
