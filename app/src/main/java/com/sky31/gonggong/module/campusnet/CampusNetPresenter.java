package com.sky31.gonggong.module.campusnet;

import android.content.Context;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.CampusNetwork;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.module.main.CampusNetView;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-2-29.
 */
public class CampusNetPresenter {
    private CampusNetView campusNetView;
    private ApiService apiService;
    private Context context;

    private String sid = null;
    private String password = null;

    public void init() {
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        initSidAndPassword();
    }

    public CampusNetPresenter(CampusNetView campusNetView) {
        this.campusNetView = campusNetView;
        init();
    }

    private void initSidAndPassword() {
        //init sid and password
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
    }


    public void getCampusNetwork() {
        final Call<CampusNetwork> call = apiService.getCampusNetwork(sid);
        call.enqueue(new Callback<CampusNetwork>() {
            @Override
            public void onResponse(Response<CampusNetwork> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    CampusNetwork campusNetwork = response.body();
                    campusNetwork.setCache();
                    campusNetView.getCampusNetwork(code, campusNetwork);
                } else if (code == 1) {
                    UserModel.setCacheNone(campusNetView.getViewContext());
                    campusNetView.getCampusNetwork(code, null);
                } else {
                    campusNetView.getCampusNetwork(code, null);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
}
