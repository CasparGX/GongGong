package com.sky31.gonggong.module.search_mate;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.sky31.gonggong.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16-3-18.
 */
public class SearchMateActivity extends Activity {
    @Bind(R.id.et_sid)
    EditText etSid;
    @Bind(R.id.til_sid)
    TextInputLayout tilSid;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.til_name)
    TextInputLayout tilName;
    @Bind(R.id.et_card)
    EditText etCard;
    @Bind(R.id.til_card)
    TextInputLayout tilCard;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_mate);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        etName.getBackground().mutate().setColorFilter(getResources().getColor(R.color.textColorPrimary), PorterDuff.Mode.SRC_ATOP);
        tilCard.setHint(getResources().getString(R.string.card));
        tilSid.setHint(getResources().getString(R.string.sid));
        tilName.setHint(getResources().getString(R.string.name));
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);

        //toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);//设置导航栏图标
        toolbar.setTitle(R.string.app_name);//设置主标题
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    }
}
