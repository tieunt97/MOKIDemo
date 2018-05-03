package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment;

import android.support.v4.app.Fragment;

/**
 * Created by tieu_nt on 5/3/2018.
 */

public abstract class FragmentSanPham extends Fragment{
    public abstract void layDanhSachSanPham(String giaTri, String sapXep);
    public abstract void setDangList(boolean dangList);
    public abstract void setGiaTriSapXep();
}
