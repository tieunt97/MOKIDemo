package com.example.tieu_nt.mokidemo.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Adapter.AdapterDanhMuc;
import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterDanhMucSanPham;
import com.example.tieu_nt.mokidemo.R;

import java.util.List;

/**
 * Created by tieu_nt on 5/7/2018.
 */

public class DanhMucActivity extends AppCompatActivity implements View.OnClickListener, ViewHienThiDanhMucSanPham{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterDanhMuc adapterDanhMuc;
    private ImageButton imgBack;
    private TextView tvTitle, tvThongBao;
    private DanhMuc danhMuc, danhMucTraVe;
    private PresenterDanhMucSanPham presenterDanhMucSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheoloai);
        anhXa();
        tvThongBao.setVisibility(View.GONE);
        danhMuc = (DanhMuc) getIntent().getSerializableExtra("danhMuc");
        tvTitle.setText(danhMuc.getTenDanhMuc());
        presenterDanhMucSanPham = new PresenterDanhMucSanPham(this);
        presenterDanhMucSanPham.layDanhSachDanhMucCon(danhMuc.getIdDanhMuc());
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
    public void hienThiDanhMuc(List<DanhMuc> danhMucs) {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if (danhMuc.getIdDanhMuc() != 0)
            danhMucs.add(0, danhMuc);
        adapterDanhMuc = new AdapterDanhMuc(this, danhMuc.getIdDanhMuc(), danhMucs);
        recyclerView.setAdapter(adapterDanhMuc);
        adapterDanhMuc.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if(requestCode  >= 0){
                this.danhMucTraVe = (DanhMuc) data.getSerializableExtra("danhMuc");
                finish();
            }
        }
    }

    public void setDanhMucTraVe(DanhMuc danhMuc){
        this.danhMucTraVe =  danhMuc;
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("danhMuc", danhMucTraVe);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
