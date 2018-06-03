package com.example.tieu_nt.mokidemo.View.TuThien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapterTrangChu;
import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class TuThienActivity extends MainActivity implements View.OnClickListener{
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private AdapterMenu adapter;
    private CircleImageView imgUserInfo;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageButton imgMenu;
    private ViewPagerAdapterTrangChu viewPagerAdapter;
    private List<Fragment> list = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tuthien_dsmua_dsban);
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
        adapter = new AdapterMenu(TuThienActivity.this, 5, drawerLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        viewPagerAdapter = new ViewPagerAdapterTrangChu(getSupportFragmentManager(), list, titles);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        if(DangNhap.getInstance().getKhachHang()  != null && !DangNhap.getInstance().getKhachHang().getAnhInfoKH().equals("null")){
            Picasso.get().load(Constants.SERVER + DangNhap.getInstance().getKhachHang().getAnhInfoKH()).into(imgUserInfo);
        }
    }

    private void anhXa(){
        frameLayout = (FrameLayout) findViewById(R.id.themFragment);
        imgMenu = (ImageButton) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgUserInfo = (CircleImageView) findViewById(R.id.imgKhachHang);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgMenu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }
}
