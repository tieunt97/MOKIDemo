package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.TrangChu.MySingleton;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class ThietLapActivity extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout frameLayout;
    private RelativeLayout relaTrangCuaToi, relaDiaChi, relaDoiMatKhau, relaDSChan, relaTaiKhoan, relaCaiDatThongBao;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private AdapterMenu adapter;
    private CircleImageView imgUserInfo;
    private ImageButton imgMenu;
    private KhachHang khachHang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thietlap);
        anhXa();

        //create Navigation
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav_drawer, R.string.close_nav_drawer){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                frameLayout.setTranslationX(slideOffset * drawerView.getWidth());
            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        khachHang = (KhachHang) getIntent().getSerializableExtra("khachHang");
        adapter = new AdapterMenu(ThietLapActivity.this, 6, drawerLayout, khachHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if(khachHang  != null && !khachHang.getAnhInfoKH().equals("null")){
            Picasso.get().load(ManHinhTrangChuActivity.SERVER + khachHang.getAnhInfoKH()).into(imgUserInfo);
        }
    }

    private void anhXa(){
        frameLayout = (FrameLayout) findViewById(R.id.themFragment);
        relaTrangCuaToi = (RelativeLayout) findViewById(R.id.relaTrangCuaToi);
        relaTrangCuaToi.setOnClickListener(this);
        relaDiaChi = (RelativeLayout) findViewById(R.id.relaDiaChi);
        relaDiaChi.setOnClickListener(this);
        relaDoiMatKhau = (RelativeLayout) findViewById(R.id.relaDoiMatKhau);
        relaDoiMatKhau.setOnClickListener(this);
        relaDSChan = (RelativeLayout) findViewById(R.id.relaDSChan);
        relaDSChan.setOnClickListener(this);
        relaTaiKhoan = (RelativeLayout) findViewById(R.id.relaTaiKhoan);
        relaTaiKhoan.setOnClickListener(this);
        relaCaiDatThongBao = (RelativeLayout) findViewById(R.id.relaCaiDatThongBao);
        relaCaiDatThongBao.setOnClickListener(this);
        imgMenu = (ImageButton) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgUserInfo = (CircleImageView) findViewById(R.id.imgKhachHang);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgMenu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.relaTrangCuaToi:
                Intent iTrangCuaToi = new Intent(ThietLapActivity.this, TrangCuaToiActivity.class);
                iTrangCuaToi.putExtra("khachHang", khachHang);
                startActivity(iTrangCuaToi);
                break;
            case R.id.relaDiaChi:
                Intent iDiaChi = new Intent(ThietLapActivity.this, DiaChiActivity.class);
                iDiaChi.putExtra("khachHang", khachHang);
                startActivity(iDiaChi);
                break;
            case R.id.relaDoiMatKhau:
                Intent iDoiMK = new Intent(ThietLapActivity.this, DoiMatKhauActivity.class);
                iDoiMK.putExtra("khachHang", khachHang);
                startActivity(iDoiMK);
                break;
            case R.id.relaDSChan:
                break;
            case R.id.relaTaiKhoan:
                Intent iTaiKhoan = new Intent(ThietLapActivity.this, TaiKhoanActivity.class);
                startActivity(iTaiKhoan);
                break;
            case R.id.relaCaiDatThongBao:
                Intent iCaiDatThongBao = new Intent(ThietLapActivity.this, CaiDatThongBaoActivity.class);
                startActivity(iCaiDatThongBao);
                break;
        }
    }
}
