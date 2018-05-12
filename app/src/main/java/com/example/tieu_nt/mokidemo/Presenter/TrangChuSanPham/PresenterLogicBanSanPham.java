package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import android.graphics.Bitmap;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.TrangChu.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewBanSanPham;

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
        if(dsHinh.size() == 0){
            viewBanSanPham.dangBanThatBai("Chưa có hình sản phẩm");
        }else if(danhMuc == null){
            viewBanSanPham.dangBanThatBai("Danh mục trống");
        }else if(thongTinSanPham.get(0).equals("")){
            viewBanSanPham.dangBanThatBai("Tên sản phẩm trống");
        }else if(thongTinSanPham.get(1).equals("")){
            viewBanSanPham.dangBanThatBai("Mô tả sản phẩm trống");
        }else if(thongTinSanPham.get(2).equals("") && danhMuc.getIdDanhMuc() != 1){
            viewBanSanPham.dangBanThatBai("Giá bán sản phẩm trống");
        }else if(thongTinSanPham.get(3).equals("")){
            viewBanSanPham.dangBanThatBai("Trạng thái sản phẩm trống");
        }else{
            boolean b = modelKhachHang.themSanPham(idKhachHang, dsHinh, danhMuc.getIdDanhMuc(), thongTinSanPham);
            if (b) viewBanSanPham.dangBanThanhCong();
        }
    }
}
