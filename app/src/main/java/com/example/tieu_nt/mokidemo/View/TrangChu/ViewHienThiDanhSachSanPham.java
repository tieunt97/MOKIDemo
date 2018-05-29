package com.example.tieu_nt.mokidemo.View.TrangChu;

import com.example.tieu_nt.mokidemo.Model.SanPham;

import java.util.List;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public interface ViewHienThiDanhSachSanPham {
    void hienThiDanhSachSanPham(List<SanPham> dsSanPham);
    void hienThiThatBai(String msg);
}
