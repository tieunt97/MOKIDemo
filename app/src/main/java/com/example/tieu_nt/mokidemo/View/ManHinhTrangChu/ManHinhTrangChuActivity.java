package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapter;
import com.example.tieu_nt.mokidemo.Model.DrawerItem;
import com.example.tieu_nt.mokidemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 2/6/2018.
 */

public class ManHinhTrangChuActivity extends AppCompatActivity implements View.OnClickListener{
    public static String SERVER = "http://192.168.1.110:8080/webmoki";
    public static String SERVER_NAME = "http://10.11.203.188:8080/webmoki";
//    public static String SERVER = "http://10.11.203.188:8080/webmoki";
    private FrameLayout trangChu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
//    NavigationView nav_view;
    Toolbar toolbar;
    RecyclerView recyclerView;
    AdapterMenu adapter;
    List<DrawerItem> dsItems = new ArrayList<>();
    String[] tenItems = {"Trang chủ", "Tin tức", "Danh sách yêu thích", "Danh sách bán", "Danh sách mua",
        "Từ thiện", "Thiết lập", "Trung tâm hỗ trợ", "Giới thiệu MOKI", "Đăng xuất"};
    int[] hinhItems = {R.drawable.home, R.drawable.newspaper, R.drawable.like, R.drawable.clipboards,
        R.drawable.shopping_cart, R.drawable.charity, R.drawable.settings, R.drawable.mail,
        R.drawable.info, R.drawable.logout};
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewFlipper viewFlipper;
    Button btnSapXep, btnLoc, btnXung;
    FloatingActionButton fab;
    int currentPos = 0;
    float x1, x2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        anhXa();
//        setIconButtons();

        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //create Navigation
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_nav_drawer, R.string.close_nav_drawer){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                trangChu.setTranslationX(slideOffset * drawerView.getWidth());
            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //
        for (int i = 0; i < tenItems.length; i++){
            dsItems.add(new DrawerItem(hinhItems[i], tenItems[i]));
        }
        adapter = new AdapterMenu(ManHinhTrangChuActivity.this, dsItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        viewFlipper.setInAnimation(this, android.R.anim.fade_in);
        viewFlipper.setOutAnimation(this, android.R.anim.fade_out);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);

        //set viewPager
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_trangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void anhXa(){
        trangChu = (FrameLayout) findViewById(R.id.themFragment);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        btnSapXep = (Button) findViewById(R.id.btnSapXep);
        btnSapXep.setOnClickListener(this);
        btnLoc = (Button) findViewById(R.id.btnLoc);
        btnLoc.setOnClickListener(this);
        btnXung = (Button) findViewById(R.id.btnXung);
        btnXung.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

//    private void setIconButtons(){
//        Drawable icon= this.getResources().getDrawable( R.drawable.sort);
//        btnSapXep.setCompoundDrawablesWithIntrinsicBounds( icon, null, null, null );
//        icon = this.getResources().getDrawable(R.drawable.filter);
//        btnLoc.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
//        icon = this.getResources().getDrawable(R.drawable.sweep);
//        btnXung.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
//    }

    //action view flipper
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                if(x2 > x1){
                    viewFlipper.showPrevious();
                }else{
                    viewFlipper.showNext();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSapXep:
                Toast.makeText(ManHinhTrangChuActivity.this, "Sắp xếp", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnLoc:
                Toast.makeText(ManHinhTrangChuActivity.this, "Lọc", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnXung:
                Toast.makeText(ManHinhTrangChuActivity.this, "Xung", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab:
                Intent intent = new Intent(ManHinhTrangChuActivity.this, CameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}
