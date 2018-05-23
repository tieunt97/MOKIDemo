package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.TrangChu.ViewBanSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/12/2018.
 */

public class PresenterLogicBanSanPham implements IPresenterBanSanPham{
    private ViewBanSanPham viewBanSanPham;
    private ModelKhachHang modelKhachHang;

    public PresenterLogicBanSanPham(ViewBanSanPham viewBanSanPham) {
        this.viewBanSanPham = viewBanSanPham;
        this.modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void dangBanSanPham(int idKhachHang, List<String> dsHinh, DanhMuc danhMuc, List<String> thongTinSanPham) {
        boolean b = modelKhachHang.themSanPham(idKhachHang, dsHinh, danhMuc.getIdDanhMuc(), thongTinSanPham);
        if (b) viewBanSanPham.dangBanThanhCong();
    }
}
