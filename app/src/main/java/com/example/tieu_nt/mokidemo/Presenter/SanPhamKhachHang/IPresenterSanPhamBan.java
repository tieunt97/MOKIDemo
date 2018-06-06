package com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang;

import com.example.tieu_nt.mokidemo.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 6/6/2018.
 */

public interface IPresenterSanPhamBan {
    void layDSSanPhamBan(String ham, int idKhachHang, int limit);
    List<SanPham> layDSSanPhamBanLoadMore(String ham, int idKhachHang, int limit);
}
