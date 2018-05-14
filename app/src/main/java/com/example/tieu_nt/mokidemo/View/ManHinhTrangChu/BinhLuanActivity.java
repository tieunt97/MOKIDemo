package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.Adapter.AdapterBinhLuan;
import com.example.tieu_nt.mokidemo.Model.BinhLuan;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Presenter.TrangChuSanPham.PresenterBinhLuan;
import com.example.tieu_nt.mokidemo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tieu_nt on 4/14/2018.
 */

public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener, ViewHienThiDanhSachBinhLuan{
    private ImageButton imgBack, imgGui;
    private RecyclerView recyclerBinhLuan;
    private EditText edtBinhLuan;
    private PresenterBinhLuan presenterBinhLuan;
    private AdapterBinhLuan adapterBinhLuan;
    private RecyclerView.LayoutManager layoutManager;
    private ModelKhachHang modelKhachHang;
    private int idSanPham;
    private List<BinhLuan> dsBinhLuan;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);
        AnhXa();

        idSanPham = getIntent().getIntExtra("idSanPham", 0);
        modelKhachHang = ModelKhachHang.getInstance();

        layoutManager = new LinearLayoutManager(this);
        recyclerBinhLuan.setLayoutManager(layoutManager);
        dsBinhLuan = new ArrayList<>();
        adapterBinhLuan = new AdapterBinhLuan(this, dsBinhLuan);
        recyclerBinhLuan.setAdapter(adapterBinhLuan);
        presenterBinhLuan = new PresenterBinhLuan(this);
        presenterBinhLuan.layDanhSachBinhLuan(idSanPham);

        setActions();
    }

    private void AnhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        recyclerBinhLuan = (RecyclerView) findViewById(R.id.recyclerViewBinhLuan);
        edtBinhLuan = (EditText) findViewById(R.id.edtBinhLuan);
        imgGui = (ImageButton) findViewById(R.id.imgGui);
    }

    private void setActions(){
        imgBack.setOnClickListener(this);
        imgGui.setOnClickListener(this);
    }

    private void guiLai(){
        Toast.makeText(this, "Gửi lại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgGui:
                guiBinhLuan();
                break;
        }
    }

    private void guiBinhLuan() {
        String noiDung = edtBinhLuan.getText().toString();
        if (noiDung.length() == 0) return;
        else{
            BinhLuan binhLuan = new BinhLuan();
            String thoiGian = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
            binhLuan.setIdKhachHang(ManHinhTrangChuActivity.idKhachHang);
            binhLuan.setIdSanPham(idSanPham);
            binhLuan.setNoiDungBL(noiDung);
            binhLuan.setThoiGianBL(thoiGian);
            binhLuan.setTenKhachHang(ManHinhTrangChuActivity.khachHang.getTenKhachHang());
            binhLuan.setHinhKhachHang(ManHinhTrangChuActivity.khachHang.getAnhInfoKH());
            boolean b = modelKhachHang.guiBinhLuan(binhLuan);
            if(b) adapterBinhLuan.add(binhLuan);
        }
    }

    @Override
    public void hienThiDanhSachBinhLuan(List<BinhLuan> dsBinhLuan) {
        adapterBinhLuan.addAlL(dsBinhLuan);
    }
}