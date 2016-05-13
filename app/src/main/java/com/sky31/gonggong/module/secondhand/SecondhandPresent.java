package com.sky31.gonggong.module.secondhand;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.UserModel;

import org.json.JSONArray;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wukunguang on 16-5-10.
 */
public class SecondhandPresent {

    private SecondhandView secondhandView;
    private ApiService apiService;

    public SecondhandPresent(SecondhandView secondhandView) {
        this.secondhandView = secondhandView;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }


    public void requestServer(){

        Call<JSONArray> call = apiService.getScondModelList();

        call.enqueue(new Callback<JSONArray>() {
            @Override
            public void onResponse(Response<JSONArray> response, Retrofit retrofit) {
                JSONArray modelList = response.body();

                UserModel.getaCache().put(Constants.Key.S_GOODS_LIST, modelList);
                //Log.e("TAG",response.body().getModels().size()+"");
                    secondhandView.getSecondhandData(modelList,0);


            }

            @Override
            public void onFailure(Throwable t) {

                secondhandView.getSecondhandData(null,2);
            }
        });

    }


}
