package com.sky31.gonggong.module.holiday;

import android.widget.Toast;

import com.sky31.gonggong.base.BasePresenter;
import com.sky31.gonggong.config.ApiErrorCode;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.EcardModel;
import com.sky31.gonggong.model.HolidayAllModel;
import com.sky31.gonggong.model.HolidayNextModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.util.ApiException;
import com.sky31.gonggong.util.BaseSubscriber;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-4-12.
 */
public class HolidayPresenter extends BasePresenter {

    private ApiService apiService;
    private String sid, password;
    private HolidayView holidayView;

    public HolidayPresenter(HolidayView holidayView) {
        //init Retrofit
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.ECARD_PASSWORD);
        this.holidayView = holidayView;
    }

    public void getHolidayNext(){
        holidayView.onGetHolidayNext();
        apiService.getHolidayNext()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HolidayNextModel>() {

                    @Override
                    public void onNext(HolidayNextModel holidayNextModel) {
                        HolidayNextModel _holidayNextModel = holidayNextModel;
                        _holidayNextModel.setCache();
                        holidayView.finishGetHolidayNext(_holidayNextModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        if (e.getErrorCode()== ApiErrorCode.ERROR_NO_HOLIDAY) {
                            holidayView.finishGetHolidayNext(null);
                        }
                    }

                });

        /*Call<HolidayNextModel> call = apiService.getHolidayNext();
        call.enqueue(new Callback<HolidayNextModel>() {
            @Override
            public void onResponse(Response<HolidayNextModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    HolidayNextModel holidayNextModel = response.body();
                    holidayNextModel.setCache();
                    holidayView.finishGetHolidayNext(holidayNextModel);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                holidayView.finishGetHolidayNext(null);
            }
        });*/
    }

    public void getHolidayAll(){

        apiService.getHolidayAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<HolidayAllModel>() {

                    @Override
                    public void onNext(HolidayAllModel holidayNextModel) {
                        HolidayAllModel _holidayAllModel = holidayNextModel;
                        //holidayNextModel.setCache();
                        holidayView.finishGetHolidayAll(_holidayAllModel);
                    }

                    @Override
                    public void onApiException(ApiException e) {

                    }

                });
        /*Call<HolidayAllModel> call = apiService.getHolidayAll();
        call.enqueue(new Callback<HolidayAllModel>() {
            @Override
            public void onResponse(Response<HolidayAllModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    HolidayAllModel holidayAllModel = response.body();
                    //holidayNextModel.setCache();
                    holidayView.finishGetHolidayAll(holidayAllModel);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/
    }
}
