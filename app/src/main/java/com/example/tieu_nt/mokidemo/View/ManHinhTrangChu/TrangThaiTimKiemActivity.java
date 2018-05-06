package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/17/2018.
 */

public class TrangThaiTimKiemActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack;
    private RelativeLayout relaTatCa, relaMoi, relaGanNhuMoi, relaTot, relaKhaTot, relaCu;
    private ImageView imgMoi, imgGanNhuMoi, imgTot, imgKhaTot, imgCu;
    private String trangThai = "Tất cả";
    private boolean themSanPham = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangthai_timkiem);
        anhXa();

        Intent intent = getIntent();
        String trangThai = intent.getStringExtra("trangThai");
        themSanPham = intent.getBooleanExtra("themSanPham", false);
        if (themSanPham) relaTatCa.setVisibility(View.GONE);
        if (trangThai != null){
            if(trangThai.equalsIgnoreCase("Mới")) imgMoi.setVisibility(View.VISIBLE);
            else if(trangThai.equalsIgnoreCase("Gần như mới")) imgGanNhuMoi.setVisibility(View.VISIBLE);
            else if(trangThai.equalsIgnoreCase("Tốt")) imgTot.setVisibility(View.VISIBLE);
            else if(trangThai.equalsIgnoreCase("Khá tốt")) imgKhaTot.setVisibility(View.VISIBLE);
            else if(trangThai.equalsIgnoreCase("Cũ")) imgCu.setVisibility(View.VISIBLE);
        }
    }

    private void anhXa() {
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        relaTatCa = (RelativeLayout) findViewById(R.id.relaTatCa);
        relaTatCa.setOnClickListener(this);
        relaMoi = (RelativeLayout) findViewById(R.id.relaMoi);
        relaMoi.setOnClickListener(this);
        relaGanNhuMoi = (RelativeLayout) findViewById(R.id.relaGanNhuMoi);
        relaGanNhuMoi.setOnClickListener(this);
        relaTot = (RelativeLayout) findViewById(R.id.relaTot);
        relaTot.setOnClickListener(this);
        relaKhaTot = (RelativeLayout) findViewById(R.id.relaKhaTot);
        relaKhaTot.setOnClickListener(this);
        relaCu = (RelativeLayout) findViewById(R.id.relaCu);
        relaCu.setOnClickListener(this);
        imgMoi = (ImageView) findViewById(R.id.imgMoi);
        imgGanNhuMoi = (ImageView) findViewById(R.id.imgGanNhuMoi);
        imgTot = (ImageView) findViewById(R.id.imgTot);
        imgKhaTot = (ImageView) findViewById(R.id.imgKhaTot);
        imgCu = (ImageView) findViewById(R.id.imgCu);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.relaTatCa:
                trangThai = "Tất cả";
                finish();
                break;
            case R.id.relaMoi:
                trangThai = "Mới";
                finish();
                break;
            case R.id.relaTot:
                trangThai = "Tốt";
                finish();
                break;
            case R.id.relaKhaTot:
                trangThai = "Khá tốt";
                finish();
                break;
            case R.id.relaGanNhuMoi:
                trangThai = "Gần như mới";
                finish();
                break;
            case R.id.relaCu:
                trangThai = "Cũ";
                finish();
                break;
        }
    }

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("trangThai", trangThai);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
