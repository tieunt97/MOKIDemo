package com.example.tieu_nt.mokidemo.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tieu_nt on 4/18/2018.
 */

public class KhachHang implements Serializable{
    private int idKhachHang, diemTinCay, soSanPham;
    private String tenKhachHang, diaChi, anhInfoKH, anhBia, moTa, thoiGianOnline, thoiGianOffline;
    private TaiKhoan taiKhoan;
    private List<SanPham> dsSanPham;
    private List<DiaChi> dsDiaChi;


    public List<SanPham> getDsSanPham() {
        return dsSanPham;
    }

    public void setDsSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
    }

    public String getAnhInfoKH() {
        return anhInfoKH;
    }

    public void setAnhInfoKH(String anhInfoKH) {
        this.anhInfoKH = anhInfoKH;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getDiemTinCay() {
        return diemTinCay;
    }

    public void setDiemTinCay(int diemTinCay) {
        this.diemTinCay = diemTinCay;
    }

    public int getSoSanPham() {
        return soSanPham;
    }

    public void setSoSanPham(int soSanPham) {
        this.soSanPham = soSanPham;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getThoiGianOnline() {
        return thoiGianOnline;
    }

    public void setThoiGianOnline(String thoiGianOnline) {
        this.thoiGianOnline = thoiGianOnline;
    }

    public String getThoiGianOffline() {
        return thoiGianOffline;
    }

    public void setThoiGianOffline(String thoiGianOffline) {
        this.thoiGianOffline = thoiGianOffline;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public List<DiaChi> getDsDiaChi() {
        return dsDiaChi;
    }

    public void setDsDiaChi(List<DiaChi> dsDiaChi) {
        this.dsDiaChi = dsDiaChi;
    }
}
