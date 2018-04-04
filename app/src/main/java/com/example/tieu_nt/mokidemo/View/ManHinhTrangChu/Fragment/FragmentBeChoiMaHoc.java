package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 3/12/2018.
 */

public class FragmentBeChoiMaHoc extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bechoimahoc, container, false);
        return view;
    }
}
