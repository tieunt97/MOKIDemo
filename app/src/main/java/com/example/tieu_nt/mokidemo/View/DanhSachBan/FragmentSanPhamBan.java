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
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.Model.DonHang;
import com.example.tieu_nt.mokidemo.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.mokidemo.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang.PresenterLogicSanPhamBan;
import com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang.PresenterSanPhamKhachHangLogic;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.ViewHienThiDanhSachSanPham;
import com.example.tieu_nt.mokidemo.View.ViewHienThiDSDonHang;

import java.util.List;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class FragmentSanPhamBan extends Fragment implements ViewHienThiDanhSachSanPham, ILoadMore{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private List<SanPham> dsSanPham;
    private PresenterLogicSanPhamBan presenterLogicSanPhamBan;
    private int idKhachHang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);

        idKhachHang = DangNhap.getInstance().getKhachHang().getIdKhachHang();

        presenterLogicSanPhamBan = new PresenterLogicSanPhamBan(this);
        presenterLogicSanPhamBan.layDSSanPhamBan("layDSSanPhamBan", idKhachHang, 0);

        return view;
    }

    @Override
    public void loadMore(int tongItem) {
        List<SanPham> sanPhamLoadMore = presenterLogicSanPhamBan.layDSSanPhamBanLoadMore("layDSSanPhamBan",
                idKhachHang, tongItem);
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
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        layoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new AdapterSanPhamGrid(getContext(), this.dsSanPham);
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
    public void hienThiThatBai(String msg) {

    }
}
