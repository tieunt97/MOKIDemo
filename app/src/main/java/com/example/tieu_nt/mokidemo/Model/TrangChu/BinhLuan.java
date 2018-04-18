package com.example.tieu_nt.mokidemo.Model.TrangChu;

import java.io.Serializable;

/**
 * Created by tieu_nt on 4/18/2018.
 */

public class BinhLuan implements Serializable{
    int idKhachHang;
    String tenKhachHang, noiDungBL, hinhKhachHang;

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNoiDungBL() {
        return noiDungBL;
    }

    public void setNoiDungBL(String noiDungBL) {
        this.noiDungBL = noiDungBL;
    }

    public String getHinhKhachHang() {
        return hinhKhachHang;
    }

    public void setHinhKhachHang(String hinhKhachHang) {
        this.hinhKhachHang = hinhKhachHang;
    }
}
