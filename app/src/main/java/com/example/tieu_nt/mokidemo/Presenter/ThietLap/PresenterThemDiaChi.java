package com.example.tieu_nt.mokidemo.Presenter.ThietLap;

import com.example.tieu_nt.mokidemo.Model.DiaChi;
import com.example.tieu_nt.mokidemo.View.ThietLap.ViewThemDiaChi;

/**
 * Created by tieu_nt on 4/24/2018.
 */

public class PresenterThemDiaChi implements IPresenterThemDiaChi{
    private ViewThemDiaChi viewThemDiaChi;

    public PresenterThemDiaChi(ViewThemDiaChi viewThemDiaChi) {
        this.viewThemDiaChi = viewThemDiaChi;
    }

    @Override
    public void kiemTraDuLieu(String tinhTP, String quanHuyen, String xaPhuong, String xom, int trangThai) {
        if(tinhTP.equals("")) viewThemDiaChi.themDiaChiThatBai("Bạn chưa chọn Tỉnh, Thành Phố");
        else if(quanHuyen.equals("")) viewThemDiaChi.themDiaChiThatBai("Bạn chưa chọn Quận, Huyện");
        else if(xaPhuong.equals("")) viewThemDiaChi.themDiaChiThatBai("Bạn chưa chọn Phường, Xã, Thị trấn");
        else if(xom.equals("")) viewThemDiaChi.themDiaChiThatBai("Bạn chưa nhập Số nhà, Xóm, Tổ...");
        else {
            DiaChi diaChi = new DiaChi();
            String diaChiS = xom + "-" + xaPhuong + "-" + quanHuyen + "-" + tinhTP;
            diaChi.setDiaChi(diaChiS);
            diaChi.setTrangThai(trangThai);
            viewThemDiaChi.themDiaChiThanhCong(diaChi);
        }
    }
}
