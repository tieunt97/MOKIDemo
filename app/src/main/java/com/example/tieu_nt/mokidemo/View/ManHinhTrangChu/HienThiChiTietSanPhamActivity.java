package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tieu_nt.mokidemo.Adapter.AdapterViewPagerSlider;
import com.example.tieu_nt.mokidemo.Model.SanPham;
import com.example.tieu_nt.mokidemo.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentSliderChiTietSanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 4/7/2018.
 */

public class HienThiChiTietSanPhamActivity extends AppCompatActivity implements View.OnClickListener, ViewChiTietSanPham, ViewPager.OnPageChangeListener{
    private SanPham sanPham;
    private TextView tvTenSP;
    private ImageButton imgBack;
    private ViewPager viewPagerSlider;
    private LinearLayout layoutDots;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TextView[] tvDots;
    private PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        anhXa();
        Intent intent = getIntent();
        sanPham = (SanPham) intent.getSerializableExtra("sanPham");
        tvTenSP.setText(sanPham.getTenSanPham());
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.layDanhSachHinhSP(sanPham);
    }

    private void anhXa(){
        tvTenSP = (TextView) findViewById(R.id.tvTenSP);
        imgBack = (ImageButton) findViewById(R.id.imgBack);
        viewPagerSlider = (ViewPager) findViewById(R.id.viewPagerSlider);
        viewPagerSlider.setOnPageChangeListener(this);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);
        imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.imgBack:
                finish();
                break;
        }
    }

    @Override
    public void hienThiSliderSP(String[] linkHinhSP) {
        for (int i = 0; i < linkHinhSP.length; i++){
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkHinh", linkHinhSP[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }
        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragmentList);
        viewPagerSlider.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        themDotSlider(0);
        viewPagerSlider.setOnPageChangeListener(this);
    }

    @Override
    public void hienThiChiTietSanPham(SanPham sanPham) {

    }

    private void themDotSlider(int viTriHienTai){
        tvDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i = 0; i < tvDots.length; i++){
            tvDots[i] = new TextView(this);
            tvDots[i].setText(Html.fromHtml("&#8226"));
            tvDots[i].setTextSize(30);
            tvDots[i].setTextColor(getIdColor(R.color.colorWhite));

            layoutDots.addView(tvDots[i]);
        }

        tvDots[viTriHienTai].setTextColor(getIdColor(R.color.colorBlack));
    }

    private int getIdColor(int idcolor){
        int color = 0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this, idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < tvDots.length; i++){
            if (i == position) continue;
            tvDots[i].setTextColor(getIdColor(R.color.colorWhite));
        }
        tvDots[position].setTextColor(getIdColor(R.color.colorBlack));
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
