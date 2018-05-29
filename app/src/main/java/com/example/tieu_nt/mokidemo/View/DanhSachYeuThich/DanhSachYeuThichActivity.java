package com.example.tieu_nt.mokidemo.View.DanhSachYeuThich;

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
import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamYeuThich;
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.DanhSachYeuThich.PresenterDanhSachYeuThich;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.MainActivity;
import com.example.tieu_nt.mokidemo.View.TrangChu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class DanhSachYeuThichActivity extends MainActivity implements View.OnClickListener, ViewDSSanPhamYeuThich{
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView, recyclerViewSPYeuThich;
    private AdapterMenu adapter;
    private CircleImageView imgUserInfo;
    private ImageButton imgMenu;
    private TextView tvTitle;
    private AdapterSanPhamYeuThich adapterSanPhamYeuThich;
    private PresenterDanhSachYeuThich presenterDanhSachYeuThich;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tintuc_dsyeuthich);
        anhXa();

        tvTitle.setText("Danh sách yêu thích");

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
        adapter = new AdapterMenu(DanhSachYeuThichActivity.this, 2, drawerLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if(DangNhap.getInstance().getKhachHang()  != null && !DangNhap.getInstance().getKhachHang().getAnhInfoKH().equals("null")){
            Picasso.get().load(TrangChuActivity.SERVER + DangNhap.getInstance().getKhachHang().getAnhInfoKH()).into(imgUserInfo);
        }

        presenterDanhSachYeuThich = new PresenterDanhSachYeuThich(this);
        presenterDanhSachYeuThich.layDSSanPhamYeuThich("layDSSanPhamYeuThich", DangNhap.getInstance().getKhachHang().getIdKhachHang(), 0);
    }

    private void anhXa(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        frameLayout = (FrameLayout) findViewById(R.id.themFragment);
        imgMenu = (ImageButton) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgUserInfo = (CircleImageView) findViewById(R.id.imgKhachHang);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewSPYeuThich = (RecyclerView) findViewById(R.id.recyclerViewTinTuc);
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
    public void hienThiDSSanPham(List<SanPham> dsSanPham) {
        adapterSanPhamYeuThich = new AdapterSanPhamYeuThich(DanhSachYeuThichActivity.this, dsSanPham);
        recyclerViewSPYeuThich.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewSPYeuThich.setHasFixedSize(true);
        recyclerViewSPYeuThich.setAdapter(adapterSanPhamYeuThich);
        adapterSanPhamYeuThich.notifyDataSetChanged();
    }
}
