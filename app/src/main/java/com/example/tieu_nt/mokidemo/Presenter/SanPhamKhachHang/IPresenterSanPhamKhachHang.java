package com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang;

import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public interface IPresenterSanPhamKhachHang {
    void layDSSanPham(String ham, int idKhachHang, int limit, int loaiSanPham, int trangThai);
    List<SanPham> layDSSanPhamLoadMore(String ham, int idKhachHang, int limit, int loaiSanPham, int trangThai);
}
