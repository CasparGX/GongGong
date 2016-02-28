package com.sky31.gonggong.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No Titlebar
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
