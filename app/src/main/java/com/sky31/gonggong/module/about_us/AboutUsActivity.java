package com.sky31.gonggong.module.about_us;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.module.article.detail.ArticleDetailActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity implements View.OnClickListener {

    private final static String JOIN_US_URL = "http://zx814.sky31.com/";

    @Bind(R.id.about_us_toolbar)
    Toolbar aboutUsToolbar;
    @Bind(R.id.about_us_logo)
    ImageView aboutUsLogo;
    @Bind(R.id.about_us_layout)
    LinearLayout aboutUsLayout;

    private final static String TAG = "AboutUsActivity";
    @Bind(R.id.about_us_check_update)
    LinearLayout aboutUsCheckUpdate;
    @Bind(R.id.about_us_open_source)
    LinearLayout aboutUsOpenSource;
    @Bind(R.id.about_us_detail)
    LinearLayout aboutUsDetail;
    @Bind(R.id.about_us_join_us)
    LinearLayout aboutUsJoinUs;
    @Bind(R.id.about)
    LinearLayout about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);

        //  initLogo();

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initLogo();
        initListener();

    }

    private void initListener() {

        about.setOnClickListener(this);
        aboutUsJoinUs.setOnClickListener(this);
        aboutUsCheckUpdate.setOnClickListener(this);
        aboutUsOpenSource.setOnClickListener(this);
        aboutUsDetail.setOnClickListener(this);

    }

    private void initLogo() {

        int height = aboutUsLayout.getHeight() / 3;
        Log.d(TAG, height + "");

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        height);
        params.setMargins(0,0,0,aboutUsLayout.getHeight() / 10);
        aboutUsLogo.setLayoutParams(params);

        aboutUsLogo.setImageBitmap(CommonFunction.
                resToBitmap(getApplicationContext(), R.drawable.about_us_logo));



    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {

            case R.id.about_us_check_update:
                Toast.makeText(getBaseContext(), "检车更新", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about_us_open_source:
                intent.setClass(AboutUsActivity.this,OpenSourceShowActivity.class);
                startActivity(intent);
                break;

            case R.id.about_us_detail:

                break;

            case R.id.about_us_join_us:


                intent.setClass(AboutUsActivity.this, ArticleDetailActivity.class);
                intent.putExtra("url",JOIN_US_URL);
                intent.putExtra("title","加入我们");
                startActivity(intent);

                break;
            case R.id.about:

                break;
        }
    }
}
