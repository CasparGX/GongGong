package com.sky31.gonggong.module.secondhand;

import com.google.gson.JsonArray;
import com.sky31.gonggong.base.BasePresenter;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.SwzlSearchResult;
import com.sky31.gonggong.util.ApiException;
import com.sky31.gonggong.util.BaseSubscriber;
import com.sky31.gonggong.util.Debug;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wukunguang on 16-5-10.
 */
public class SecondhandPresent extends BasePresenter{

    private SecondhandView secondhandView;
    private ApiService apiService;

    public SecondhandPresent(SecondhandView secondhandView) {
        this.secondhandView = secondhandView;
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.SECOND_HAND)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
    }


    public void requestServer(){

        apiService.getScondModelList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<JsonArray>() {

                    @Override
                    public void onNext(JsonArray jsonArray) {
                        JsonArray modelList = jsonArray;
                        secondhandView.getSecondhandData(modelList, 0);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        secondhandView.getSecondhandData(null,2);
                    }

                });

        /*Call<JsonArray> call = apiService.getScondModelList();

        call.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Response<JsonArray> response, Retrofit retrofit) {
                JsonArray modelList = response.body();
                Debug.i("secondlist", modelList + "");
                //UserModel.getaCache().put(Constants.Key.S_GOODS_LIST, modelList);
                //Log.e("TAG",response.body().getModels().size()+"");
                *//*JSONObject jsonArray = null;
                try {
                    jsonArray = new JSONArray(modelList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*//*

                secondhandView.getSecondhandData(modelList, 0);

            }

            @Override
            public void onFailure(Throwable t) {
                Debug.d("secondhand", t.getCause() + "   " + t.getMessage());
                secondhandView.getSecondhandData(null,2);
            }
        });*/

    }


}
