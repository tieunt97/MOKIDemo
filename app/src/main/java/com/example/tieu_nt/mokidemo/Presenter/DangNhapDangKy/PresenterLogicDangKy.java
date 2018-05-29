package com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy;

import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.Data.ModelDangNhapDangKy;
import com.example.tieu_nt.mokidemo.View.DangNhapDangKy.ViewDangKy;

/**
 * Created by tieu_nt on 4/18/2018.
 */

public class PresenterLogicDangKy implements IPresenterDangKy{
    private ViewDangKy viewDangKy;
    private ModelDangNhapDangKy modelDangNhap_dangKy;


    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        modelDangNhap_dangKy = ModelDangNhapDangKy.getInstance();
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
