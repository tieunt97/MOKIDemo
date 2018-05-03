package com.example.tieu_nt.mokidemo.View.DanhSachBan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamGrid;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang.PresenterSanPhamKhachHangLogic;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ViewHienThiDSSanPhamKhachHang;

import java.util.List;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class FragmentSanPham extends Fragment implements ViewHienThiDSSanPhamKhachHang{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private PresenterSanPhamKhachHangLogic presenterSanPhamKhachHangLogic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);

        Bundle bundle = getArguments();
        KhachHang khachHang = (KhachHang) bundle.getSerializable("khachHang");
        presenterSanPhamKhachHangLogic = new PresenterSanPhamKhachHangLogic(this);
        presenterSanPhamKhachHangLogic.layDSSanPham("layDSSanPhamBan", khachHang);

        return view;
    }

    @Override
    public void hienThiDSSanPham(List<SanPham> dsSanPhams) {
        layoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new AdapterSanPhamGrid(getContext(), dsSanPhams);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
