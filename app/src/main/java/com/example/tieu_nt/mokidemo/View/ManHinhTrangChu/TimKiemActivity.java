package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/17/2018.
 */

public class TimKiemActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener,
        View.OnClickListener{
    private  NumberPicker npNho, npLon;
    private BottomSheetBehavior behavior;
    private TextView tvGiaTimKiem, tvThoat, tvChon, tvTrangThai, tvTimKiemDanhMuc, tvTimKiemNhanHieu,
        tvTimKiemKichCo;
    private EditText edtTimKiem;
    private RelativeLayout relaDanhMuc, relaNhanHieu, relaKichCo, relaTrangThai;
    private ImageButton imgBack, imgDelete, imgVoice;
    private Button btnTimKiem, btnHuongDanTimKiem;
    private  AlertDialog.Builder builder;
    private int REQUEST_TRANGTHAI = 3;
    private String gia[] = {"10,000 VNĐ", "50,000 VNĐ", "100,000 VNĐ", "200,000 VNĐ", "400,000 VNĐ", "800,000 VNĐ", "1,000,000 VNĐ",
            "2,000,000 VNĐ", "4,000,000 VNĐ", "8,000,000 VNĐ", "10,000,000 VNĐ", "20,000,000 VNĐ"};

    private String gia1[] = {"50,000 VNĐ", "100,000 VNĐ", "200,000 VNĐ", "400,000 VNĐ", "800,000 VNĐ", "1,000,000 VNĐ",
            "2,000,000 VNĐ", "4,000,000 VNĐ", "8,000,000 VNĐ", "10,000,000 VNĐ", "20,000,000 VNĐ", "30,000,000 VNĐ"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_timkiem);
        anhXa();

        View view = findViewById(R.id.layout_bottomSheet);
        behavior = BottomSheetBehavior.from(view);

        npNho.setMinValue(0);
        npNho.setMaxValue(gia.length - 1);
        npNho.setDisplayedValues(gia);
        npNho.setWrapSelectorWheel(false);

        npLon.setMinValue(0);
        npLon.setMaxValue(gia1.length - 1);
        npLon.setDisplayedValues(gia1);
        npLon.setWrapSelectorWheel(false);

    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        imgDelete = (ImageButton) findViewById(R.id.imgDelete);
        imgDelete.setOnClickListener(this);
        imgVoice = (ImageButton) findViewById(R.id.imgVoice);
        imgVoice.setOnClickListener(this);
        npNho = (NumberPicker) findViewById(R.id.picker);
        npNho.setOnValueChangedListener(this);
        npLon = (NumberPicker) findViewById(R.id.picker1);
        npLon.setOnValueChangedListener(this);
        tvChon = (TextView) findViewById(R.id.tvChon);
        tvChon.setOnClickListener(this);
        tvGiaTimKiem = (TextView) findViewById(R.id.tvGiaTimKiem);
        tvGiaTimKiem.setOnClickListener(this);
        tvThoat = (TextView) findViewById(R.id.tvThoat);
        tvThoat.setOnClickListener(this);
        tvTrangThai = (TextView) findViewById(R.id.tvTrangThai);
        tvTimKiemDanhMuc = (TextView) findViewById(R.id.tvDanhMucTimKiem);
        tvTimKiemNhanHieu = (TextView) findViewById(R.id.tvNhanHieuTimKiem);
        tvTimKiemKichCo = (TextView) findViewById(R.id.tvKichCoTimKiem);
        edtTimKiem = (EditText) findViewById(R.id.edtTimKiem);
        relaDanhMuc = (RelativeLayout) findViewById(R.id.relaDanhMuc);
        relaDanhMuc.setOnClickListener(this);
        relaNhanHieu = (RelativeLayout) findViewById(R.id.relaNhanHieu);
        relaNhanHieu.setOnClickListener(this);
        relaKichCo = (RelativeLayout) findViewById(R.id.relaKichCoTimKiem);
        relaKichCo.setOnClickListener(this);
        relaTrangThai = (RelativeLayout) findViewById(R.id.relaTrangThai);
        relaTrangThai.setOnClickListener(this);
        btnTimKiem = (Button) findViewById(R.id.btnTimKiem);
        btnTimKiem.setOnClickListener(this);
        btnHuongDanTimKiem = (Button) findViewById(R.id.btnHuongDanTimKiem);
        btnHuongDanTimKiem.setOnClickListener(this);
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        switch (numberPicker.getId()){
            case R.id.picker:
                if(i1 > npLon.getValue()) npLon.setValue(i1);
                break;
            case R.id.picker1:
                if(i1 < npNho.getValue()) npNho.setValue(i1);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgDelete:
                xoaThongTinTimKiem();
                break;
            case R.id.relaDanhMuc:
                break;
            case R.id.relaNhanHieu:
                break;
            case R.id.relaKichCoTimKiem:
                break;
            case R.id.relaTrangThai:
                Intent iTrangThai = new Intent(TimKiemActivity.this, TrangThaiTimKiemActivity.class);
                iTrangThai.putExtra("trangThai", tvTrangThai.getText());
                startActivityForResult(iTrangThai, REQUEST_TRANGTHAI);
                break;
            case R.id.tvGiaTimKiem:
                if(behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.tvThoat:
                if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.tvChon:
                if(behavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    String giaNho = gia[npNho.getValue()];
                    String giaLon = gia1[npLon.getValue()];
                    tvGiaTimKiem.setText(giaNho.substring(0, giaNho.length() - 8) + " K - " + giaLon.substring(0, giaLon.length() - 8) + " K");
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.btnTimKiem:
                break;
            case R.id.btnHuongDanTimKiem:
                break;
        }
    }

    private void xoaThongTinTimKiem() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_thongbao_xacnhan, null, false);
        TextView tvNoiDung = (TextView) view.findViewById(R.id.tvNoiDung);
        tvNoiDung.setText("Bạn có chắc chắn muốn xóa thông tin tìm kiếm?");
        Button btnHuy = (Button) view.findViewById(R.id.btnHuy);
        btnHuy.setText("Không xóa");
        Button btnXacNhan = (Button) view.findViewById(R.id.btnDongY);
        btnXacNhan.setText("Có xóa");

        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtTimKiem.setText("");
                tvTimKiemDanhMuc.setTextColor(getResources().getColor(R.color.colorRed));
                tvTimKiemDanhMuc.setText("Tất cả");
                tvTimKiemNhanHieu.setTextColor(getResources().getColor(R.color.colorRed));
                tvTimKiemNhanHieu.setText("Tất cả");
                tvTimKiemKichCo.setTextColor(getResources().getColor(R.color.colorRed));
                tvTimKiemKichCo.setText("Tất cả");
                tvGiaTimKiem.setText("");
                tvTrangThai.setTextColor(getResources().getColor(R.color.colorRed));
                tvTrangThai.setText("Tất cả");
                alertDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_TRANGTHAI){
                String trangThai = data.getStringExtra("trangThai");
                if(!trangThai.equalsIgnoreCase("Tất cả")){
                    tvTrangThai.setTextColor(getResources().getColor(R.color.colorBlack));
                }else{
                    tvTrangThai.setTextColor(getResources().getColor(R.color.colorRed));
                }
                tvTrangThai.setText(trangThai);
            }
        }
    }
}
