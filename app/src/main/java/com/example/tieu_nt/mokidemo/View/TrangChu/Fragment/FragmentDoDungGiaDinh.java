package com.example.tieu_nt.mokidemo.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamGrid;
import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamList;
import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.mokidemo.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.LocSanPham;
import com.example.tieu_nt.mokidemo.View.TrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 3/12/2018.
 */

public class FragmentDoDungGiaDinh extends FragmentSanPham implements ViewHienThiDanhSachSanPham, ILoadMore{
    private RecyclerView.Adapter adapter;
    private List<SanPham> dsSanPham;
    private boolean dangList = false;
    private int idKhachHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);
        Bundle bundle = getArguments();
        idKhachHang = bundle.getInt("idKhachHang");

        setIdLoaiSP(Constants.ID_DODUNGGIADINH);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);
        layoutManagerGrid = new GridLayoutManager(getContext(), 2);
        layoutManagerLinear = new LinearLayoutManager(getContext());
        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", idLoaiSP, 0,
                idKhachHang, giaTri, sapXep, giaThap, giaCao);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setIdLoaiSP(Constants.ID_DODUNGGIADINH);
                refreshItems("layDanhSachSanPhamTheoLoaiSP");
            }
        });

        return view;
    }

    @Override
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        if(!dangList){
            recyclerView.setLayoutManager(layoutManagerGrid);
            adapter = new AdapterSanPhamGrid(getContext(), this.dsSanPham);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManagerGrid, this));
        }else{
            recyclerView.setLayoutManager(layoutManagerLinear);
            adapter = new AdapterSanPhamList(getContext(),this.dsSanPham);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManagerLinear, this));
        }
    }

    @Override
    public void hienThiThatBai(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setDangList(boolean dangList){
        this.dangList = dangList;
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", idLoaiSP,
                0, idKhachHang, giaTri, sapXep, giaThap, giaCao);
    }

    @Override
    public void loadMore(int tongItem) {
        List<SanPham> sanPhamLoadMore = presenterLogicSanPham.layDanhSachSanPhamLoadMore("layDanhSachSanPhamTheoLoaiSP",
                idLoaiSP, tongItem, idKhachHang, giaTri, sapXep, giaThap, giaCao);
        if (sanPhamLoadMore.size() > 0){
            dsSanPham.addAll(sanPhamLoadMore);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void layDanhSachSanPhamSapXep(String giaTri, String sapXep) {
        this.sapXep = sapXep;
        this.giaTri = giaTri;
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", idLoaiSP,
                0, idKhachHang, giaTri, sapXep, giaThap, giaCao);
    }

    @Override
    public void locSanPham(int idLoaiSP, int giaThap, int giaCao) {
        this.idLoaiSP = idLoaiSP;
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", idLoaiSP, 0,
                idKhachHang, giaTri, sapXep, giaThap, giaCao);
    }
}
