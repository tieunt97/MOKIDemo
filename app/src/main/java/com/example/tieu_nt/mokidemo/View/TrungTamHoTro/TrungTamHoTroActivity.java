package com.example.tieu_nt.mokidemo.View.TrungTamHoTro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class TrungTamHoTroActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout frameLayout;
    private RelativeLayout relaCall, relaSendMail, relaHuongDanSD, relaChat;
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
        setContentView(R.layout.layout_trungtamhotro);
        anhXa();

        //create Navigation
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav_drawer, R.string.close_nav_drawer) {
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
        adapter = new AdapterMenu(TrungTamHoTroActivity.this, 7, drawerLayout, khachHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        if(khachHang  != null && !khachHang.getAnhInfoKH().equals("null")){
            Picasso.get().load(ManHinhTrangChuActivity.SERVER + khachHang.getAnhInfoKH()).into(imgUserInfo);
        }
    }

    private void anhXa() {
        frameLayout = (FrameLayout) findViewById(R.id.themFragment);
        imgMenu = (ImageButton) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgUserInfo = (CircleImageView) findViewById(R.id.imgKhachHang);
        imgUserInfo.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        relaCall = (RelativeLayout) findViewById(R.id.relaCall);
        relaCall.setOnClickListener(this);
        relaHuongDanSD = (RelativeLayout) findViewById(R.id.relaHuongDanSD);
        relaHuongDanSD.setOnClickListener(this);
        relaChat = (RelativeLayout) findViewById(R.id.relaChat);
        relaChat.setOnClickListener(this);
        relaSendMail = (RelativeLayout) findViewById(R.id.relaSendMail);
        relaSendMail.setOnClickListener(this);
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
        switch (view.getId()) {
            case R.id.imgMenu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.relaHuongDanSD:
                Intent iHDSD = new Intent(TrungTamHoTroActivity.this, HuongDanSuDungActivity.class);
                iHDSD.putExtra("title", "Hướng Dẫn Sử Dụng MOKI");
                startActivity(iHDSD);
                break;
            case R.id.relaChat:
                break;
            case R.id.relaCall:
                Intent iCall = new Intent(Intent.ACTION_CALL);
                iCall.setData(Uri.parse("tel:0965677760"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(iCall);
                break;
            case R.id.relaSendMail:
                Intent iSendMail = new Intent(Intent.ACTION_SEND);
                iSendMail.setType("message/rfc822");
                iSendMail.putExtra(Intent.EXTRA_EMAIL  , new String[]{"tieutienyen1027@gmail.com"});
                iSendMail.putExtra(Intent.EXTRA_SUBJECT, "Hỗ trợ người dùng MOKI");
//                iSendMail.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try{
                    startActivity(iSendMail);
                }catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(TrungTamHoTroActivity.this, "Bạn cần cài đặt Gmail của Google để sử dụng chức năng này", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
