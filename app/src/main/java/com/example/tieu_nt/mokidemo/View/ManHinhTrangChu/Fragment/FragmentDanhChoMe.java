package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPham;
import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamList;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 3/12/2018.
 */

public class FragmentDanhChoMe extends Fragment implements ViewHienThiDanhSachSanPham{
    private RecyclerView recyclerView;
    private PresenterLogicSanPham presenterLogicSanPham;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private boolean dangList = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);
        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", 10);
        return view;
    }

    @Override
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        if(!dangList){
            adapter = new AdapterSanPham(getContext(), dsSanPham);
            layoutManager = new GridLayoutManager(getContext(), 2);
        }else{
            adapter = new AdapterSanPhamList(getContext(), dsSanPham);
            layoutManager = new LinearLayoutManager(getContext());
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setDangList(boolean dangList){
        if(this.dangList == !dangList){
            this.dangList = dangList;
            presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", 10);
        }
    }
}
