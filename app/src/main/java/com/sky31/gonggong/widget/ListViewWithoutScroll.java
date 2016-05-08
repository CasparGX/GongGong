package com.sky31.gonggong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import java.util.List;

/**
 *
 *
 * 用于课程表布局
 * Created by wukunguang on 16-4-27.
 */
public class ListViewWithoutScroll extends ListView {


    public ListViewWithoutScroll(Context context) {
        super(context);
    }

    public ListViewWithoutScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewWithoutScroll(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
