package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.TrangChu.ModelSanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class PresenterLogicSanPham implements IPresenterSanPham{
    ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham;
    ModelSanPham modelSanPham;

    public PresenterLogicSanPham(ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham) {
        this.viewHienThiDanhSachSanPham = viewHienThiDanhSachSanPham;
        this.modelSanPham = new ModelSanPham();
    }

    @Override
    public void layDanhSachSanPham(String ham) {
        List<SanPham> dsSanPham = modelSanPham.layDanhSachSanPham(ham);
        if (dsSanPham.size() > 0){
            viewHienThiDanhSachSanPham.hienThiDanhSachSanPham(dsSanPham);
        }
    }
}
