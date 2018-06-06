package com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang;

import com.example.tieu_nt.mokidemo.Model.DonHang;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.ViewHienThiDSDonHang;

import java.util.List;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class PresenterSanPhamKhachHangLogic implements IPresenterSanPhamKhachHang{
    private ViewHienThiDSDonHang viewHienThiDSDonHang;
    private ModelKhachHang modelKhachHang;

    public PresenterSanPhamKhachHangLogic(ViewHienThiDSDonHang viewHienThiDSDonHang) {
        this.viewHienThiDSDonHang = viewHienThiDSDonHang;
        modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDSDonHang(String ham, int idKhachHang, int limit, int trangThai) {
        List<DonHang> dsDonHang = modelKhachHang.layDSDonHang(ham, idKhachHang, limit, trangThai);
        if(dsDonHang.size() > 0){
            viewHienThiDSDonHang.hienThiDSSanPham(dsDonHang);
        }
    }

    @Override
    public List<DonHang> layDSDonHangLoadMore(String ham, int idKhachHang, int limit, int trangThai) {
        return modelKhachHang.layDSDonHang(ham, idKhachHang, limit, trangThai);
    }
}
