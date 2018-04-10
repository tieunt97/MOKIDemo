package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapterInfoUser;
import com.example.tieu_nt.mokidemo.R;

/**
 * Created by tieu_nt on 4/8/2018.
 */

public class ThongTinNguoiDungActivity extends AppCompatActivity{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapterInfoUser adapterInfoUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_user);
        anhXa();

        adapterInfoUser = new ViewPagerAdapterInfoUser(getSupportFragmentManager());
        viewPager.setAdapter(adapterInfoUser);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void anhXa(){
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}
