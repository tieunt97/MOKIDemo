package com.example.tieu_nt.mokidemo.Model;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class KhachHangDTO {
    private int idKhachHang;
    private String tenKhachHang, soDT, diaChi;
    private byte[] anhInfoKH;

    public KhachHangDTO(int idKhachHang, String tenKhachHang, String soDT, String diaChi, byte[] anhInfoKH) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.soDT = soDT;
        this.diaChi = diaChi;
        this.anhInfoKH = anhInfoKH;
    }

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

    public byte[] getAnhInfoKH() {
        return anhInfoKH;
    }

    public void setAnhInfoKH(byte[] anhInfoKH) {
        this.anhInfoKH = anhInfoKH;
    }
}
