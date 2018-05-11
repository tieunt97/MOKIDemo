package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.TrangChu.ModelSanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhMucSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/7/2018.
 */

public class PresenterDanhMucSanPham implements IPresenterDanhMucSanPham{
    private ViewHienThiDanhMucSanPham viewHienThiDanhMuc;
    private ModelSanPham modelSanPham;

    public PresenterDanhMucSanPham(ViewHienThiDanhMucSanPham viewHienThiDanhMuc) {
        this.viewHienThiDanhMuc = viewHienThiDanhMuc;
        this.modelSanPham = ModelSanPham.getInstance();
    }

    @Override
    public void layDanhSachDanhMucCon(int idDanhMucCha) {
        List<DanhMuc> danhMucs = modelSanPham.layDSDanhMucCon(idDanhMucCha);
        if (danhMucs.size() > 0){
            viewHienThiDanhMuc.hienThiDanhMuc(danhMucs);
        }
    }
}
