package com.example.tieu_nt.mokidemo.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamGrid;
import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Model.LoadMore.ILoadMore;
import com.example.tieu_nt.mokidemo.Model.LoadMore.LoadMoreScroll;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicSanPham;
import com.example.tieu_nt.mokidemo.R;

import java.util.List;

/**
 * Created by tieu_nt on 4/8/2018.
 */

public class HienThiSanPhamTheoLoaiActivity extends AppCompatActivity implements View.OnClickListener,
        ViewHienThiDanhSachSanPham, ILoadMore{
    private RecyclerView recyclerView;
    private ImageButton imgBack;
    private TextView tvTenLoaiSP, tvThongBao;
    private AdapterSanPhamGrid adapterSanPham;
    private PresenterLogicSanPham presenterLogicSanPham;
    private DanhMuc danhMuc;
    private int idKhachHang = 0;
    private List<SanPham> dsSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheoloai);
        anhXa();

        tvThongBao.setVisibility(View.GONE);
        Intent intent = getIntent();
        danhMuc = (DanhMuc) intent.getSerializableExtra("danhMuc");
        idKhachHang = intent.getIntExtra("idKhachHang", idKhachHang);
        tvTenLoaiSP.setText(danhMuc.getTenDanhMuc());
        presenterLogicSanPham = new PresenterLogicSanPham(this);
        presenterLogicSanPham.layDanhSachSanPham("layDanhSachSanPhamTheoLoaiSP", danhMuc.getIdDanhMuc(), 0, idKhachHang, "", "",  0, 0);
    }

    private void anhXa(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerDSSanPhamTheoLoai);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        tvTenLoaiSP = (TextView) findViewById(R.id.tvTenLoaiSP);
        tvThongBao = (TextView) findViewById(R.id.tvThongBao);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBack:
                finish();
                break;
        }
    }

    @Override
    public void hienThiDanhSachSanPham(List<SanPham> dsSanPham) {
        this.dsSanPham = dsSanPham;
        adapterSanPham = new AdapterSanPhamGrid(this, dsSanPham);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSanPham);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapterSanPham.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void hienThiThatBai(String msg) {

    }

    @Override
    public void loadMore(int tongItem) {
        List<SanPham> sanPhamLoadMore = presenterLogicSanPham.layDanhSachSanPhamLoadMore("layDanhSachSanPhamTheoLoaiSP", danhMuc.getIdDanhMuc(), tongItem, idKhachHang, "", "", 0, 0);
        if (sanPhamLoadMore.size() > 0){
            dsSanPham.addAll(sanPhamLoadMore);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapterSanPham.notifyDataSetChanged();
                }
            });
        }
    }
}
