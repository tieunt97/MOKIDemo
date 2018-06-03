package com.example.tieu_nt.mokidemo.View.GioiThieuMOKI;

import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.MainActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class GioiThieuMOKIActivity extends MainActivity implements View.OnClickListener{
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;
    private AdapterMenu adapter;
    private CircleImageView imgKhachHang;
    private TextView tvTenKhachHang;
    private ImageButton imgMenu;
    private RelativeLayout relaShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gioithieumoki);
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

        adapter = new AdapterMenu(GioiThieuMOKIActivity.this, 7, drawerLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if (DangNhap.getInstance().getKhachHang() != null){
            tvTenKhachHang.setText(DangNhap.getInstance().getKhachHang().getTenKhachHang());
            if(!DangNhap.getInstance().getKhachHang().getAnhInfoKH().equals("null"))
                Picasso.get().load(Constants.SERVER + DangNhap.getInstance().getKhachHang().getAnhInfoKH()).into(imgKhachHang);
        }

        relaShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String body = "Cùng MOKI kết nối những bà mẹ";
                String sub = "Ứng dụng di động MOKI";
                intent.putExtra(Intent.EXTRA_SUBJECT, sub);
                intent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intent, "Chia sẻ MOKi với"));
            }
        });
    }

    private void anhXa(){
        frameLayout = (FrameLayout) findViewById(R.id.themFragment);
        imgMenu = (ImageButton) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgKhachHang = (CircleImageView) findViewById(R.id.imgKhachHang);
        tvTenKhachHang = (TextView) findViewById(R.id.tvTenKhachHang);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        relaShare = (RelativeLayout) findViewById(R.id.relaShare);
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
