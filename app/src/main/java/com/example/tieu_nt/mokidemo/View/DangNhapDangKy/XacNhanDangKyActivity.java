package com.example.tieu_nt.mokidemo.View.DangNhapDangKy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Model.Data.ModelDangNhapDangKy;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 2/9/2018.
 */

public class XacNhanDangKyActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{
    private EditText edt1, edt2, edt3, edt4;
    private Button btnGuiLai;
    private ImageButton imgBack;
    private TaiKhoan taiKhoan;
    private ModelDangNhapDangKy modelDangNhap_dangKy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xacnhandangky_layout);
        Intent intent = getIntent();
        taiKhoan = (TaiKhoan) intent.getSerializableExtra("taiKhoan");
        modelDangNhap_dangKy = ModelDangNhapDangKy.getInstance();
        AnhXa();
        setActions();
    }

    private void AnhXa(){
        edt1 = (EditText) findViewById(R.id.edt1);
        edt2 = (EditText) findViewById(R.id.edt2);
        edt3 = (EditText) findViewById(R.id.edt3);
        edt4 = (EditText) findViewById(R.id.edt4);
        btnGuiLai = (Button) findViewById(R.id.btnGuiLai);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
    }

    private void setActions(){
        btnGuiLai.setOnClickListener(this);
        imgBack.setOnClickListener(this);
        edt1.addTextChangedListener(this);
        edt2.addTextChangedListener(this);
        edt3.addTextChangedListener(this);
        edt4.addTextChangedListener(this);
    }

    private void guiLai(){
        Toast.makeText(this, "Gửi lại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnGuiLai:
                guiLai();
                break;
            case R.id.imgBack:
                finish();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(edt1.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            if(edt2.getText().length() > 0 && edt3.getText().length() > 0 && edt4.getText().length() > 0){
                xacNhan();
            }else{
                edt2.requestFocus();
            }
        }
        if(edt2.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            if(edt1.getText().length() > 0 && edt3.getText().length() > 0 && edt4.getText().length() > 0){
                xacNhan();
            }else{
                edt3.requestFocus();
            }
        }
        if(edt3.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            if(edt1.getText().length() > 0 && edt2.getText().length() > 0 && edt4.getText().length() > 0){
                xacNhan();
            }else{
                edt4.requestFocus();
            }
        }
        if(edt4.getText().hashCode() == charSequence.hashCode() && charSequence.length() > 0){
            if(edt1.getText().length() == 0) edt1.requestFocus();
            else if(edt2.getText().length() == 0) edt2.requestFocus();
            else if(edt3.getText().length() == 0) edt3.requestFocus();
            else if(edt1.getText().length() > 0 && edt2.getText().length() > 0 && edt3.getText().length() > 0){
                xacNhan();
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    private void xacNhan(){
        int maXacNhan = Integer.parseInt(edt1.getText().toString() + edt2.getText().toString()
                + edt3.getText().toString() + edt4.getText().toString());
        if(maXacNhan == taiKhoan.getMaXacNhan()) {
            boolean b = modelDangNhap_dangKy.xacNhanDangKy("xacNhanDangKy", taiKhoan.getSoDT());
            if(b){
                taiKhoan.setTrangThai(1);
                final AlertDialog.Builder builder = new AlertDialog.Builder(XacNhanDangKyActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_thongbao_dangnhap, null, false);
                ImageView imgDialog = (ImageView) view.findViewById(R.id.imgDialog);
                imgDialog.setImageDrawable(getResources().getDrawable(R.drawable.success));
                TextView tvNoiDung = (TextView) view.findViewById(R.id.tvNoiDung);
                tvNoiDung.setText("Tài khoản đã được kích hoạt");
                Button btnDong = (Button) view.findViewById(R.id.btnDong);

                builder.setView(view);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                btnDong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        Intent iDangNhap = new Intent(XacNhanDangKyActivity.this, DangNhapActivity.class);
                        iDangNhap.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(iDangNhap);
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
                            Intent iDangNhap = new Intent(XacNhanDangKyActivity.this, DangNhapActivity.class);
                            iDangNhap.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(iDangNhap);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        }
        else {
            edt1.setText("");
            edt2.setText("");
            edt3.setText("");
            edt4.setText("");
            edt1.requestFocus();

            final AlertDialog.Builder builder = new AlertDialog.Builder(XacNhanDangKyActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_thongbao_dangnhap, null, false);
            TextView tvNoiDung = (TextView) view.findViewById(R.id.tvNoiDung);
            tvNoiDung.setText("Mã xác nhận không đúng");
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
        }
    }
}
