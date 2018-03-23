package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 3/13/2018.
 */

public class CaptureCameraActivity extends AppCompatActivity{
    ImageView imgHinhChup;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_camra_trangchu_layout);
        anhXa();
        Intent intent = getIntent();
        Bitmap image = intent.getParcelableExtra("image");
        imgHinhChup.setImageBitmap(image);

//        setImage();
//        Bundle bundle = (Bundle) getIntent().getBundleExtra("hinh");
//        Bitmap bitmap = (Bitmap) bundle.get("data");
//        imgHinhChup.setImageBitmap(bitmap);
    }

    private void anhXa(){
        imgHinhChup = (ImageView) findViewById(R.id.imgHinhChup);
    }

    private void setImage(){
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CaptureCameraActivity.this);
        builder.setTitle("Add image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Camera")){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);
                }else if(items[i].equals("Gallery")){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });

        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if(requestCode == REQUEST_CAMERA){
                Bundle bundle = data.getExtras();
                final Bitmap bitmap = (Bitmap) bundle.get("data");
                imgHinhChup.setImageBitmap(bitmap);
            }else if(requestCode == SELECT_FILE){
                Uri uri = data.getData();
                imgHinhChup.setImageURI(uri);
            }
        }
    }
}
