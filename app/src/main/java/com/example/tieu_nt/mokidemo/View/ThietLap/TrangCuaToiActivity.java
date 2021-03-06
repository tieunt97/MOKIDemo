package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tieu_nt.mokidemo.Model.Constants;
import com.example.tieu_nt.mokidemo.Model.DangNhap;
import com.example.tieu_nt.mokidemo.Presenter.ThietLap.PresenterCapNhatThongTin;
import com.example.tieu_nt.mokidemo.R;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tieu_nt on 4/13/2018.
 */

public class TrangCuaToiActivity extends AppCompatActivity implements View.OnClickListener, BottomSheetThayAnhNen.GiaoTiepGiuaFragmentVaActivity {
    private FrameLayout frameChinhSuaAnhInfo;
    private ImageButton imgBack;
    private EditText edtThongTin;
    private Button btnCapNhat;
    private TextView tvSoKyTu, tvXong, tvChinhSuaAnhBia;
    private RelativeLayout relaXong;
    private BottomSheetThayAnhNen bottomSheetThayAnhNen;
    private ImageView imgAnhBia;
    private CircleImageView imgKhachHang;
    private boolean visible = false;
    private Bitmap bitmapKH, bitmapAnhBia;
    private PresenterCapNhatThongTin presenterCapNhatThongTin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangcuatoi);
        anhXa();
        if (!DangNhap.getInstance().getKhachHang().getAnhInfoKH().equals("null"))
            Picasso.get().load(Constants.SERVER + DangNhap.getInstance().getKhachHang().getAnhInfoKH()).into(imgKhachHang);
        if (!DangNhap.getInstance().getKhachHang().getAnhBia().equals("null"))Picasso.get().load(Constants.SERVER +
                DangNhap.getInstance().getKhachHang().getAnhBia()).into(imgAnhBia);
        if (!DangNhap.getInstance().getKhachHang().getMoTa().equals("null"))
            edtThongTin.setText(DangNhap.getInstance().getKhachHang().getMoTa());

        presenterCapNhatThongTin = new PresenterCapNhatThongTin();

        bottomSheetThayAnhNen = new BottomSheetThayAnhNen();

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

        edtThongTin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!visible){
                    visible = !visible;
                    tvSoKyTu.setText(String.valueOf(edtThongTin.getText().toString().length()));
                    relaXong.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        btnCapNhat = (Button) findViewById(R.id.btnCapNhat);
        btnCapNhat.setOnClickListener(this);
        frameChinhSuaAnhInfo = (FrameLayout) findViewById(R.id.frameChinhSuaAnhInfo);
        frameChinhSuaAnhInfo.setOnClickListener(this);
        imgAnhBia = (ImageView) findViewById(R.id.imgAnhBia);
        imgKhachHang = (CircleImageView) findViewById(R.id.imgKhachHang);
        edtThongTin = (EditText) findViewById(R.id.edtThongTin);
        edtThongTin.setOnClickListener(this);
        relaXong = (RelativeLayout) findViewById(R.id.relaXong);
        tvSoKyTu = (TextView) findViewById(R.id.tvSoKyTu);
        tvXong = (TextView) findViewById(R.id.tvXong);
        tvXong.setOnClickListener(this);
        tvChinhSuaAnhBia = (TextView) findViewById(R.id.tvChinhSuaAnhBia);
        tvChinhSuaAnhBia.setOnClickListener(this);
    }

    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnCapNhat:
                String name = "", image = "", nameAnhBia = "", imageAnhBia = "", moTa = "";
                if (bitmapKH != null) {
                    name = DangNhap.getInstance().getKhachHang().getIdKhachHang() + "AnhKhachHang";
                    image = imageToString(bitmapKH);
                }
                if (bitmapAnhBia != null) {
                    nameAnhBia = DangNhap.getInstance().getKhachHang().getIdKhachHang() + "AnhBia";
                    imageAnhBia = imageToString(bitmapAnhBia);
                }
                if (edtThongTin.getText().toString().length() > 0) {
                    moTa = edtThongTin.getText().toString();
                }
                presenterCapNhatThongTin.capNhatThongTin(name, image, nameAnhBia, imageAnhBia, moTa);
                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
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
            case R.id.frameChinhSuaAnhInfo:
                bottomSheetThayAnhNen.setAnhBia(false);
                bottomSheetThayAnhNen.show(getSupportFragmentManager(), "anhInfo");
                break;
            case R.id.tvChinhSuaAnhBia:
                bottomSheetThayAnhNen.setAnhBia(true);
                bottomSheetThayAnhNen.show(getSupportFragmentManager(), "anhBia");
                break;
        }
    }

    @Override
    public void bitMap(boolean anhBia, Bitmap bitmap) {
        if(anhBia) {
            bottomSheetThayAnhNen.dismiss();
            bitmapAnhBia = bitmap;
            imgAnhBia.setImageBitmap(bitmapAnhBia);
        }
        else{
            bottomSheetThayAnhNen.dismiss();
            bitmapKH = bitmap;
            imgKhachHang.setImageBitmap(bitmapKH);
        }
    }
}