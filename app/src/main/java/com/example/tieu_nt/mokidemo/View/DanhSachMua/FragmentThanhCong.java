package com.example.tieu_nt.mokidemo.View.DanhSachMua;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.mokidemo.Adapter.AdapterDonHang;
import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamGrid;
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.Model.DonHang;
import com.example.tieu_nt.mokidemo.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.mokidemo.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.SanPhamKhachHang.PresenterSanPhamKhachHangLogic;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ViewHienThiDSDonHang;

import java.util.List;

/**
 * Created by tieu_nt on 4/21/2018.
 */

public class FragmentThanhCong extends Fragment implements ViewHienThiDSDonHang, ILoadMore {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private PresenterSanPhamKhachHangLogic presenterSanPhamKhachHangLogic;
    private List<DonHang> dsDonHang;
    private int idKhachHang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sanpham, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSanPham);

        idKhachHang = DangNhap.getInstance().getKhachHang().getIdKhachHang();

        presenterSanPhamKhachHangLogic = new PresenterSanPhamKhachHangLogic(this);
        presenterSanPhamKhachHangLogic.layDSDonHang("layDSDonHangMua", idKhachHang, 0, DonHang.TRANGTHAI_THANHCONG);

        return view;
    }

    @Override
    public void loadMore(int tongItem) {
        List<DonHang> donHangLoadMore = presenterSanPhamKhachHangLogic.layDSDonHangLoadMore("layDSDonHangMua",
                idKhachHang, 0, DonHang.TRANGTHAI_THANHCONG);
        if (donHangLoadMore.size() > 0){
            this.dsDonHang.addAll(donHangLoadMore);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void hienThiDSSanPham(List<DonHang> dsDonHang) {
        this.dsDonHang = dsDonHang;
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new AdapterDonHang(getContext(), this.dsDonHang);
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
}
