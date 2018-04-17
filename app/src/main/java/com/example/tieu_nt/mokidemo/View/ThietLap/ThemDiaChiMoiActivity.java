package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/13/2018.
 */

public class ThemDiaChiMoiActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diachimoi);
        anhXa();
    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
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
