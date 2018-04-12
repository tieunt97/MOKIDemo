package com.example.tieu_nt.mokidemo.View.ManHinhTrangChu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapterInfoUser;
import com.example.tieu_nt.mokidemo.Adapter.ViewPagerAdapterTrangChu;
import com.example.tieu_nt.mokidemo.R;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentTheoDoi;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentUserSanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 4/8/2018.
 */

public class ThongTinNguoiDungActivity extends AppCompatActivity{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapterTrangChu adapterTrangChu;
    private List<Fragment> list = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_user);
        anhXa();

        list.add(new FragmentUserSanPham());
        list.add(new FragmentTheoDoi());
        list.add(new FragmentTheoDoi());

        titles.add("Sản phẩm");
        titles.add("Đang theo dõi");
        titles.add("Người theo dõi");
        adapterTrangChu = new ViewPagerAdapterTrangChu(getSupportFragmentManager(), list, titles);
        viewPager.setAdapter(adapterTrangChu);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void anhXa(){
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}
