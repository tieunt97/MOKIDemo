package com.example.tieu_nt.mokidemo.Presenter.ThietLap;

import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.View.ThietLap.ViewDoiMatKhau;

/**
 * Created by tieu_nt on 4/22/2018.
 */

public class PresenterDoiMatKhau implements IPresenterDoiMatKhau{
    private ViewDoiMatKhau viewDoiMatKhau;
    private ModelKhachHang modelKhachHang;
    private int idKhachHang;

    public PresenterDoiMatKhau(ViewDoiMatKhau viewDoiMatKhau, int idKhachHang) {
        this.viewDoiMatKhau = viewDoiMatKhau;
        modelKhachHang = ModelKhachHang.getInstance();
        this.idKhachHang = idKhachHang;
    }

    @Override
    public void doiMatKhau(String matKhau, String matKhauCu, String matKhauMoi, String xacNhanMK) {
        if (matKhauCu.equals("")) viewDoiMatKhau.doiMatKhauThatBai("Bạn chưa nhập Mật khẩu cũ");
        else if(!matKhau.equals(matKhauCu)) viewDoiMatKhau.doiMatKhauThatBai("Mật khẩu cũ không đúng");
        else if(matKhauMoi.equals("")) viewDoiMatKhau.doiMatKhauThatBai("Bạn chưa nhập Mật khẩu mới");
        else if(matKhauMoi.length() < 6) viewDoiMatKhau.doiMatKhauThatBai("Mật khẩu mới không hợp lệ, ít nhất 6 ký tự");
        else if(xacNhanMK.equals("")) viewDoiMatKhau.doiMatKhauThatBai("Bạn chưa Xác nhận mật khẩu mới");
        else if(!matKhauMoi.equals(xacNhanMK)) viewDoiMatKhau.doiMatKhauThatBai("Mật khẩu mới và xác nhận mật khẩu mới không trùng khớp");
        else {
            boolean b = modelKhachHang.doiMatKhau("doiMatKhau", idKhachHang, matKhauMoi);
            if(b) viewDoiMatKhau.doiMatKhauThanhCong(matKhauMoi);
        }
    }
}
