package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import android.util.Log;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.TrangChu.ModelSanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class PresenterLogicSanPham implements IPresenterSanPham{
    private ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham;
    private ModelSanPham modelSanPham;
    private List<SanPham> dsSanPham;


    public PresenterLogicSanPham(ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham) {
        this.viewHienThiDanhSachSanPham = viewHienThiDanhSachSanPham;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachSanPham(String ham, int idLoaiSP) {
        if(dsSanPham == null){
            dsSanPham = modelSanPham.layDanhSachSanPham(ham, idLoaiSP);
        }
        if (dsSanPham.size() > 0){
            viewHienThiDanhSachSanPham.hienThiDanhSachSanPham(dsSanPham);
        }
    }
}
