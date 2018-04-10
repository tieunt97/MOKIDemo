package com.example.tieu_nt.mokidemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentTheoDoi;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentUserSanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tieu_nt on 4/8/2018.
 */

public class ViewPagerAdapterInfoUser extends FragmentPagerAdapter{
    List<Fragment> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public ViewPagerAdapterInfoUser(FragmentManager fm) {
        super(fm);

        list.add(new FragmentUserSanPham());
        list.add(new FragmentTheoDoi());
        list.add(new FragmentTheoDoi());

        titles.add("Sản phẩm");
        titles.add("Đang theo dõi");
        titles.add("Người theo dõi");
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
