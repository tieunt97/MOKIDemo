package com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy;

import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.TrangChu.ModelDangNhap_DangKy;
import com.example.tieu_nt.mokidemo.View.ManHinhDangNhap.ViewDangKy;

/**
 * Created by tieu_nt on 4/18/2018.
 */

public class PresenterLogicDangKy implements IPresenterDangKy{
    private ViewDangKy viewDangKy;
    private ModelDangNhap_DangKy modelDangNhap_dangKy;


    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangNhap_dangKy = new ModelDangNhap_DangKy();
    }

    @Override
    public void dangKyTaiKhoan(String soDT, String matKhau) {
        TaiKhoan taiKhoan = modelDangNhap_dangKy.dangKyTaiKhoan("dangKy", soDT, matKhau);
        if (taiKhoan.getMaXacNhan() == -1){
            viewDangKy.dangKyThatBai("Đã có tài khoản đăng ký với số điện thoại này");
        }else{
            viewDangKy.dangKyThanhCong(taiKhoan);
        }
    }
}
