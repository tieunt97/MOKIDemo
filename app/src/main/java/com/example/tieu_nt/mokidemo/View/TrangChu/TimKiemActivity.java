package com.example.tieu_nt.mokidemo.View.TrangChu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/17/2018.
 */

public class TimKiemActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener,
        View.OnClickListener{
    private  NumberPicker npNho, npLon;
    private BottomSheetBehavior behavior;
    private TextView tvGiaTimKiem, tvThoat, tvChon, tvTrangThai, tvTimKiemDanhMuc;
    private EditText edtTimKiem;
    private RelativeLayout relaDanhMuc, relaTrangThai;
    private ImageButton imgBack, imgDelete, imgVoice;
    private Button btnTimKiem, btnHuongDanTimKiem;
    private  AlertDialog.Builder builder;
    private final int REQUEST_DANHMUC = 4, REQUEST_TRANGTHAI = 5, REQUEST_NHANHIEU = 6, REQUEST_KICHCO = 7;
    private String gia[] = {"10,000 VNĐ", "50,000 VNĐ", "100,000 VNĐ", "200,000 VNĐ", "400,000 VNĐ", "800,000 VNĐ", "1,000,000 VNĐ",
            "2,000,000 VNĐ", "4,000,000 VNĐ", "8,000,000 VNĐ", "10,000,000 VNĐ", "20,000,000 VNĐ"};
    private String gia1[] = {"50,000 VNĐ", "100,000 VNĐ", "200,000 VNĐ", "400,000 VNĐ", "800,000 VNĐ", "1,000,000 VNĐ",
            "2,000,000 VNĐ", "4,000,000 VNĐ", "8,000,000 VNĐ", "10,000,000 VNĐ", "20,000,000 VNĐ", "30,000,000 VNĐ"};
    private int giaTK[] = {10000, 50000, 100000, 200000, 400000, 800000, 1000000, 2000000, 4000000, 8000000, 10000000,
                20000000, 3000000};
    private int giaThap = 0, giaCao = 0;
    private DanhMuc danhMuc;
    private String trangThai = "";

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
//        tvTimKiemNhanHieu = (TextView) findViewById(R.id.tvNhanHieuTimKiem);
//        tvTimKiemKichCo = (TextView) findViewById(R.id.tvKichCoTimKiem);
        edtTimKiem = (EditText) findViewById(R.id.edtTimKiem);
        relaDanhMuc = (RelativeLayout) findViewById(R.id.relaDanhMuc);
        relaDanhMuc.setOnClickListener(this);
//        relaNhanHieu = (RelativeLayout) findViewById(R.id.relaNhanHieu);
//        relaNhanHieu.setOnClickListener(this);
//        relaKichCo = (RelativeLayout) findViewById(R.id.relaKichCoTimKiem);
//        relaKichCo.setOnClickListener(this);
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
                Intent iDanhMuc = new Intent(this, DanhMucActivity.class);
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setIdDanhMuc(0);
                danhMuc.setTenDanhMuc("Danh mục");
                iDanhMuc.putExtra("danhMuc", danhMuc);
                startActivityForResult(iDanhMuc, REQUEST_DANHMUC);
                break;
//            case R.id.relaNhanHieu:
//                break;
//            case R.id.relaKichCoTimKiem:
//                break;
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
                    giaThap = giaTK[npNho.getValue()];
                    String giaLon = gia1[npLon.getValue()];
                    giaCao = giaTK[npLon.getValue() + 1];
                    tvGiaTimKiem.setText(giaNho.substring(0, giaNho.length() - 8) + " K - " + giaLon.substring(0, giaLon.length() - 8) + " K");
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.btnTimKiem:
                timKiemSanPham();
                break;
            case R.id.btnHuongDanTimKiem:
                break;
        }
    }

    private boolean kiemTraDuLieuTimKiem(){
        if(edtTimKiem.getText().toString().equals("") && danhMuc == null && trangThai.equals("") && giaThap == 0 && giaCao == 0)
            return false;
        else
            return true;
    }

    private void timKiemSanPham() {
        if(!kiemTraDuLieuTimKiem()){
            final AlertDialog.Builder builder = new AlertDialog.Builder(TimKiemActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_thongbao_dangnhap, null, false);
            TextView tvNoiDung = (TextView) view.findViewById(R.id.tvNoiDung);
            tvNoiDung.setText("Chưa có thông tin tìm kiếm");
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
        }else{
            Intent iTimKiem = new Intent(this, SanPhamTimKiemActivity.class);
            iTimKiem.putExtra("tenSP", edtTimKiem.getText().toString());
            if(danhMuc != null){
                iTimKiem.putExtra("idLoaiSP", danhMuc.getIdDanhMuc());
            }
            iTimKiem.putExtra("trangThai", trangThai);
            iTimKiem.putExtra("giaThap", giaThap);
            iTimKiem.putExtra("giaCao", giaCao);

            startActivity(iTimKiem);
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
//                tvTimKiemNhanHieu.setTextColor(getResources().getColor(R.color.colorRed));
//                tvTimKiemNhanHieu.setText("Tất cả");
//                tvTimKiemKichCo.setTextColor(getResources().getColor(R.color.colorRed));
//                tvTimKiemKichCo.setText("Tất cả");
                tvGiaTimKiem.setText("");
                tvTrangThai.setTextColor(getResources().getColor(R.color.colorRed));
                tvTrangThai.setText("Tất cả");
                giaCao = 0;
                giaThap = 0;
                danhMuc = null;
                edtTimKiem.setText("");
                alertDialog.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_TRANGTHAI){
                trangThai = data.getStringExtra("trangThai");
                if(!trangThai.equalsIgnoreCase("Tất cả")){
                    tvTrangThai.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvTrangThai.setText(trangThai);
                }else{
                    tvTrangThai.setTextColor(getResources().getColor(R.color.colorRed));
                    tvTrangThai.setText(trangThai);
                    trangThai = "";
                }
            }else if(requestCode == REQUEST_DANHMUC){
                danhMuc = (DanhMuc) data.getSerializableExtra("danhMuc");
                if (danhMuc  != null){
                    tvTimKiemDanhMuc.setTextColor(getResources().getColor(R.color.colorBlack));
                    tvTimKiemDanhMuc.setText(danhMuc.getTenDanhMuc());
                }
            }
        }
    }
}
