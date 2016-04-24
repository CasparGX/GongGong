package com.sky31.gonggong.module.swzl;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PublishSwzlActivity extends BaseActivity implements PublishSwzlFragment.OnFragmentInteractionListener {


    @Bind(R.id.swzl_publish_toolbar)
    Toolbar swzlPublishToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_swzl);
        ButterKnife.bind(this);

        initToolbar();
        PublishSwzlFragment fragment = PublishSwzlFragment.newInstance("11", "22");

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.swzl_publish_fragment, fragment);
        transaction.commit();


    }

    private void initToolbar(){

        //setSupportActionBar(swzlPublishToolbar);
        //swzlPublishToolbar.inflateMenu(R.menu.swzl_publish_toolbar_menu);
        //设置返回监听。
        swzlPublishToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        swzlPublishToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.swzl_publish:


                }

                return false;
            }
        });

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
