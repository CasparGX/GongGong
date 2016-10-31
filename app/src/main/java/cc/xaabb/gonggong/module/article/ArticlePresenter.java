package cc.xaabb.gonggong.module.article;

import cc.xaabb.gonggong.base.BasePresenter;
import cc.xaabb.gonggong.model.ArticleListModel;
import cc.xaabb.gonggong.network.ArticleService;
import cc.xaabb.gonggong.module.article.list.ArticleListView;
import cc.xaabb.gonggong.network.ApiException;
import cc.xaabb.gonggong.network.BaseSubscriber;

import java.util.HashMap;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wukunguang on 16-3-26.
 */
public class ArticlePresenter extends BasePresenter {
    private ArticleListView listView;
    private ArticleService service;

    //请求参数的map
    private HashMap<String,String> map;

    public ArticlePresenter(ArticleListView listView) {
        this.listView = listView;
    }

    public void initReqService(HashMap<String,String> map){
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        service = mRetrofit.create(ArticleService.class);
        this.map = map;
    }

    public void getDatas(){
        service.getArticleList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<ArticleListModel>() {

                    @Override
                    public void onNext(ArticleListModel articleListModel) {
                        ArticleListModel model = articleListModel;
                        listView.getAriticleList(model.getCode(),model);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        listView.getAriticleList(-2,null);
                    }

                });

        /*Call<ArticleListModel> modelCall = service.getArticleList(map);
        modelCall.enqueue(new Callback<ArticleListModel>() {
            @Override
            public void onResponse(Call<ArticleListModel> call, Response<ArticleListModel> response) {

            }

            @Override
            public void onFailure(Call<ArticleListModel> call, Throwable t) {

            }

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
        });*/

    }
}
