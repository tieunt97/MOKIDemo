package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import android.graphics.Bitmap;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;

import java.util.List;

/**
 * Created by tieu_nt on 5/12/2018.
 */

public interface IPresenterBanSanPham {
    void dangBanSanPham(int idKhachHang, List<String> dsHinh, DanhMuc danhMuc, List<String> thongTinSanPham);
}
