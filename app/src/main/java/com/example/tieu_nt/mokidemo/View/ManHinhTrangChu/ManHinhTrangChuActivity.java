package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapter;
import com.example.tieu_nt.mokidemo.Model.DrawerItem;
import com.example.tieu_nt.mokidemo.Model.TrangChu.MySingleton;
import com.example.tieu_nt.mokidemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 2/6/2018.
 */

public class ManHinhTrangChuActivity extends AppCompatActivity implements View.OnClickListener{
    public static String SERVER = "http://192.168.1.110:8080/webmoki";
    public static String SERVER_NAME = "http://192.168.1.110:8080/webmoki/laydssanpham.php";
//    public static String SERVER_NAME = "http://10.11.203.188:8080/webmoki";
    private String uploadUrl = "http://192.168.1.110:8080/webmoki/dangnhap_dangky.php?ham=updateImgUserInfo";

    private FrameLayout trangChu;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private AdapterMenu adapter;
    private List<DrawerItem> dsItems = new ArrayList<>();
    private String[] tenItems = {"Trang chủ", "Tin tức", "Danh sách yêu thích", "Danh sách bán", "Danh sách mua",
        "Từ thiện", "Thiết lập", "Trung tâm hỗ trợ", "Giới thiệu MOKI", "Đăng xuất"};
    private int[] hinhItems = {R.drawable.home, R.drawable.newspaper, R.drawable.like, R.drawable.clipboards,
        R.drawable.shopping_cart, R.drawable.charity, R.drawable.settings, R.drawable.mail,
        R.drawable.info, R.drawable.logout};
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewFlipper viewFlipper;
    private Button btnSapXep, btnLoc, btnXung;
    private CircleImageView imgUserInfo;
    private FloatingActionButton fab;
    private int currentPos = 0;
    private float x1, x2;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);
        anhXa();

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
        imgUserInfo = (CircleImageView) findViewById(R.id.imgUserInfo);
        imgUserInfo.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

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
            case R.id.imgUserInfo:
                selectImage();
                break;
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

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgUserInfo.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(ManHinhTrangChuActivity.this, Response, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", "1ImgUserInfo");
                params.put("image", imageToString(bitmap));

                return params;
            }
        };

        MySingleton.getInstance(ManHinhTrangChuActivity.this).addToRequestQue(stringRequest);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
}
