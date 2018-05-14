package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.Data.ModelSanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class PresenterLogicSanPham implements IPresenterSanPham{
    private ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham;
    private ModelSanPham modelSanPham;


    public PresenterLogicSanPham(ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham) {
        this.viewHienThiDanhSachSanPham = viewHienThiDanhSachSanPham;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachSanPham(String ham, int idLoaiSP, int limit, int idKhachHang, String giaTri, String sapXep) {
         List<SanPham> dsSanPham = modelSanPham.layDanhSachSanPham(ham, idLoaiSP, limit, idKhachHang, giaTri, sapXep);
        if (dsSanPham.size() > 0){
            viewHienThiDanhSachSanPham.hienThiDanhSachSanPham(dsSanPham);
        }
    }

    public List<SanPham> layDanhSachSanPhamLoadMore(String ham, int idLoaiSP, int limit, int idKhachHang, String giaTri, String sapXep) {
        List<SanPham> dsSanPham =  modelSanPham.layDanhSachSanPham(ham, idLoaiSP, limit, idKhachHang, giaTri, sapXep);

        return dsSanPham;
    }
}
