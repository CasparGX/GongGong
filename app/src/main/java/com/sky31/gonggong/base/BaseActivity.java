package com.sky31.gonggong.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sky31.gonggong.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        super.onCreate(savedInstanceState);
        // No Titlebar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slided_left_in, R.anim.slide_right_out);
    }

    // 写一个广播的内部类，当收到动作时，结束activity
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this); // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };
}
