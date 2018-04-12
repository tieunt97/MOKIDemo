package com.example.tieu_nt.mokidemo.Presenter.ChiTietSanPham;

import android.util.Log;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewChiTietSanPham;


/**
 * Created by tieu_nt on 4/12/2018.
 */

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham{
    ViewChiTietSanPham viewChiTietSanPham;


    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
    }

    @Override
    public void layDanhSachHinhSP(SanPham sanPham) {
        if (sanPham.getIdSanPham() > 0){
            String[] hinhNho = sanPham.getHinhNho().split(",");
            String[] linkHinhSP = new String[hinhNho.length + 1];
            linkHinhSP[0] = sanPham.getHinhLon();
            for(int i = 1; i < linkHinhSP.length; i++){
                linkHinhSP[i] = ManHinhTrangChuActivity.SERVER + hinhNho[i -1];
                Log.d("HINHSP", linkHinhSP[i]);
            }

            viewChiTietSanPham.hienThiSliderSP(linkHinhSP);
        }
    }
}
