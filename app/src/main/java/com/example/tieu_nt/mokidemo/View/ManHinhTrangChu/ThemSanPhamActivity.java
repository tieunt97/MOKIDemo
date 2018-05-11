package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.tieu_nt.mokidemo.Model.DanhMuc;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.CameraTrangChu.CameraActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 5/3/2018.
 */

public class ThemSanPhamActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher{
    private ImageButton imgBack, imgDelete;
    private List<Bitmap> bitmaps = new ArrayList<>();
    private List<ImageView> dsImgView = new ArrayList<>();
    private EditText edtTenSP, edtMoTaSP, edtGiaBan;
    private TextView tvDanhMuc, tvTrangThai, tvKhoiLuong, tvKichThuoc, tvNhanHieu, tvNoiBan;
    private ToggleButton tgMienPhi, tgBanNhanh, tgMacCa;
    private RelativeLayout relaDanhMuc, relaTrangThai, relaNhanHieu, relaKhoiLuong, relaKichThuoc, relaNoiBan;
    private final int REQUEST_DANHMUC = 4, REQUEST_TRANGTHAI = 5, REQUEST_NHANHIEU = 6,
        REQUEST_KHOILUONG = 7, REQUEST_KICHTHUOC = 8, REQUEST_NOIBAN = 9;
    private int position = 0;
    private DanhMuc danhMuc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themsanpham);
        anhXa();
        byte[] byte1 = getIntent().getByteArrayExtra("image");
        bitmaps.add(BitmapFactory.decodeByteArray(byte1, 0, byte1.length));
        setImageBitMap(0);
        setImageCapture(1);
        setActions();
    }

    private void anhXa() {
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgDelete = (ImageButton) findViewById(R.id.imgDelete);
        dsImgView.add((ImageView) findViewById(R.id.img1));
        dsImgView.add((ImageView) findViewById(R.id.img2));
        dsImgView.add((ImageView) findViewById(R.id.img3));
        dsImgView.add((ImageView) findViewById(R.id.img4));
        edtTenSP = (EditText) findViewById(R.id.edtTenSP);
        edtMoTaSP = (EditText) findViewById(R.id.edtMoTaSP);
        edtGiaBan = (EditText) findViewById(R.id.edtGiaBan);
        tvDanhMuc = (TextView) findViewById(R.id.tvDanhMuc);
        tvTrangThai = (TextView) findViewById(R.id.tvTrangThai);
        tvNhanHieu = (TextView) findViewById(R.id.tvNhanHieu);
        tvKhoiLuong = (TextView) findViewById(R.id.tvKhoiLuong);
        tvKichThuoc = (TextView) findViewById(R.id.tvKichThuoc);
        tvNoiBan = (TextView) findViewById(R.id.tvNoiBan);
        tgMienPhi = (ToggleButton) findViewById(R.id.tgMienPhi);
        tgBanNhanh = (ToggleButton) findViewById(R.id.tgBanNhanh);
        tgMacCa = (ToggleButton) findViewById(R.id.tgMacCa);
        relaDanhMuc = (RelativeLayout) findViewById(R.id.relaDanhMuc);
        relaTrangThai = (RelativeLayout) findViewById(R.id.relaTrangThai);
        relaNhanHieu = (RelativeLayout) findViewById(R.id.relaNhanHieu);
        relaKhoiLuong = (RelativeLayout) findViewById(R.id.relaKhoiLuong);
        relaKichThuoc = (RelativeLayout) findViewById(R.id.relaKichThuoc);
        relaNoiBan = (RelativeLayout) findViewById(R.id.relaNoiBan);
    }

    private void setActions(){
        imgBack.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        setActionImageView();
        relaDanhMuc.setOnClickListener(this);
        relaTrangThai.setOnClickListener(this);
        relaNhanHieu.setOnClickListener(this);
        relaNoiBan.setOnClickListener(this);
        edtGiaBan.addTextChangedListener(this);
    }

    private void setActionImageView(){
        int size = bitmaps.size();
        if(size > 2){
            size = 3;
        }
        for (int i = 0; i < size + 1; i++){
            dsImgView.get(i).setOnClickListener(this);
        }
    }

    private void setImageBitMap(int position){
        dsImgView.get(position).setImageBitmap(bitmaps.get(position));
        dsImgView.get(position).setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private void setImageCapture(int position){
        dsImgView.get(position).setImageDrawable(getResources().getDrawable(R.drawable.capture_image));
        dsImgView.get(position).setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        dsImgView.get(position).setBackgroundColor(getResources().getColor(R.color.colorGreyish));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.imgDelete:
                break;
            case R.id.img1:
                editImage(0);
                break;
            case R.id.img2:
                editImage(1);
                break;
            case R.id.img3:
                editImage(2);
                break;
            case R.id.img4:
                editImage(3);
                break;
            case R.id.relaDanhMuc:
                Intent iDanhMuc = new Intent(this, DanhMucActivity.class);
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setIdDanhMuc(0);
                danhMuc.setTenDanhMuc("Danh mục");
                iDanhMuc.putExtra("danhMuc", danhMuc);
                startActivityForResult(iDanhMuc, REQUEST_DANHMUC);
                break;
            case R.id.relaTrangThai:
                Intent iTrangThai = new Intent(this, TrangThaiTimKiemActivity.class);
                iTrangThai.putExtra("themSanPham", true);
                startActivityForResult(iTrangThai, REQUEST_TRANGTHAI);
                break;
            case R.id.relaNhanHieu:
                break;
            case R.id.relaKhoiLuong:
                break;
            case R.id.relaKichThuoc:
                break;
            case R.id.relaNoiBan:
                break;
        }
    }

    private void editImage(final int position){
        this.position = position;
        if(position >= bitmaps.size()){
            Intent iCamera = new Intent(ThemSanPhamActivity.this, CameraActivity.class);
            iCamera.putExtra("position", position);
            startActivityForResult(iCamera, position);
        }else{
            final AlertDialog.Builder builder = new AlertDialog.Builder(ThemSanPhamActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_suaanh_video, null, false);
            ImageButton imgDelete = view.findViewById(R.id.imgDelete);
            ImageButton imgEdit = view.findViewById(R.id.imgEdit);
            ImageView imgSanPham = view.findViewById(R.id.imgSanPham);
            imgSanPham.setImageBitmap(bitmaps.get(position));

            builder.setView(view);
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    bitmaps.remove(position);
                    for(int i = 0; i < bitmaps.size(); i++){
                        setImageBitMap(i);
                    }
                    if (bitmaps.size() < 4){
                        setImageCapture(bitmaps.size());
                        if(bitmaps.size() < 3)
                            setImageNull(bitmaps.size() + 1);
                    }
                }
            });

            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iCamera = new Intent(ThemSanPhamActivity.this, CameraActivity.class);
                    iCamera.putExtra("position", position);
                    startActivityForResult(iCamera, position);
                    alertDialog.dismiss();
                }
            });
        }
    }

    private void setImageNull(int position){
        dsImgView.get(position).setImageDrawable(null);
        dsImgView.get(position).setBackgroundColor(getResources().getColor(R.color.colorWhite));
        dsImgView.get(position).setOnClickListener(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == this.position ){
                byte[] byteImage = data.getByteArrayExtra("image");
                Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                if(requestCode > bitmaps.size() - 1){
                    //thêm bimap vào dsBitmap
                    bitmaps.add(bitmap);
                    setActionImageView();
                    if(requestCode < 3){
                        setImageCapture(requestCode + 1);
                    }
                }else{
                    //thay bitmap vào vị trí yêu cầu chỉnh sửa
                    bitmaps.set(requestCode, bitmap);
                }
                setImageBitMap(requestCode);
            }else if (requestCode == REQUEST_DANHMUC){
                danhMuc = (DanhMuc) data.getSerializableExtra("danhMuc");
                if (danhMuc  != null)
                    tvDanhMuc.setText(danhMuc.getTenDanhMuc());
            }else if(requestCode == REQUEST_TRANGTHAI){
                tvTrangThai.setText(data.getStringExtra("trangThai"));
            }else if(requestCode == REQUEST_KHOILUONG){

            }else if(requestCode == REQUEST_KICHTHUOC){

            }else if(requestCode == REQUEST_NHANHIEU){

            }else if(requestCode == REQUEST_NOIBAN){

            }

        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (edtGiaBan.getText().hashCode() == charSequence.hashCode()){
            if (charSequence.length() == 1 && charSequence.equals("0")){
                edtGiaBan.setText("");
            }
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
