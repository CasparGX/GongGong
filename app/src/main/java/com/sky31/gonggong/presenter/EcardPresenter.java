package com.sky31.gonggong.presenter;

import android.content.Context;
import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.view.EcardView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-2-29.
 */
public class EcardPresenter {
    private EcardView ecardView;

    public EcardPresenter(EcardView ecardView) {
        this.ecardView = ecardView;
    }


    public void getBalance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<EcardModel> call = apiService.getBalance("2013960837","191515");
        call.enqueue(new Callback<EcardModel>() {
            @Override
            public void onResponse(Response<EcardModel> response, Retrofit retrofit) {
                EcardModel ecardModel = response.body();
                ecardView.getBalance(ecardModel);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText((Context) ecardView,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
