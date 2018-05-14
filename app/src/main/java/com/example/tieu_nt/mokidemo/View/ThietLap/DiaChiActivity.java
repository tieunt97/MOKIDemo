package com.example.tieu_nt.mokidemo.View.ThietLap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.tieu_nt.mokidemo.Adapter.AdapterDiaChi;
import com.example.tieu_nt.mokidemo.Model.BottomSheetCapNhatDiaChi;
import com.example.tieu_nt.mokidemo.Model.DiaChi;
import com.example.tieu_nt.mokidemo.Model.KhachHang;
import com.example.tieu_nt.mokidemo.Model.Data.ModelKhachHang;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/13/2018.
 */

public class DiaChiActivity extends AppCompatActivity implements View.OnClickListener, BottomSheetCapNhatDiaChi.GiaoTiepGiuaFragmentVaActivity{
    private ImageButton imgBack;
    private Button btnThemDiaChiMoi;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterDiaChi adapterDiaChi;
    private KhachHang khachHang;
    private ModelKhachHang modelKhachHang;
    public static int THEM_DIA_CHI = 1, CAP_NHAT_DIA_CHI = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_diachi);
        anhXa();
        modelKhachHang = ModelKhachHang.getInstance();

        khachHang = (KhachHang) getIntent().getSerializableExtra("khachHang");
        adapterDiaChi = new AdapterDiaChi(DiaChiActivity.this, khachHang.getDsDiaChi());
        layoutManager = new LinearLayoutManager(DiaChiActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDiaChi);
        adapterDiaChi.notifyDataSetChanged();
    }

    private void anhXa(){
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        imgBack.setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDiaChi);
        btnThemDiaChiMoi = (Button) findViewById(R.id.btnThemDiaChi);
        btnThemDiaChiMoi.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgBack:
                finish();
                break;
            case R.id.btnThemDiaChi:
                Intent iThemDiaChiMoi = new Intent(DiaChiActivity.this, ThemDiaChiMoiActivity.class);
                iThemDiaChiMoi.putExtra("themDiaChi", true);
                iThemDiaChiMoi.putExtra("khachHang", khachHang);
                startActivityForResult(iThemDiaChiMoi, THEM_DIA_CHI);
                break;
        }
    }

    @Override
    public void xoaDiaChi(int position) {
        DiaChi diaChi = khachHang.getDsDiaChi().get(position);
        khachHang.getDsDiaChi().remove(position);
        boolean b = modelKhachHang.capNhatDiaChi("xoaDiaChi", khachHang.getIdKhachHang(), "", diaChi.getDiaChi(), 0);
        adapterDiaChi.notifyDataSetChanged();

        if (b) Log.d("thanhCong", "xoa");
    }

    @Override
    public void suaDiaChi(int position) {
        DiaChi diaChi = khachHang.getDsDiaChi().get(position);
        Intent iDiaChiMoi = new Intent(DiaChiActivity.this, ThemDiaChiMoiActivity.class);
        iDiaChiMoi.putExtra("themDiaChi", false);
        iDiaChiMoi.putExtra("khachHang", khachHang);
        iDiaChiMoi.putExtra("position", position);
        startActivityForResult(iDiaChiMoi, CAP_NHAT_DIA_CHI);
    }

    @Override
    public void datMacDinh(int position) {
        DiaChi diaChi = khachHang.getDsDiaChi().get(position);
        khachHang.setDiaChi(diaChi.getDiaChi());
        for(int i = 0; i < khachHang.getDsDiaChi().size(); i++){
            if(i != position && khachHang.getDsDiaChi().get(i).getTrangThai() == 1){
                khachHang.getDsDiaChi().get(i).setTrangThai(0);
                break;
            }
        }
        khachHang.getDsDiaChi().get(position).setTrangThai(1);
        boolean b = modelKhachHang.capNhatDiaChi("datDiaChiMacDinh", khachHang.getIdKhachHang(), "", diaChi.getDiaChi(), 1);
        if (b) Log.d("thanhCong", "datMacDinh");
        adapterDiaChi.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            int trangThai = data.getIntExtra("trangThai", 0);
            Log.d("resultDiaChi", requestCode + "");
            adapterDiaChi.notifyDataSetChanged();
        }
    }
}
