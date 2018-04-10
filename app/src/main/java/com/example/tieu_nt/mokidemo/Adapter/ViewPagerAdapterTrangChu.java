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
    List<Fragment> list = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    public ViewPagerAdapterTrangChu(FragmentManager fm) {
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
