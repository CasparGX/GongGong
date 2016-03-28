package com.sky31.gonggong.module.article.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky31.gonggong.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleListActivityFragment extends Fragment {

    public ArticleListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_list, container, false);
    }
}
