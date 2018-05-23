package com.example.tieu_nt.mokidemo.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tieu_nt.mokidemo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class FragmentSliderChiTietSanPham extends Fragment{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_slider_fragmentchitietsanpham, container, false);

        Bundle bundle = getArguments();
        String linkHinh = bundle.getString("linkHinh");

        ImageView imageView = view.findViewById(R.id.imgHinhSlider);
        Picasso.get().load(linkHinh).into(imageView);
        return view;
    }
}
