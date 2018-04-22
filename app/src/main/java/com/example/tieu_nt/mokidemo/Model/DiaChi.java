package com.example.tieu_nt.mokidemo.Model;

import java.io.Serializable;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class DiaChi implements Serializable{
    private int idDiaChi, trangThai;
    private String diaChi;

    public int getIdDiaChi() {
        return idDiaChi;
    }

    public void setIdDiaChi(int idDiaChi) {
        this.idDiaChi = idDiaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
