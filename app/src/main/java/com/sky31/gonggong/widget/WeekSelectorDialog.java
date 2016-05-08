package com.sky31.gonggong.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by wukunguang on 16-4-29.
 */
public class WeekSelectorDialog extends Dialog {


    public WeekSelectorDialog(Context context) {
        super(context);
    }

    protected WeekSelectorDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public WeekSelectorDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
    }
}
