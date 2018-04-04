package com.example.tieu_nt.mokidemo.Model;

import java.util.List;

/**
 * Created by tieu_nt on 2/28/2018.
 */

public class SanPham {
    private int idSanPham, idNguoiBan, gia, soLuotThich, soBinhLuan;
    private String tenSanPham, donViTinh, moTa, hinhLon;
    private boolean yeuThich;
    private ChiTietSanPham chiTietSanPham;

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public int getIdNguoiBan() {
        return idNguoiBan;
    }

    public void setIdNguoiBan(int idNguoiBan) {
        this.idNguoiBan = idNguoiBan;
    }

    public String getHinhLon() {
        return hinhLon;
    }

    public void setHinhLon(String hinhLon) {
        this.hinhLon = hinhLon;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoLuotThich() {
        return soLuotThich;
    }

    public void setSoLuotThich(int soLuotThich) {
        this.soLuotThich = soLuotThich;
    }

    public int getSoBinhLuan() {
        return soBinhLuan;
    }

    public void setSoBinhLuan(int soBinhLuan) {
        this.soBinhLuan = soBinhLuan;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public boolean isYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(boolean yeuThich) {
        this.yeuThich = yeuThich;
    }

    public ChiTietSanPham getChiTietSanPham() {
        return chiTietSanPham;
    }

    public void setChiTietSanPham(ChiTietSanPham chiTietSanPham) {
        this.chiTietSanPham = chiTietSanPham;
    }
}
