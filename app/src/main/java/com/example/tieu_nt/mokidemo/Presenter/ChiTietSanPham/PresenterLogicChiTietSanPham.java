package com.example.tieu_nt.mokidemo.Presenter.ChiTietSanPham;

import android.util.Log;

import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.View.TrangChu.ViewChiTietSanPham;


/**
 * Created by tieu_nt on 4/12/2018.
 */

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham{
    private ViewChiTietSanPham viewChiTietSanPham;
    private ModelKhachHang modelKhachHang;


    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        this.modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDanhSachHinhSP(SanPham sanPham) {
        if (sanPham.getIdSanPham() > 0){
            String[] hinhNho = sanPham.getHinhNho().split(",");
            String[] linkHinhSP = new String[hinhNho.length + 1];
            linkHinhSP[0] = sanPham.getHinhLon();
            for(int i = 1; i < linkHinhSP.length; i++){
                linkHinhSP[i] = Constants.SERVER + hinhNho[i -1];
                Log.d("HINHSP", linkHinhSP[i]);
            }

            viewChiTietSanPham.hienThiSliderSP(linkHinhSP);
            viewChiTietSanPham.hienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public boolean themSanPhamYeuThich(int idKhachHang, int idSanPham) {
        return modelKhachHang.capNhatSanPhamYeuThich("themSanPhamYeuThich", idKhachHang, idSanPham);
    }

    @Override
    public boolean xoaSanPhamYeuThich(int idKhachHang, int idSanPham) {
        return modelKhachHang.capNhatSanPhamYeuThich("xoaSanPhamYeuThich", idKhachHang, idSanPham);
    }

    @Override
    public boolean muaSanPham(int idKhachHang, int idSanPham) {
        return modelKhachHang.muaSanPham(idKhachHang, idSanPham);
    }
}
