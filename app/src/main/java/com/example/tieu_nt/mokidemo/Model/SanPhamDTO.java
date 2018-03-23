package com.example.tieu_nt.mokidemo.Model;

import java.util.List;

/**
 * Created by tieu_nt on 3/20/2018.
 */

public class SanPhamDTO {
    private int idSanPham, idNguoiBan, giaChuan;
    private String tenSanPham, donViTinh, moTa;
    private List<String> dsDanhMucSanPham;
    private List<byte[]> dsHinhSanPham;

    public SanPhamDTO(){

    }

    public SanPhamDTO(int idSanPham, int idNguoiBan, String tenSanPham, String donViTinh, int giaChuan, String moTa) {
        this.idSanPham = idSanPham;
        this.idNguoiBan = idNguoiBan;
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.giaChuan = giaChuan;
        this.moTa = moTa;
    }

    public SanPhamDTO(int idSanPham, int idNguoiBan, String tenSanPham, String donViTinh, int giaChuan,
                      String moTa, List<String> dsDanhMucSanPham, List<byte[]> dsHinhSanPham) {
        this.idSanPham = idSanPham;
        this.idNguoiBan = idNguoiBan;
        this.tenSanPham = tenSanPham;
        this.donViTinh = donViTinh;
        this.giaChuan = giaChuan;
        this.moTa = moTa;
        this.dsDanhMucSanPham = dsDanhMucSanPham;
        this.dsHinhSanPham = dsHinhSanPham;
    }

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

    public int getGiaChuan() {
        return giaChuan;
    }

    public void setGiaChuan(int giaChuan) {
        this.giaChuan = giaChuan;
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

    public List<String> getDsDanhMucSanPham() {
        return dsDanhMucSanPham;
    }

    public void setDsDanhMucSanPham(List<String> dsDanhMucSanPham) {
        this.dsDanhMucSanPham = dsDanhMucSanPham;
    }

    public List<byte[]> getDsHinhSanPham() {
        return dsHinhSanPham;
    }

    public void setDsHinhSanPham(List<byte[]> dsHinhSanPham) {
        this.dsHinhSanPham = dsHinhSanPham;
    }
}
