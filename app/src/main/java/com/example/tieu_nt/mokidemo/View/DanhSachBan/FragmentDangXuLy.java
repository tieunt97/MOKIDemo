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
import com.example.tieu_nt.mokidemo.Model.ILoadMore;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.LoadMoreScroll;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang.PresenterSanPhamKhachHangLogic;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ViewHienThiDSSanPhamKhachHang;

import java.util.List;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class FragmentDangXuLy extends Fragment implements ViewHienThiDSSanPhamKhachHang, ILoadMore {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private PresenterSanPhamKhachHangLogic presenterSanPhamKhachHangLogic;
    private List<SanPham> dsSanPham;
    private KhachHang khachHang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);

        Bundle bundle = getArguments();
        khachHang = (KhachHang) bundle.getSerializable("khachHang");
        presenterSanPhamKhachHangLogic = new PresenterSanPhamKhachHangLogic(this);
        presenterSanPhamKhachHangLogic.layDSSanPham("layDSSanPhamMuaBan", khachHang, 0, 1, 1);

        return view;
    }

    @Override
    public void hienThiDSSanPham(List<SanPham> dsSanPhams) {
        this.dsSanPham = dsSanPhams;
        layoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new AdapterSanPhamGrid(getContext(), dsSanPhams);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void loadMore(int tongItem) {
        List<SanPham> sanPhamLoadMore = presenterSanPhamKhachHangLogic.layDSSanPhamLoadMore("layDSSanPhamMuaBan", khachHang, tongItem, 1, 0);
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
}
