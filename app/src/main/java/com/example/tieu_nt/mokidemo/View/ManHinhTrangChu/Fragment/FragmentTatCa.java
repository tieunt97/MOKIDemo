package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamGrid;
import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamList;
import com.example.tieu_nt.mokidemo.Model.ILoadMore;
import com.example.tieu_nt.mokidemo.Model.LoadMoreScroll;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 2/27/2018.
 */

public class FragmentTatCa extends FragmentSanPham implements ViewHienThiDanhSachSanPham, ILoadMore{
    private RecyclerView recyclerView;
    private PresenterLogicSanPham presenterLogicSanPham;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<SanPham> dsSanPham;
    private boolean dangList = false;
    private int idKhachHang;
    private String giaTri = "", sapXep = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);
        Bundle bundle = getArguments();
        idKhachHang = bundle.getInt("idKhachHang");

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);
        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPham", 0, 0, idKhachHang, giaTri, sapXep);
        return view;
    }

    @Override
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        if(!dangList){
            adapter = new AdapterSanPhamGrid(getContext(), this.dsSanPham);
            layoutManager = new GridLayoutManager(getContext(), 2);
        }else{
            adapter = new AdapterSanPhamList(getContext(),this.dsSanPham);
            layoutManager = new LinearLayoutManager(getContext());
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setDangList(boolean dangList){
        if(this.dangList == !dangList){
            this.dangList = dangList;
            presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPham", 0, 0, idKhachHang, giaTri, sapXep);
        }
    }

    @Override
    public void setGiaTriSapXep() {
        this.giaTri = "";
        this.sapXep = "";
    }

    @Override
    public void loadMore(int tongItem) {
        List<SanPham> sanPhamLoadMore = presenterLogicSanPham.layDanhSachSanPhamLoadMore("layDanhSachSanPham", 0, tongItem, idKhachHang, giaTri, sapXep);
        if (sanPhamLoadMore.size() > 0){
            this.dsSanPham.addAll(sanPhamLoadMore);
            adapter.notifyItemInserted(tongItem - 1);
            Log.d("LoadMore",sanPhamLoadMore.size() + " : " + sanPhamLoadMore.get(0).getTenSanPham());
        }
    }

    @Override
    public void layDanhSachSanPham(String giaTri, String sapXep) {
        this.sapXep = sapXep;
        this.giaTri = giaTri;
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", 0, 0, idKhachHang, giaTri, sapXep);
    }
}
