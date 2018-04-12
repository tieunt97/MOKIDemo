package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class DoiMatKhauActivity extends AppCompatActivity{
    private EditText edtMatKhauCu, edtMatKhauMoi, edtXacNhanMKMoi;
    private Button btnCapNhat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_doimatkhau);
        anhXa();
    }

    private void anhXa() {
        edtMatKhauCu = (EditText) findViewById(R.id.edtMatKhauCu);
        edtMatKhauMoi = (EditText) findViewById(R.id.edtMatKhauMoi);
        edtXacNhanMKMoi = (EditText) findViewById(R.id.edtXacNhanMKMoi);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
    }
}
