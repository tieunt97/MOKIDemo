package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Presenter.ThietLap.PresenterDoiMatKhau;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class DoiMatKhauActivity extends AppCompatActivity implements View.OnClickListener, ViewDoiMatKhau{
    private EditText edtMatKhauCu, edtMatKhauMoi, edtXacNhanMKMoi;
    private Button btnCapNhat;
    private ImageButton imgBack;
    private PresenterDoiMatKhau presenterDoiMatKhau;
    private KhachHang khachHang;
    private AlertDialog.Builder builder;
    private ModelKhachHang modelKhachHang;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_doimatkhau);
        anhXa();
        khachHang = (KhachHang) getIntent().getSerializableExtra("khachHang");
        modelKhachHang = ModelKhachHang.getInstance();
        presenterDoiMatKhau = new PresenterDoiMatKhau(this);
    }

    private void anhXa() {
        edtMatKhauCu = (EditText) findViewById(R.id.edtMatKhauCu);
        edtMatKhauMoi = (EditText) findViewById(R.id.edtMatKhauMoi);
        edtXacNhanMKMoi = (EditText) findViewById(R.id.edtXacNhanMKMoi);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        btnCapNhat.setOnClickListener(this);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnCapNhat:
                presenterDoiMatKhau.doiMatKhau(khachHang.getTaiKhoan().getMatKhau(), edtMatKhauCu.getText().toString(),
                        edtMatKhauMoi.getText().toString(), edtXacNhanMKMoi.getText().toString());
                break;
        }
    }

    @Override
    public void doiMatKhauThanhCong(String matKhauMoi) {
        boolean b = modelKhachHang.doiMatKhau("doiMatKhau", khachHang.getIdKhachHang(), matKhauMoi);
        if (b){
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void doiMatKhauThatBai(String msg) {
        builder = new AlertDialog.Builder(DoiMatKhauActivity.this);
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
    }
}
