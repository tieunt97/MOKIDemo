package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.CameraTrangChu.CameraActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 5/3/2018.
 */

public class ThemSanPhamActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton imgBack, imgDelete;
    private List<Bitmap> bitmaps = new ArrayList<>();
    private List<ImageView> dsImgView = new ArrayList<>();

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
    }

    private void setActions(){
        imgBack.setOnClickListener(this);
        imgDelete.setOnClickListener(this);
        setActionImageView();
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
        }
    }

    private void editImage(final int position){
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
        }
    }
}
