package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by tieu_nt on 4/19/2018.
 */

public class BottomSheetThayAnhNen extends BottomSheetDialogFragment{
    private boolean anhBia;
    private static final int REQUEST_IMAGE_CAPTURE = 1, REQUEST_IMG_GALLERY = 2;
    private GiaoTiepGiuaFragmentVaActivity giaoTiepGiuaFragmentVaActivity;

    public interface GiaoTiepGiuaFragmentVaActivity {
        void bitMap(boolean anhBia, Bitmap bitmap);
    }

    //dung cho phien ban 5.0 tro len
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        giaoTiepGiuaFragmentVaActivity = (GiaoTiepGiuaFragmentVaActivity) context;
    }

    //dung cho phien ban 5.0 tro xuong
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        giaoTiepGiuaFragmentVaActivity = (GiaoTiepGiuaFragmentVaActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_thayanhnen, container, false);
        TextView tvChupAnhMoi = (TextView) view.findViewById(R.id.tvChupAnhMoi);
        TextView tvAnhTuThuVien = (TextView) view.findViewById(R.id.tvAnhTuThuVien);

        tvChupAnhMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        tvAnhTuThuVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REQUEST_IMG_GALLERY);
            }
        });

        Button btnDong = (Button) view.findViewById(R.id.btnDong);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE){
                Bundle extras = data.getExtras();
                if (anhBia){
                    giaoTiepGiuaFragmentVaActivity.bitMap(anhBia, (Bitmap) extras.get("data"));
                }else{
                    giaoTiepGiuaFragmentVaActivity.bitMap(anhBia, (Bitmap) extras.get("data"));
                }
            }else if(requestCode == REQUEST_IMG_GALLERY){
                Uri uri = data.getData();
                if (anhBia){
                    try {
                        giaoTiepGiuaFragmentVaActivity.bitMap(anhBia, MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        giaoTiepGiuaFragmentVaActivity.bitMap(anhBia, MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public void setAnhBia(boolean anhBia){
        this.anhBia = anhBia;
    }

    public boolean isAnhBia(){
        return this.anhBia;
    }
}
