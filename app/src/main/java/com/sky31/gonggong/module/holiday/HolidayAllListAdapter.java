package com.sky31.gonggong.module.holiday;

import android.content.Context;
import android.os.AsyncTask;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by root on 16-5-23.
 */
public class HolidayAllListAdapter extends BaseQuickAdapter<AsyncTask.Status> {
    public HolidayAllListAdapter(Context context, int layoutResId, List<AsyncTask.Status> data) {
        super(context, layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AsyncTask.Status status) {

    }
}
