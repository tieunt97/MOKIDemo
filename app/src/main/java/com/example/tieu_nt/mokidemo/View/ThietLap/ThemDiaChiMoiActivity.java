package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.DiaChi;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.Presenter.ThietLap.PresenterThemDiaChi;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/13/2018.
 */

public class ThemDiaChiMoiActivity extends AppCompatActivity implements View.OnClickListener, ViewThemDiaChi{
    private ImageButton imgBack;
    private EditText edtTinhTP, edtQuanHuyen, edtXaPhuong, edtXom;
    private TextView tvThemDiaChi;
    private CheckBox cbMacDinh;
    private Button btnThemDiaChi;
    private boolean themDiaChi;
    private KhachHang khachHang;
    private ModelKhachHang modelKhachHang;
    private int position;
    private PresenterThemDiaChi presenterThemDiaChi;
    private DiaChi diaChi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diachimoi);
        anhXa();

        modelKhachHang = ModelKhachHang.getInstance();
        themDiaChi = getIntent().getBooleanExtra("themDiaChi", false);
        position = getIntent().getIntExtra("position", 0);
        khachHang = (KhachHang) getIntent().getSerializableExtra("khachHang");

        if(themDiaChi && khachHang.getDsDiaChi().size() == 0){
            cbMacDinh.setVisibility(View.GONE);
        }
        if(!themDiaChi) {
            diaChi = khachHang.getDsDiaChi().get(position);
            tvThemDiaChi.setText("Chỉnh Sửa Địa Chỉ");
            String diaChiS[] = diaChi.getDiaChi().split("-");
            edtTinhTP.setText(diaChiS[3]);
            edtQuanHuyen.setText(diaChiS[2]);
            edtXaPhuong.setText(diaChiS[1]);
            edtXom.setText(diaChiS[0]);
            if (diaChi.getTrangThai() == 1){
                cbMacDinh.setChecked(true);
            }
            btnThemDiaChi.setText("Sửa");
        }

        setActions();
        presenterThemDiaChi = new PresenterThemDiaChi(this);
    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        tvThemDiaChi = (TextView) findViewById(R.id.tvDiaChiMoi);
        edtTinhTP = (EditText) findViewById(R.id.edtTinhTP);
        edtQuanHuyen = (EditText) findViewById(R.id.edtQuanHuyen);
        edtXaPhuong = (EditText) findViewById(R.id.edtXaPhuong);
        edtXom = (EditText) findViewById(R.id.edtXom);
        cbMacDinh = (CheckBox) findViewById(R.id.cbMacDinh);
        btnThemDiaChi = (Button) findViewById(R.id.btnThemDiaChi);
    }

    private void setActions(){
        imgBack.setOnClickListener(this);
        btnThemDiaChi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnThemDiaChi:
                int macDinh = cbMacDinh.isChecked()?1:0;
                presenterThemDiaChi.kiemTraDuLieu(edtTinhTP.getText().toString(), edtQuanHuyen.getText().toString(),
                            edtXaPhuong.getText().toString(), edtXom.getText().toString(), macDinh);
                break;
        }
    }

    @Override
    public void themDiaChiThanhCong(DiaChi diaChi) {
        if(themDiaChi) {
            boolean b = modelKhachHang.capNhatDiaChi("themDiaChi", khachHang.getIdKhachHang(), "", diaChi.getDiaChi(), diaChi.getTrangThai());
            if(b){
                ((KhachHang) getIntent().getSerializableExtra("khachHang")).getDsDiaChi().add(diaChi);
                finish();
            }
        }else{
            boolean b = modelKhachHang.capNhatDiaChi("suaDiaChi", khachHang.getIdKhachHang(), this.diaChi.getDiaChi(), diaChi.getDiaChi(), diaChi.getTrangThai());
            if(b){
                finish();
            }
        }

    }

    @Override
    public void themDiaChiThatBai(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ThemDiaChiMoiActivity.this);
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

    @Override
    public void finish() {
        Intent data = new Intent();
        data.putExtra("trangThai", 1);
        setResult(RESULT_OK, data);
        super.finish();
    }
}
