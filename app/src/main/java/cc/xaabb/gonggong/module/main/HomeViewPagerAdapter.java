package cc.xaabb.gonggong.module.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;

import java.util.List;

/**
 * Created by Caspar on 2015/7/30.
 */
public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mDatas;
    private String titles[] = null;

    public HomeViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> mDatas) {
        super(fragmentManager);
        this.mDatas = mDatas;
    }

    public void setTitles(String[] titles) {
        this.titles = titles.clone();
    }

    @Override
    public Fragment getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null) {
            SpannableString sb = new SpannableString(titles[position]);
            return sb;
        } else {
            return super.getPageTitle(position);
        }
    }
}
