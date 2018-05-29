package com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.Data.ModelSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 5/28/2018.
 */

public class PresenterLogicDanhMuc {
    private ModelSanPham modelSanPham;

    public PresenterLogicDanhMuc(){
        modelSanPham = ModelSanPham.getInstance();
    }

    public List<DanhMuc> layDSDanhMucCon(int idDanhMuc){
        List<DanhMuc> dsDanhMuc = modelSanPham.layDSDanhMucCon(idDanhMuc);
        return dsDanhMuc;
    }
}
