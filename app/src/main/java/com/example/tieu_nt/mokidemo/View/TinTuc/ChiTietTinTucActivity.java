package com.example.tieu_nt.mokidemo.View.TinTuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class ChiTietTinTucActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiettintuc);


        AnhXa();
        setActions();
    }

    private void AnhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
    }

    private void setActions(){
        imgBack.setOnClickListener(this);
    }

    private void guiLai(){
        Toast.makeText(this, "Gửi lại", Toast.LENGTH_SHORT).show();
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
