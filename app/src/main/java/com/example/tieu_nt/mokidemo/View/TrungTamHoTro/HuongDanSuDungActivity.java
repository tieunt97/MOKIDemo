package com.example.tieu_nt.mokidemo.View.TrungTamHoTro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/28/2018.
 */

public class HuongDanSuDungActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton imgBack;
    private TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_huongdansdmoki);
        anhXa();
        tvTitle.setText("Hướng Dẫn Sử Dụng MOKI");
    }

    private void anhXa() {
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
        }
    }
}
