package com.sky31.gonggong.module.article.list;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.model.ArticleListModel;
import com.sky31.gonggong.module.article.ArticlePresenter;
import com.sky31.gonggong.module.article.detail.ArticleDetailActivity;
import com.sky31.gonggong.util.Debug;

import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArticleListActivityFragment extends Fragment implements ArticleListView {

    @Bind(R.id.article_listview)
    ListView articleListview;


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

        //listAdapter = new ArticleActivityListAdapter(getActivity(),model);



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


        if (initTitle.equals("活动日历")){
            ArticleActivityListAdapter datapter = new ArticleActivityListAdapter(getActivity(),model);
            articleListview.setDividerHeight(0);
            articleListview.setAdapter(datapter);
            datapter.notifyDataSetChanged();
        }
        else {
            model.getMsg();
            Log.d("list_fragment",model.getMsg());
            Log.d("list_fragment",model.getData().size()+"");
            ArticleCommonListAdapter adapter = new ArticleCommonListAdapter(getActivity(),model.getData());
            articleListview.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        articleListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = model.getData().get(position).getUrl();

                Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title","文章详情");
                startActivity(intent);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
