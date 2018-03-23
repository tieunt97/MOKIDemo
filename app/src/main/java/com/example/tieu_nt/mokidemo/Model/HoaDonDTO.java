package com.example.tieu_nt.mokidemo.Model;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class HoaDonDTO {
    private int idHoaDon, idKhachHang, idSanPham;
    private String ngayDatHang, ngayGiaoHang;

    public HoaDonDTO(int idHoaDon, int idKhachHang, int idSanPham, String ngayDatHang, String ngayGiaoHang) {
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.idSanPham = idSanPham;
        this.ngayDatHang = ngayDatHang;
        this.ngayGiaoHang = ngayGiaoHang;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getNgayDatHang() {
        return ngayDatHang;
    }

    public void setNgayDatHang(String ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    public String getNgayGiaoHang() {
        return ngayGiaoHang;
    }

    public void setNgayGiaoHang(String ngayGiaoHang) {
        this.ngayGiaoHang = ngayGiaoHang;
    }
}
