package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import com.example.tieu_nt.mokidemo.Model.Data.ModelSanPham;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/14/2018.
 */

public class PresenterLogicTimKiemSanPham implements IPresenterTimKiemSanPham{
    private ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham;
    private ModelSanPham modelSanPham;

    public PresenterLogicTimKiemSanPham(ViewHienThiDanhSachSanPham viewHienThiDanhSachSanPham) {
        this.viewHienThiDanhSachSanPham = viewHienThiDanhSachSanPham;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void timKiemSanPham(String tenSP, int idLoaiSP, int giaThap, int giaCao, String trangThai, int idKhachHang, int limit) {
        List<SanPham> dsSanPham = modelSanPham.layDanhSachSanPhamTimKiem(tenSP, idLoaiSP, giaThap, giaCao, trangThai, idKhachHang, limit);
        if(dsSanPham.size() > 0){
            viewHienThiDanhSachSanPham.hienThiDanhSachSanPham(dsSanPham);
        }
    }

    @Override
    public List<SanPham> timKiemSanPhamLoadMore(String tenSP, int idLoaiSP, int giaThap, int giaCao, String trangThai, int idKhachHang, int limit) {
        List<SanPham> dsSanPham = modelSanPham.layDanhSachSanPhamTimKiem(tenSP, idLoaiSP, giaThap, giaCao, trangThai, idKhachHang, limit);

        return  dsSanPham;
    }


}
