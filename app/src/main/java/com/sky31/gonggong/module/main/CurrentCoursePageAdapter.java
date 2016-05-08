package com.sky31.gonggong.module.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by wukunguang on 16-4-26.
 */
public class CurrentCoursePageAdapter extends FragmentPagerAdapter {


    private FragmentManager fm;
    private List<Fragment> fragmentList;
    public CurrentCoursePageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);

        this.fm = fm;
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
