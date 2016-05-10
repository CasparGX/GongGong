package com.sky31.gonggong.module.main;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

/**
 * Created by wukunguang on 16-5-9.
 */
public class HeaderImagesViewPagesAdapter extends PagerAdapter {


    private ImageView[] imageViews;
    private static final int MAX_PAGER = 5;
    private Context context;

    public HeaderImagesViewPagesAdapter(Context context, ImageView[] imageViews) {
        this.context = context;
        this.imageViews = imageViews;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        int pos = position % imageViews.length;

        if (pos < 0){
            pos = pos + imageViews.length;
        }
        ImageView imageView = imageViews[pos];
        ViewParent parent = imageView.getParent();
        if (parent!=null){
            ViewGroup group = (ViewGroup) parent;
            group.removeView(imageView);
        }

        container.addView(imageView);
        return imageView;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
         //container.removeView(imageViews[position % imageViews.length] );
    }


}
