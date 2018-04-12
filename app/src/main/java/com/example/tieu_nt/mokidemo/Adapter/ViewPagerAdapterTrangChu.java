package com.example.tieu_nt.mokidemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeAn;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeChoiMaHoc;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeDiRaNgoai;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeKhoeAnToan;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeMac;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeNgu;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeTam;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentBeVeSinh;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentDanhChoMe;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentDoDungGiaDinh;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentMienPhi;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentSanPhamKhac;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.Fragment.FragmentTatCa;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tieu_nt on 2/27/2018.
 */

public class ViewPagerAdapterTrangChu extends FragmentPagerAdapter {
    List<Fragment> list;
    List<String> titles;

    public ViewPagerAdapterTrangChu(FragmentManager fm, List<Fragment> list, List<String> titles) {
        super(fm);
        this.list = list;
        this.titles = titles;
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
