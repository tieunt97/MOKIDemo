package com.example.tieu_nt.mokidemo.View.ManHinhDangNhap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.ManHinhTrangChuActivity;

/**
 * Created by tieu_nt on 2/2/2018.
 */

public class ManHinhDangKyActivity extends AppCompatActivity {

    private Button btnDaCoTK, btnBoQua, btnDangKy;
    private EditText edtSDT, edtMatKhau;
    private TextView txtDieuKhoanDK, tvMOKI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdangky_layout);
        AnhXa();
        setStyleText();
        setActionEdt();

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Register())
                {
                 Intent iXacNhan = new Intent(ManHinhDangKyActivity.this, XacNhanDangKyActivity.class);
                 startActivity(iXacNhan);
                }

            }
        });

        btnDaCoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManHinhDangKyActivity.this, ManHinhDangNhapActivity.class);
                startActivity(intent);
//                finish();
            }
        });

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrangChu = new Intent(ManHinhDangKyActivity.this, ManHinhTrangChuActivity.class);
                startActivity(intentTrangChu);
            }
        });

//        edtSDT.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if(charSequence.length() > 0){
//                    if(charSequence.toString().indexOf("0") != 0){
//                        edtSDT.setText("");
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

    }

    private void setActionEdt(){
        edtMatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 5 && edtSDT.getText().toString().length() > 9) {
                    btnDangKy.setBackgroundResource(R.drawable.custom_button_dangnhap);
                }
                if(charSequence.length() < 6){
                    btnDangKy.setBackgroundResource(R.drawable.custom_button_dangky);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtSDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 0){
                    if(charSequence.toString().indexOf("0") != 0){
                        edtSDT.setText("");
                    }
                }
                if(charSequence.length() > 9 && edtMatKhau.getText().toString().length() > 5) {
                    btnDangKy.setBackgroundResource(R.drawable.custom_button_dangnhap);
                }
                if(charSequence.length() < 10){
                    btnDangKy.setBackgroundResource(R.drawable.custom_button_dangky);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private boolean Register(){
        String sdt = edtSDT.getText().toString();
        String matKhau = edtMatKhau.getText().toString();
        String msg = "";
        if(matKhau.equals("") && sdt.equals(""))
            msg = "Bạn chưa nhập Số điện thoại và Mật khẩu";
        else if(sdt.equals("")) msg = "Bạn chưa nhập Số điện thoại";
        else if(matKhau.equals("")) msg = "Bạn chưa nhập Mật khẩu";
        else if(sdt.indexOf("0") != 0 || sdt.length() < 10)
            msg = "Số điện thoại không đúng";
        else if(matKhau.length() < 6)
            msg = "Mật khẩu không hợp lệ, ít nhất 6 ký tự";

        if(!msg.equals("")){
            final AlertDialog.Builder builder = new AlertDialog.Builder(ManHinhDangKyActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_thongbao_dangnhap, null, false);
            TextView tvNoiDung = (TextView) view.findViewById(R.id.tvNoiDung);
            tvNoiDung.setText(msg);
            Button btnDong = (Button) view.findViewById(R.id.btnDong);

            builder.setView(view);
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            btnDong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

            //đóng sau 3s
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        if(alertDialog.isShowing())
                            alertDialog.dismiss();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();

            return false;
        }


        return true;
    }

    private void setStyleText(){
        String text = "<u>" + txtDieuKhoanDK.getText().toString() + "</u>";
        txtDieuKhoanDK.setText(Html.fromHtml(text));
        String textMOKI = "<b>" + "MOKI" + "</b>" + " cần được kích hoạt bằng số điện thoại";
        tvMOKI.setText(Html.fromHtml(textMOKI));
    }

    private void AnhXa(){
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnDaCoTK = (Button) findViewById(R.id.btnDaCoTK);
        btnBoQua = (Button) findViewById(R.id.btnBoQua);
        edtSDT = (EditText) findViewById(R.id.edtSoDT);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        txtDieuKhoanDK = (TextView) findViewById(R.id.txtDieuKhoanDK);
        tvMOKI = (TextView) findViewById(R.id.tvMOKI);
    }
}
