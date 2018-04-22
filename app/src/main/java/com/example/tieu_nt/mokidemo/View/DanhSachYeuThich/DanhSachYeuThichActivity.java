package com.example.tieu_nt.mokidemo.View.DanhSachYeuThich;

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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tieu_nt.mokidemo.Adapter.AdapterMenu;
import com.example.tieu_nt.mokidemo.Adapter.AdapterSanPhamYeuThich;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.TrangChu.MySingleton;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/11/2018.
 */

public class DanhSachYeuThichActivity extends AppCompatActivity implements View.OnClickListener{
    private FrameLayout frameLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView, recyclerViewTinTuc;
    private AdapterMenu adapter;
    private CircleImageView imgUserInfo;
    private ImageButton imgMenu;
    private TextView tvTitle;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private AdapterSanPhamYeuThich adapterSanPhamYeuThich;
    private KhachHang khachHang;


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
        khachHang = (KhachHang) getIntent().getSerializableExtra("khachHang");
        adapter = new AdapterMenu(DanhSachYeuThichActivity.this, 2, drawerLayout, khachHang);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapterSanPhamYeuThich = new AdapterSanPhamYeuThich(DanhSachYeuThichActivity.this, new ArrayList<SanPham>());
        recyclerViewTinTuc.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTinTuc.setHasFixedSize(true);
        recyclerViewTinTuc.setAdapter(adapterSanPhamYeuThich);

    }

    private void anhXa(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        frameLayout = (FrameLayout) findViewById(R.id.themFragment);
        imgMenu = (ImageButton) findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        imgUserInfo = (CircleImageView) findViewById(R.id.imgKhachHang);
        imgUserInfo.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewTinTuc = (RecyclerView) findViewById(R.id.recyclerViewTinTuc);
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
            case R.id.imgKhachHang:
                selectImage();
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, ManHinhTrangChuActivity.uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(DanhSachYeuThichActivity.this, Response, Toast.LENGTH_SHORT).show();
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

        MySingleton.getInstance(DanhSachYeuThichActivity.this).addToRequestQue(stringRequest);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }
}
