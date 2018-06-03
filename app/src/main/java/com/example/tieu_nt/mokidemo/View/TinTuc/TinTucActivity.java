package com.example.tieu_nt.mokidemo.View.TinTuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Adapter.AdapterTinTuc;
import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.Model.TinTuc;
import com.example.tieu_nt.mokidemo.Presenter.TinTuc.PresenterLogicTinTuc;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class TinTucActivity extends MainActivity implements View.OnClickListener, ViewHienThiDanhSachTinTuc{
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView, recyclerViewTinTuc;
    private AdapterMenu adapter;
    private CircleImageView imgKhachHang;
    private TextView tvTenKhachHang;
    private ImageButton imgMenu;
    private AdapterTinTuc adapterTinTuc;
    private PresenterLogicTinTuc presenterTinTuc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tintuc_dsyeuthich);
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

        //set viewpager
        adapter = new AdapterMenu(TinTucActivity.this, 1, drawerLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if (DangNhap.getInstance().getKhachHang() != null){
            tvTenKhachHang.setText(DangNhap.getInstance().getKhachHang().getTenKhachHang());
            if(!DangNhap.getInstance().getKhachHang().getAnhInfoKH().equals("null"))
                Picasso.get().load(Constants.SERVER + DangNhap.getInstance().getKhachHang().getAnhInfoKH()).into(imgKhachHang);
        }

        recyclerViewTinTuc.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTinTuc.setHasFixedSize(true);
        presenterTinTuc = new PresenterLogicTinTuc(this);
        presenterTinTuc.layDanhSachTinTuc(0);
    }

    private void anhXa(){
        frameLayout = (FrameLayout) findViewById(R.id.themFragment);
        imgMenu = (ImageButton) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgKhachHang = (CircleImageView) findViewById(R.id.imgKhachHang);
        tvTenKhachHang = (TextView) findViewById(R.id.tvTenKhachHang);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewTinTuc = (RecyclerView) findViewById(R.id.recyclerViewTinTuc);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgMenu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void hienThiDanhSachTinTuc(List<TinTuc> dsTinTuc) {
        adapterTinTuc = new AdapterTinTuc(TinTucActivity.this, dsTinTuc);
        recyclerViewTinTuc.setAdapter(adapterTinTuc);
        adapterTinTuc.notifyDataSetChanged();
    }
}
