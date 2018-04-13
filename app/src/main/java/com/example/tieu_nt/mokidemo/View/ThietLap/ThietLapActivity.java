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
import com.example.tieu_nt.mokidemo.Model.TrangChu.MySingleton;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

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
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private ImageButton imgMenu;

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


        adapter = new AdapterMenu(ThietLapActivity.this, 6, drawerLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
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
        imgUserInfo = (CircleImageView) findViewById(R.id.imgUserInfo);
        imgUserInfo.setOnClickListener(this);
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
            case R.id.imgUserInfo:
                selectImage();
                break;
            case R.id.relaTrangCuaToi:
                Intent iTrangCuaToi = new Intent(ThietLapActivity.this, TrangCuaToiActivity.class);
                startActivity(iTrangCuaToi);
                break;
            case R.id.relaDiaChi:
                Intent iDiaChi = new Intent(ThietLapActivity.this, DiaChiActivity.class);
                startActivity(iDiaChi);
                break;
            case R.id.relaDoiMatKhau:
                Intent iDoiMK = new Intent(ThietLapActivity.this, DoiMatKhauActivity.class);
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
                    Toast.makeText(ThietLapActivity.this, Response, Toast.LENGTH_SHORT).show();
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

        MySingleton.getInstance(ThietLapActivity.this).addToRequestQue(stringRequest);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

}
