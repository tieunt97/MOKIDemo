package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/14/2018.
 */

public class BinhLuanActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack;
    private RecyclerView recyclerBinhLuan;
    private EditText edtBinhLuan;
    private ImageButton imgGui;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_binhluan);


        AnhXa();
        setActions();
    }

    private void AnhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        recyclerBinhLuan = (RecyclerView) findViewById(R.id.recyclerViewBinhLuan);
        edtBinhLuan = (EditText) findViewById(R.id.edtBinhLuan);
        imgGui = (ImageButton) findViewById(R.id.imgGui);
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