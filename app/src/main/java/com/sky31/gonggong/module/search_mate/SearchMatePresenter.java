package com.sky31.gonggong.module.search_mate;

import android.support.annotation.Nullable;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.MateInfoModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.util.Debug;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-3-25.
 */
public class SearchMatePresenter {
    private ApiService apiService;
    private String sid, password;
    private SearchMateView searchMateView;

    public SearchMatePresenter(SearchMateView searchMateView) {
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
        this.searchMateView = searchMateView;
    }


    public void getMateInfo(@Nullable String sid, @Nullable String name, @Nullable String card, String type) {
        Call<MateInfoModel> call = apiService.getMateInfo(sid, name, card, type);
        call.enqueue(new Callback<MateInfoModel>() {
            @Override
            public void onResponse(Response<MateInfoModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    MateInfoModel mateInfoModel = response.body();
                    for (MateInfoModel.DataEntity item : mateInfoModel.getData()) {
                        Debug.i("MateInfo", item.getName());
                    }
                } else if (code == 1) {


                } else {

                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
