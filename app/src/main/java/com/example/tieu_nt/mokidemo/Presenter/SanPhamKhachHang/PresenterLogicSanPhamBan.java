package com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang;

import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.View.TrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 6/6/2018.
 */

public class PresenterLogicSanPhamBan implements IPresenterSanPhamBan{
    private ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham;
    private ModelKhachHang modelKhachHang;

    public PresenterLogicSanPhamBan(ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham) {
        this.viewHienThiDanhSachSanPham = viewHienThiDanhSachSanPham;
        this.modelKhachHang = ModelKhachHang.getInstance();
    }


    @Override
    public void layDSSanPhamBan(String ham, int idKhachHang, int limit) {
        List<SanPham> dsSanPham = modelKhachHang.layDSSanPhamBan(ham, idKhachHang, limit);
        if(dsSanPham.size() > 0) viewHienThiDanhSachSanPham.hienThiDanhSachSanPham(dsSanPham);
    }

    @Override
    public List<SanPham> layDSSanPhamBanLoadMore(String ham, int idKhachHang, int limit) {
        return modelKhachHang.layDSSanPhamBan(ham, idKhachHang, limit);
    }
}
