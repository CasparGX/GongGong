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

    private String initTitle;
    private  ArticleListQuery query;

    public ArticleListActivityFragment(String title) {

        initTitle = title;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //int w = 'x'-'y';
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        ButterKnife.bind(this, view);

        query = new ArticleListQuery();
        initData();

        //listAdapter = new ArticleListAdapter(getActivity(),model);



        return view;
    }

    private void initData() {

        query.setCatname(initTitle);
        query.setLimit(50);
        query.setCheckID(0);
        query.setCo("act_time");
        query.setOrder("id DESC");
        ArticlePresenter presenter = new ArticlePresenter(this);
        presenter.initReqService(query.getHashMap());
        presenter.getDatas();

    }

    @Override
    public void getAriticleList(int code ,ArticleListModel model) {

        switch (code){
            case 0:
                this.model = model;
                setData();
                break;
            default:
                CommonFunction.errorToast(getActivity(),code);
        }

    }

    //加载 ListView
    public void setData(){

        ArticleListAdapter datapter = new ArticleListAdapter(getActivity(),model);
        articleListview.setAdapter(datapter);
        datapter.notifyDataSetChanged();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
