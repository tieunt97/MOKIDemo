package com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy;

import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.TrangChu.ModelDangNhap_DangKy;
import com.example.tieu_nt.mokidemo.View.ManHinhDangNhap.ViewDangNhap;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class PresenterLogicDangNhap implements IPresenterDangNhap{
    private ViewDangNhap viewDangNhap;
    private ModelDangNhap_DangKy modelDangNhap;

    public PresenterLogicDangNhap(ViewDangNhap viewDangNhap) {
        this.viewDangNhap = viewDangNhap;
        modelDangNhap = new ModelDangNhap_DangKy();
    }

    @Override
    public void kiemTraDangNhap(String soDT, String matKhau) {
        if(matKhau.equals("") && soDT.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Số điện thoại và Mật khẩu");
            return;
        }
        else if(soDT.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Số điện thoại");
            return;
        }
        else if(matKhau.equals("")){
            viewDangNhap.dangNhapThatBai("Bạn chưa nhập Mật khẩu");
            return;
        }
        else if(soDT.length() < 10){
            viewDangNhap.dangNhapThatBai("Số điện thoại không đúng");
            return;
        }
        else if(matKhau.length() < 6){
            viewDangNhap.dangNhapThatBai("Mật khẩu không hợp lệ, ít nhất 6 ký tự");
            return;
        }else{
            TaiKhoan taiKhoan = modelDangNhap.layTaiKhoan("dangNhap", soDT, matKhau);
            if(!taiKhoan.getSoDT().equals("null")){
                if(taiKhoan.getTrangThai() == -1){
                    viewDangNhap.dangNhapThatBai("Tài khoản chưa được kích hoạt");
                    return;
                }else if(taiKhoan.getTrangThai() == 1){
                    viewDangNhap.dangNhapThanhCong(taiKhoan);
                    return;
                }
            }else{
                viewDangNhap.dangNhapThatBai("Số điện thoại hoặc mật khẩu không đúng.\nXin vui " +
                        "lòng nhập lại");
            }
        }
    }

}
