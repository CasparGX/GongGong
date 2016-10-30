package cc.xaabb.gonggong.module.swzl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.view.ViewGroup;

import java.util.List;

/**
 *
 * Created by wukunguang on 16-3-29.
 *
 */
public class SwzlViewPagerAdapter extends FragmentPagerAdapter{


    private List<Fragment> fragmentList;
    private FragmentManager manager;

    public SwzlViewPagerAdapter(FragmentManager manager,List<Fragment> fragmentList,List<String> titles) {
        super(manager);

        this.fragmentList = fragmentList;


    }



    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }



    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);

    }







}
