package com.example.tieu_nt.mokidemo.Presenter.DanhSachYeuThich;

import android.util.Log;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.TrangChu.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.DanhSachYeuThich.ViewDSSanPhamYeuThich;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 4/26/2018.
 */

public class PresenterDanhSachYeuThich implements IPresenterDanhSachYeuThich{
    private ViewDSSanPhamYeuThich viewDSSanPhamYeuThich;
    private ModelKhachHang modelKhachHang;

    public PresenterDanhSachYeuThich(ViewDSSanPhamYeuThich viewDSSanPhamYeuThich) {
        this.viewDSSanPhamYeuThich = viewDSSanPhamYeuThich;
        this.modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDSSanPhamYeuThich(String ham, int idKhachHang, int limit) {
        List<SanPham> dsSanPham = modelKhachHang.layDSSanPhamYeuThich(ham, idKhachHang, limit);
        if (dsSanPham.size() > 0){
            viewDSSanPhamYeuThich.hienThiDSSanPham(dsSanPham);
        }else{
            Log.d("BLABLA", "Null");
        }
    }
}
