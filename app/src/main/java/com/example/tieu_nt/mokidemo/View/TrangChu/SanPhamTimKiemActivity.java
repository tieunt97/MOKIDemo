package com.example.tieu_nt.mokidemo.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamGrid;
import com.example.tieu_nt.mokidemo.Model.ILoadMore;
import com.example.tieu_nt.mokidemo.Model.LoadMoreScroll;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterLogicTimKiemSanPham;
import com.example.tieu_nt.mokidemo.R;

import java.util.List;

/**
 * Created by tieu_nt on 5/14/2018.
 */

public class SanPhamTimKiemActivity extends AppCompatActivity implements View.OnClickListener, ViewHienThiDanhSachSanPham,
        ILoadMore{
    private RecyclerView recyclerView;
    private ImageButton imgBack;
    private TextView tvTitle, tvThongBao;
    private AdapterSanPhamGrid adapterSanPham;
    private PresenterLogicTimKiemSanPham presenterLogicTimKiemSanPham;
    private int idLoaiSP, giaThap, giaCao;
    private String tenSP, trangThai;
    private int idKhachHang = 0;
    private List<SanPham> dsSanPham;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheoloai);
        anhXa();

        if(TrangChuActivity.khachHang != null){
            idKhachHang = TrangChuActivity.khachHang.getIdKhachHang();
        }

        tvTitle.setText("Sản phẩm tìm kiếm");

        nhanDuLieuTimKiem();

        presenterLogicTimKiemSanPham = new PresenterLogicTimKiemSanPham(this);
        presenterLogicTimKiemSanPham.timKiemSanPham(tenSP, idLoaiSP, giaThap, giaCao, trangThai, idKhachHang, 0);
    }

    private void nhanDuLieuTimKiem(){
        Intent intent = getIntent();
        tenSP = intent.getStringExtra("tenSP");
        Log.d("TENSP", tenSP);
        idLoaiSP = intent.getIntExtra("idLoaiSP", 0);
        giaThap = intent.getIntExtra("giaThap", 0);
        giaCao = intent.getIntExtra("giaCao", 0);
        trangThai = intent.getStringExtra("trangThai");
    }

    private void anhXa(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerDSSanPhamTheoLoai);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tvTenLoaiSP);
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
        Log.d("TIMKIEMSANPHAM", dsSanPham.size() + "");
        this.dsSanPham = dsSanPham;
        tvThongBao.setVisibility(View.GONE);
        adapterSanPham = new AdapterSanPhamGrid(this, dsSanPham);
        layoutManager = new GridLayoutManager(this, 2);
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
    public void loadMore(int tongItem) {
        Log.d("TongItem", tongItem + "");
        List<SanPham> dsSanPhams = presenterLogicTimKiemSanPham.timKiemSanPhamLoadMore(tenSP, idLoaiSP, giaThap, giaCao, trangThai, idKhachHang, tongItem);
        if(dsSanPhams.size() > 0){
            dsSanPham.addAll(dsSanPhams);
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapterSanPham.notifyDataSetChanged();
                }
            });
        }
    }
}
