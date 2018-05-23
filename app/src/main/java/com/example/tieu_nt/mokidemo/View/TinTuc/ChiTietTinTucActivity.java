package com.example.tieu_nt.mokidemo.View.TinTuc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.Model.TinTuc;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class ChiTietTinTucActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack;
    private TextView tvTieuDe, tvNgayDang, tvNoiDung;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitiettintuc);
        anhXa();
        setActions();
        hienThiTinTuc();
    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        tvTieuDe = (TextView) findViewById(R.id.tvTieuDe);
        tvNgayDang = (TextView) findViewById(R.id.tvNgayDang);
        tvNoiDung = (TextView) findViewById(R.id.tvNoiDung);
    }

    private void setActions(){
        imgBack.setOnClickListener(this);
    }

    private void hienThiTinTuc(){
        TinTuc tinTuc = (TinTuc) getIntent().getSerializableExtra("tinTuc");
        tvTieuDe.setText(tinTuc.getTieuDe());
        String ngayDang = "Ngày " + tinTuc.getNgayDang()[2] + " Tháng " + tinTuc.getNgayDang()[1] + " Năm" + tinTuc.getNgayDang()[0];
        tvNgayDang.setText(ngayDang);
        tvNoiDung.setText(tinTuc.getNoiDung());
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
