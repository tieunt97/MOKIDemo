package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import com.example.tieu_nt.mokidemo.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/14/2018.
 */

public interface IPresenterTimKiemSanPham {
    void timKiemSanPham(String tenSP, int idLoaiSP, int giaThap, int giaCao, String trangThai, int idKhachHang, int limit);
    List<SanPham> timKiemSanPhamLoadMore(String tenSP, int idLoaiSP, int giaThap, int giaCao, String trangThai, int idKhachHang, int limit);
}
