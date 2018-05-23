package com.example.tieu_nt.mokidemo.Presenter.TinTuc;

import com.example.tieu_nt.mokidemo.Model.TinTuc;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.TinTuc.ViewHienThiDanhSachTinTuc;

import java.util.List;

/**
 * Created by tieu_nt on 4/26/2018.
 */

public class PresenterLogicTinTuc implements IPresenterTinTuc{
    private ViewHienThiDanhSachTinTuc viewHienThiDanhSachTinTuc;
    private ModelKhachHang modelKhachHang;

    public PresenterLogicTinTuc(ViewHienThiDanhSachTinTuc viewHienThiDanhSachTinTuc) {
        this.viewHienThiDanhSachTinTuc = viewHienThiDanhSachTinTuc;
        modelKhachHang = ModelKhachHang.getInstance();
    }

    @Override
    public void layDanhSachTinTuc(int limit) {
        List<TinTuc> dsTinTuc = modelKhachHang.layDanhSachTinTuc(limit);
        if (dsTinTuc.size() > 0){
            viewHienThiDanhSachTinTuc.hienThiDanhSachTinTuc(dsTinTuc);
        }
    }
}
