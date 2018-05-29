package com.example.tieu_nt.mokidemo.View.TrangChu.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.View.TrangChu.LocSanPham;
import com.example.tieu_nt.mokidemo.View.TrangChu.TrangChuActivity;

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
    protected String giaTri = "", sapXep = "";
    protected int idLoaiSP = 0, giaThap = 0, giaCao = 0;

    public abstract void layDanhSachSanPhamSapXep(String giaTri, String sapXep);
    public abstract void setDangList(boolean dangList);
    public abstract void locSanPham(int idLoaiSP, int giaThap, int giaCao);

    public void setGiaTriSapXepLoc(){
        this.giaTri = "";
        this.sapXep = "";
        this.giaThap = 0;
        this.giaCao = 0;
    }

    void refreshItems(String ham) {
        presenterLogicSanPham.layDanhSachSanPham(ham, idLoaiSP,0, TrangChuActivity.idKhachHang,
                "", "", 0, 0);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void setIdLoaiSP(int idLoaiSP) {
        this.idLoaiSP = idLoaiSP;
    }
}
