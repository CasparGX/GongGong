package com.sky31.gonggong.module.article.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.model.ArticleListModel;
import com.sky31.gonggong.module.article.ArticlePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleListActivityFragment extends Fragment implements ArticleListView {

    @Bind(R.id.article_listview)
    ListView articleListview;

    ArticleListAdapter listAdapter;

    private ArticleListModel model;
    public ArticleListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        ButterKnife.bind(this, view);

        initData();

        listAdapter = new ArticleListAdapter(getActivity(),model);

        articleListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        articleListview.setAdapter(listAdapter);

        return view;
    }

    private void initData() {

        ArticleListQuery query = new ArticleListQuery();
        query.setCatname("活动日历");
        query.setLimit(50);
        query.setCheckID(0);
        query.setCo("act_time");
        ArticlePresenter presenter = new ArticlePresenter(this,query.getHashMap());
        presenter.getDatas();
    }

    @Override
    public void getAriticleList(int code ,ArticleListModel model) {

        switch (code){
            case 0:
                this.model = model;
                break;
            default:
                CommonFunction.errorToast(getActivity(),code);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
