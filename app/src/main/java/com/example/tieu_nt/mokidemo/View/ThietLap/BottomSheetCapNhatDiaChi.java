package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/23/2018.
 */

public class BottomSheetCapNhatDiaChi extends BottomSheetDialogFragment{
    private GiaoTiepGiuaFragmentVaActivity giaoTiepGiuaFragmentVaActivity;

    public interface GiaoTiepGiuaFragmentVaActivity {
        void xoaDiaChi(int position);
        void suaDiaChi(int position);
        void datMacDinh(int position);
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
        View view = inflater.inflate(R.layout.bottomsheet_capnhatdiachi, container, false);
        TextView tvXoa = (TextView) view.findViewById(R.id.tvXoa);
        TextView tvSua = (TextView) view.findViewById(R.id.tvSua);
        TextView tvDatMacDinh = (TextView) view.findViewById(R.id.tvDatMacDinh);

        Bundle bundle = getArguments();
        final int position = bundle.getInt("position", 0);
        int macDinh = bundle.getInt("macDinh", 0);
        if(macDinh == 0) tvDatMacDinh.setVisibility(View.VISIBLE);

        tvXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giaoTiepGiuaFragmentVaActivity.xoaDiaChi(position);
                dismiss();
            }
        });

        tvSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giaoTiepGiuaFragmentVaActivity.suaDiaChi(position);
            }
        });

        tvDatMacDinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giaoTiepGiuaFragmentVaActivity.datMacDinh(position);
                dismiss();
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

}
