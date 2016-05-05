package com.sky31.gonggong.module.article.list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wukunguang on 16-5-3.
 */
public class ArticleViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    public ArticleViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {

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
