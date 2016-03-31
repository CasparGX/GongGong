package com.sky31.gonggong.module.swzl;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by wukunguang on 16-3-30.
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener{
    public RefreshListView(Context context) {
        super(context);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
