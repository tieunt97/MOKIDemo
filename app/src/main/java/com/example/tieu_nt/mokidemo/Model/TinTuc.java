package com.example.tieu_nt.mokidemo.Model;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class TinTuc {
    private String ngayDang, thoiGian, tenThongBao;

    public TinTuc(String ngayDang, String thoiGian, String tenThongBao) {
        this.ngayDang = ngayDang;
        this.thoiGian = thoiGian;
        this.tenThongBao = tenThongBao;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTenThongBao() {
        return tenThongBao;
    }

    public void setTenThongBao(String tenThongBao) {
        this.tenThongBao = tenThongBao;
    }
}
