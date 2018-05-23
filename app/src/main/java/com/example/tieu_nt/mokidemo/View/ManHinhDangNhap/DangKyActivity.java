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

import com.example.tieu_nt.mokidemo.Model.TaiKhoan;
import com.example.tieu_nt.mokidemo.Presenter.DangNhapDangKy.PresenterLogicDangKy;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.TrangChu.TrangChuActivity;
import com.example.tieu_nt.mokidemo.View.TrungTamHoTro.HuongDanSuDungActivity;

/**
 * Created by tieu_nt on 2/2/2018.
 */

public class DangKyActivity extends AppCompatActivity implements ViewDangKy{
    private PresenterLogicDangKy presenterLogicDangKy;
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

        presenterLogicDangKy = new PresenterLogicDangKy(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt = edtSDT.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                if(kiemTraDuLieuHopLe(sdt, matKhau)) {
                    presenterLogicDangKy.dangKyTaiKhoan(sdt, matKhau);
                }

            }
        });

        btnDaCoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iDangNhap = new Intent(DangKyActivity.this, DangNhapActivity.class);
                iDangNhap.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(iDangNhap);
            }
        });

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrangChu = new Intent(DangKyActivity.this, TrangChuActivity.class);
                startActivity(intentTrangChu);
            }
        });

        txtDieuKhoanDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iHDSD = new Intent(DangKyActivity.this, HuongDanSuDungActivity.class);
                iHDSD.putExtra("title", "Các Điều Khoản Sử Dụng");
                startActivity(iHDSD);
            }
        });

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

    private boolean kiemTraDuLieuHopLe(String sdt, String matKhau){
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(DangKyActivity.this);
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

    @Override
    public void dangKyThanhCong(TaiKhoan taiKhoan) {
        Intent iXacNhanDangKy = new Intent(DangKyActivity.this, XacNhanDangKyActivity.class);
        iXacNhanDangKy.putExtra("taiKhoan", taiKhoan);
        startActivity(iXacNhanDangKy);

        //gửi mã xác nhận
    }

    @Override
    public void dangKyThatBai(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DangKyActivity.this);
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
    }
}
