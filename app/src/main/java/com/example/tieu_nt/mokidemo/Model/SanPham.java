package com.example.tieu_nt.mokidemo.Model;

import java.io.Serializable;

/**
 * Created by tieu_nt on 2/28/2018.
 */

public class SanPham implements Serializable {
    private int idSanPham, gia, soLuotThich, soBinhLuan;
    private String tenSanPham, moTa, hinhLon, hinhNho, NoiBan;
    private boolean yeuThich;
    private ChiTietSanPham chiTietSanPham;
    private KhachHang khachHang;


    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public String getNoiBan() {
        return NoiBan;
    }

    public void setNoiBan(String noiBan) {
        NoiBan = noiBan;
    }

    public String getHinhNho() {
        return hinhNho;
    }

    public void setHinhNho(String hinhNho) {
        this.hinhNho = hinhNho;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
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
