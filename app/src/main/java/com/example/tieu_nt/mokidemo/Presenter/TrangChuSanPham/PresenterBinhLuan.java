package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import android.util.Log;

import com.example.tieu_nt.mokidemo.Model.BinhLuan;
import com.example.tieu_nt.mokidemo.Model.Data.ModelSanPham;
import com.example.tieu_nt.mokidemo.View.TrangChu.ViewHienThiDanhSachBinhLuan;

import java.util.List;

/**
 * Created by tieu_nt on 4/28/2018.
 */

public class PresenterBinhLuan implements IPrenterBinhLuan{
    private ViewHienThiDanhSachBinhLuan viewHienThiDanhSachBinhLuan;
    private ModelSanPham modelSanPham;

    public PresenterBinhLuan(ViewHienThiDanhSachBinhLuan viewHienThiDanhSachBinhLuan) {
        this.viewHienThiDanhSachBinhLuan = viewHienThiDanhSachBinhLuan;
        modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachBinhLuan(int idSanPham) {
        List<BinhLuan> dsBinhLuan = modelSanPham.layDanhSachBinhLuan(idSanPham);
        if(dsBinhLuan.size() > 0){
            Log.d("binhLuan", dsBinhLuan.get(0).getNoiDungBL());
            viewHienThiDanhSachBinhLuan.hienThiDanhSachBinhLuan(dsBinhLuan);
        }else{
            Log.d("NOCOMENT", "NULL");
        }
    }
}
