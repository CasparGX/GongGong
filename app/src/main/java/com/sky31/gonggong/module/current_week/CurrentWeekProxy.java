package com.sky31.gonggong.module.current_week;

import android.content.Context;

/**
 * Created by wukunguang on 16-4-27.
 */
public class CurrentWeekProxy {

    private CurrentWeekView currentWeekView;
    private Context context;

    public CurrentWeekProxy(Context context, CurrentWeekView currentWeekView) {
        this.context = context;
        this.currentWeekView = currentWeekView;
    }

    public void setRequestProxy(){

        CurrentWeekPresent present = new CurrentWeekPresent(currentWeekView,context);
        present.requestServer();

    }
}
