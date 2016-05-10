package com.sky31.gonggong.module.secondhand;

import android.content.Context;
import android.util.Log;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.SecondhandModelList;

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

        Call<SecondhandModelList> call = apiService.getScondModelList();

        call.enqueue(new Callback<SecondhandModelList>() {
            @Override
            public void onResponse(Response<SecondhandModelList> response, Retrofit retrofit) {
                SecondhandModelList modelList = response.body();


                Log.e("TAG",response.body().getModels().size()+"");
                    secondhandView.getSecondhandData(modelList,0);


            }

            @Override
            public void onFailure(Throwable t) {

                secondhandView.getSecondhandData(null,2);
            }
        });

    }


}
