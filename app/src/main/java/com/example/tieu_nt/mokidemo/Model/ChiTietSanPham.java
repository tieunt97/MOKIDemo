package com.example.tieu_nt.mokidemo.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class ChiTietSanPham implements Serializable{
    private String khoiLuong, trangThai, kichThuoc;
    private List<DanhMuc> danhMucList;


    public List<DanhMuc> getDanhMucList() {
        return danhMucList;
    }

    public void setDanhMucList(List<DanhMuc> danhMucList) {
        this.danhMucList = danhMucList;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(String khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }
}
