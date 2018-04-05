package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPham;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ViewHienThiDanhSachSanPham;

import java.util.List;

/**
 * Created by tieu_nt on 3/12/2018.
 */

public class FragmentDoDungGiaDinh extends Fragment implements ViewHienThiDanhSachSanPham{
    private RecyclerView recyclerView;
    private PresenterLogicSanPham presenterLogicSanPham;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dodunggiadinh, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerDoDungGiaDinh);
        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamDoDungGiaDinh");
        return view;
    }

    @Override
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        AdapterSanPham adapterSanPham = new AdapterSanPham(getContext(), dsSanPham);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSanPham);
        adapterSanPham.notifyDataSetChanged();
    }
}
