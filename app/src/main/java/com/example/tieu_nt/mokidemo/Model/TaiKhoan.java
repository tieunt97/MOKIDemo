package com.example.tieu_nt.mokidemo.Model;

import java.io.Serializable;

/**
 * Created by tieu_nt on 4/18/2018.
 */

public class TaiKhoan implements Serializable{
    private String soDT, matKhau, ngayKichHoat;
    private int trangThai, maXacNhan;

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getNgayKichHoat() {
        return ngayKichHoat;
    }

    public void setNgayKichHoat(String ngayKichHoat) {
        this.ngayKichHoat = ngayKichHoat;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getMaXacNhan() {
        return maXacNhan;
    }

    public void setMaXacNhan(int maXacNhan) {
        this.maXacNhan = maXacNhan;
    }
}
