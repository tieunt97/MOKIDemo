package com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang;

import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.ViewHienThiDSSanPhamKhachHang;

import java.util.List;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class PresenterSanPhamKhachHangLogic implements IPresenterSanPhamKhachHang{
    private ViewHienThiDSSanPhamKhachHang viewHienThiDSSanPhamKhachHang;
    private ModelKhachHang modelKhachHang;

    public PresenterSanPhamKhachHangLogic(ViewHienThiDSSanPhamKhachHang viewHienThiDSSanPhamKhachHang) {
        this.viewHienThiDSSanPhamKhachHang = viewHienThiDSSanPhamKhachHang;
        modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDSSanPham(String ham, KhachHang khachHang, int limit, int loaiSanPham, int trangThai) {
        List<SanPham> dsSanPham = modelKhachHang.layDSSanPhamMuaBan(ham, khachHang, limit, loaiSanPham, trangThai);
        if (dsSanPham.size() > 0) viewHienThiDSSanPhamKhachHang.hienThiDSSanPham(dsSanPham);
    }

    @Override
    public List<SanPham> layDSSanPhamLoadMore(String ham, KhachHang khachHang, int limit, int loaiSanPham, int trangThai) {
        List<SanPham> sanPhamLoadMore = modelKhachHang.layDSSanPhamMuaBan(ham, khachHang, limit, loaiSanPham, trangThai);

        return sanPhamLoadMore;
    }
}
