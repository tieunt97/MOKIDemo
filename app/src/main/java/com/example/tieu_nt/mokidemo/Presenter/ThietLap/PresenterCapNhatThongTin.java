package com.example.tieu_nt.mokidemo.Presenter.ThietLap;

import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.TrangChu.TrangChuActivity;

/**
 * Created by tieu_nt on 5/23/2018.
 */

public class PresenterCapNhatThongTin implements IPresenterCapNhatThongTin{
    private ModelKhachHang modelKhachHang;


    public PresenterCapNhatThongTin(){
        modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void capNhatThongTin(String name, String image, String nameAnhBia, String imageAnhBia, String moTa) {
        modelKhachHang.capNhatThongTinKhachHang("capNhatThongTinKhachHang", TrangChuActivity.khachHang.getIdKhachHang(),
                name, image, nameAnhBia, imageAnhBia, moTa);
    }
}
