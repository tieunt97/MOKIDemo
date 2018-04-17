package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/13/2018.
 */

public class DiaChiActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack;
    private Button btnThemDiaChiMoi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diachi);
        anhXa();
    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        btnThemDiaChiMoi = (Button) findViewById(R.id.btnThemDiaChi);
        btnThemDiaChiMoi.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnThemDiaChi:
                Intent iThemDiaChiMoi = new Intent(DiaChiActivity.this, ThemDiaChiMoiActivity.class);
                startActivity(iThemDiaChiMoi);
                break;
        }
    }
}
