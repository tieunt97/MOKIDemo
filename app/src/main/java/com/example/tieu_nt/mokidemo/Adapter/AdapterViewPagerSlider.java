package com.example.tieu_nt.mokidemo.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by tieu_nt on 4/12/2018.
 */

public class AdapterViewPagerSlider extends FragmentPagerAdapter{
    List<Fragment> fragmentList;
    public AdapterViewPagerSlider(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
