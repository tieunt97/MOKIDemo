package com.example.tieu_nt.mokidemo.Presenter.ChiTietSanPham;

import com.example.tieu_nt.mokidemo.Model.SanPham;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public interface IPresenterChiTietSanPham {
    void layDanhSachHinhSP(SanPham sanPham);
    boolean themSanPhamYeuThich(int idKhachHang, int idSanPham);
    boolean xoaSanPhamYeuThich(int idKhachHang, int idSanPham);
    boolean muaSanPham(int idKhachHang, int idSanPham);
}
