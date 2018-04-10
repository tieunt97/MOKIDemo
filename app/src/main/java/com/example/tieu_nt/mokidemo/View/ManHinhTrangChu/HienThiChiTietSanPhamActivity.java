package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/7/2018.
 */

public class HienThiChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener{
    private SanPham sanPham;
    private TextView tvTenSP;
    private ImageButton imgBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        anhXa();
        Intent intent = getIntent();
        sanPham = (SanPham) intent.getSerializableExtra("sanPham");
        tvTenSP.setText(sanPham.getTenSanPham());
    }

    private void anhXa(){
        tvTenSP = (TextView) findViewById(R.id.tvTenSP);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
