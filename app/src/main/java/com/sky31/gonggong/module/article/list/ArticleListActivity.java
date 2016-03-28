package com.sky31.gonggong.module.article.list;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.sky31.gonggong.R;

public class ArticleListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        ArticleListActivityFragment fragment = new ArticleListActivityFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.article_list,fragment).commit();
    }

}
