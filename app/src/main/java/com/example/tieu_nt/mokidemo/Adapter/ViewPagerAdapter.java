package com.example.tieu_nt.mokidemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeAn;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeChoiMaHoc;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeDiRaNgoai;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeKhoeAnToan;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeMac;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeNgu;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeTam;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentBeVeSinh;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentDanhChoMe;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentDoDungGiaDinh;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentMienPhi;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentSanPhamKhac;
import com.example.tieu_nt.mokidemo.View.ManHinhTrangChu.FragmentTatCa;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tieu_nt on 2/27/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        list.add(new FragmentTatCa());
        list.add(new FragmentMienPhi());
        list.add(new FragmentBeAn());
        list.add(new FragmentBeMac());
        list.add(new FragmentBeNgu());
        list.add(new FragmentBeTam());
        list.add(new FragmentBeVeSinh());
        list.add(new FragmentBeKhoeAnToan());
        list.add(new FragmentBeDiRaNgoai());
        list.add(new FragmentBeChoiMaHoc());
        list.add(new FragmentDanhChoMe());
        list.add(new FragmentDoDungGiaDinh());
        list.add(new FragmentSanPhamKhac());

        titles.add("Tất cả");
        titles.add("Miễn phí");
        titles.add("Bé ăn");
        titles.add("Bé mặc");
        titles.add("Bé ngủ");
        titles.add("Bé tắm");
        titles.add("Bé vệ sinh");
        titles.add("Bé khỏe-an toàn");
        titles.add("Bé đi ra ngoài");
        titles.add("Bé chơi mà học");
        titles.add("Dành cho mẹ");
        titles.add("Đồ dùng gia đình");
        titles.add("Sản phẩm khác");
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
