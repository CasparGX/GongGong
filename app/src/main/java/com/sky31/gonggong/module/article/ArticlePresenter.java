package com.sky31.gonggong.module.article;

import com.google.gson.Gson;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ArticleListModel;
import com.sky31.gonggong.model.ArticleService;
import com.sky31.gonggong.module.article.list.ArticleListView;

import java.util.HashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wukunguang on 16-3-26.
 */
public class ArticlePresenter {
    private ArticleListView listView;
    private ArticleService service;

    //请求参数的map
    private HashMap<String,String> map;


    public ArticlePresenter(ArticleListView listView) {



        this.listView = listView;


    }

    public void initReqService(HashMap<String,String> map){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ArticleService.class);
        this.map = map;
    }

    public void getDatas(){

        Call<ArticleListModel> modelCall = service.getArticleList(map);
        modelCall.enqueue(new Callback<ArticleListModel>() {
            @Override
            public void onResponse(Response<ArticleListModel> response, Retrofit retrofit) {
                ArticleListModel model = response.body();

                listView.getAriticleList(model.getCode(),model);

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                listView.getAriticleList(-2,null);
            }
        });

    }
}
