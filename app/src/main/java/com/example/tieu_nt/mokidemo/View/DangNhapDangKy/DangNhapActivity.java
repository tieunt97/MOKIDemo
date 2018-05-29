package com.example.tieu_nt.mokidemo.View.DangNhapDangKy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy.PresenterLogicDangNhap;
import com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy.PresenterTaiKhoan;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.TrangChuActivity;

/**
 * Created by tieu_nt on 2/2/2018.
 */

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener, ViewDangNhap{

    private Button btnDangNhap, btnDangKy, btnBoQua;
    private EditText edtSoDT, edtMatKhau;
    private TextView txtQuenMK;
    private PresenterLogicDangNhap presenterLogicDangNhap;
    private AlertDialog.Builder builder;
    private PresenterTaiKhoan presenterTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manhinhdangnhap_layout);
        AnhXa();
        setUnderLine();

        presenterTaiKhoan = new PresenterTaiKhoan(this);

        presenterLogicDangNhap = new PresenterLogicDangNhap(this);

        edtSoDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 0){
                    if(charSequence.toString().indexOf("0") != 0){
                        edtSoDT.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String soDT = edtSoDT.getText().toString().trim();
                String matKhau = edtMatKhau.getText().toString().trim();
                presenterLogicDangNhap.kiemTraDangNhap(soDT, matKhau);
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intentDangKy);
            }
        });

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrangChu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                startActivity(intentTrangChu);
            }
        });

    }

    private void AnhXa(){
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnBoQua = (Button) findViewById(R.id.btnBoQua);
        edtSoDT = (EditText) findViewById(R.id.edtSoDT);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        txtQuenMK = (TextView) findViewById(R.id.txtQuenMK);
        txtQuenMK.setOnClickListener(this);
    }

    private void setUnderLine(){
        String text = "<u>" + txtQuenMK.getText().toString() + "</u>";
        txtQuenMK.setText(Html.fromHtml(text));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtQuenMK:
                Intent intent = new Intent(DangNhapActivity.this, QuenMatKhauActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void dangNhapThanhCong(KhachHang khachHang) {
        presenterTaiKhoan.themTaiKhoan(khachHang.getTaiKhoan());
        DangNhap.getInstance().setKhachHang(khachHang);
        Intent iTrangChu = new Intent(DangNhapActivity.this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void dangNhapThatBai(String msg) {
        builder = new AlertDialog.Builder(DangNhapActivity.this);
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
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(alertDialog.isShowing()){
                    alertDialog.dismiss();
                }
            }
        };

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 3000);
    }
}
