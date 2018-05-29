package com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy;

import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.Data.ModelDangNhapDangKy;
import com.example.tieu_nt.mokidemo.View.DangNhapDangKy.ViewDangNhap;

/**
 * Created by tieu_nt on 4/3/2018.
 */

public class PresenterLogicDangNhap implements IPresenterDangNhap{
    private ViewDangNhap viewDangNhap;
    private ModelDangNhapDangKy modelDangNhap;
    private ModelKhachHang modelKhachHang;

    public PresenterLogicDangNhap(ViewDangNhap viewDangNhap) {
        this.viewDangNhap = viewDangNhap;
        modelDangNhap = ModelDangNhapDangKy.getInstance();
        modelKhachHang = ModelKhachHang.getInstance();
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
                    KhachHang khachHang = modelKhachHang.layThongTinKhachHang("layThongTinKhachHang", taiKhoan);
                    viewDangNhap.dangNhapThanhCong(khachHang);
                    return;
                }
            }else{
                viewDangNhap.dangNhapThatBai("Số điện thoại hoặc mật khẩu không đúng.\nXin vui " +
                        "lòng nhập lại");
            }
        }
    }

}
