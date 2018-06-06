package com.example.tieu_nt.mokidemo.Model;

import java.util.Date;

/**
 * Created by tieu_nt on 6/6/2018.
 */

public class DonHang {
    public static final int TRANGTHAI_DANGXULY = 0, TRANGTHAI_THANHCONG = 4;
    private SanPham sanPham;
    private String soDT, diaChi;
    private Date ngayDat, ngayGiao;

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(Date ngayGiao) {
        this.ngayGiao = ngayGiao;
    }
}
