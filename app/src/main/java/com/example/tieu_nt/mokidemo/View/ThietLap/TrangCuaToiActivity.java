package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/13/2018.
 */

public class TrangCuaToiActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack;
    private EditText edtThongTin;
    private TextView tvSoKyTu, tvXong;
    private RelativeLayout relaXong;
    private boolean visible = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangcuatoi);
        anhXa();

        edtThongTin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvSoKyTu.setText(charSequence.length() + "/1000");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        edtThongTin = (EditText) findViewById(R.id.edtThongTin);
        edtThongTin.setOnClickListener(this);
        relaXong = (RelativeLayout) findViewById(R.id.relaXong);
        tvSoKyTu = (TextView) findViewById(R.id.tvSoKyTu);
        tvXong = (TextView) findViewById(R.id.tvXong);
        tvXong.setOnClickListener(this);
        edtThongTin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!visible){
                    visible = !visible;
                    relaXong.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.edtThongTin:
                if(!visible){
                    visible = !visible;
                    relaXong.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tvXong:
                //ẩn bàn phím nhập
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(edtThongTin.getWindowToken(), 0);
                visible = !visible;
                relaXong.setVisibility(View.GONE);
                break;

        }
    }
}