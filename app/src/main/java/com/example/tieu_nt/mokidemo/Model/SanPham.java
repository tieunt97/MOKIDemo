package com.example.tieu_nt.mokidemo.Model;

/**
 * Created by tieu_nt on 2/28/2018.
 */

public class SanPham {
    private int hinh, gia, soLuotThich, soBinhLuan;
    private String ten, moTa;
    private boolean yeuThich;

    public SanPham(int hinh, String ten, String moTa, int gia, int soLuotThich, int soBinhLuan, boolean yeuThich){
        this.hinh = hinh;
        this.ten = ten;
        this.moTa = moTa;
        this.gia = gia;
        this.soLuotThich = soLuotThich;
        this.soBinhLuan = soBinhLuan;
        this.yeuThich = yeuThich;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getTen() {
        return ten;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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

    public boolean isYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(boolean yeuThich) {
        this.yeuThich = yeuThich;
    }
}
