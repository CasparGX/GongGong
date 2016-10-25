package com.sky31.gonggong.module.search_mate;

import com.sky31.gonggong.base.BasePresenter;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.CurrentWeekModel;
import com.sky31.gonggong.model.MateInfoModel;
import com.sky31.gonggong.model.UserModel;
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
 * Created by root on 16-3-25.
 */
public class SearchMatePresenter extends BasePresenter{
    private ApiService apiService;
    private String sid, password;
    private SearchMateView searchMateView;

    public SearchMatePresenter(SearchMateView searchMateView) {
        //init Retrofit
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.PASSWORD);
        this.searchMateView = searchMateView;
    }


    public void getMateInfo(String sid, String name, String card, String type) {
        searchMateView.onGetSearchMate();
        apiService.getMateInfo(sid, name, card, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<MateInfoModel>() {

                    @Override
                    public void onNext(MateInfoModel mateInfoModel) {
                        searchMateView.finishGetSearchMate(mateInfoModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        searchMateView.finishGetSearchMate(null);
                    }

                });

        /*Call<MateInfoModel> call = apiService.getMateInfo(sid, name, card, type);
        call.enqueue(new Callback<MateInfoModel>() {
            @Override
            public void onResponse(
                    Response<MateInfoModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                Debug.i("code", code + "");
                if (code == 0) {
                    MateInfoModel mateInfoModel = response.body();
                    searchMateView.finishGetSearchMate(mateInfoModel);
                } else if (code == 5) {
                    searchMateView.finishGetSearchMate(null);
                } else {
                    searchMateView.finishGetSearchMate(null);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Debug.i("onFailue", t.getCause() + "\n" + t.getMessage());
                searchMateView.finishGetSearchMate(null);
            }
        });*/
    }
}
