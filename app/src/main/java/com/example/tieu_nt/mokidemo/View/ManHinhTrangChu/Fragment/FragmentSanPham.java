package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

import java.util.List;

/**
 * Created by tieu_nt on 5/3/2018.
 */

public abstract class FragmentSanPham extends Fragment{
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected RecyclerView recyclerView;
    protected PresenterLogicSanPham presenterLogicSanPham;
    protected RecyclerView.LayoutManager layoutManagerLinear, layoutManagerGrid;
    protected RecyclerView.Adapter adapter;
    protected List<SanPham> dsSanPham;

    public abstract void layDanhSachSanPhamSapXep(String giaTri, String sapXep);
    public abstract void setDangList(boolean dangList);
    public abstract void setGiaTriSapXep();

    void refreshItems(String ham, int idLoaiSP) {
        presenterLogicSanPham.layDanhSachSanPham(ham, idLoaiSP,0, ManHinhTrangChuActivity.idKhachHang, "", "");
        swipeRefreshLayout.setRefreshing(false);
    }
}
