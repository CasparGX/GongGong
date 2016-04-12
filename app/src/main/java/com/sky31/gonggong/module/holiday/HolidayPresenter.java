package com.sky31.gonggong.module.holiday;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.HolidayAllModel;
import com.sky31.gonggong.model.HolidayNextModel;
import com.sky31.gonggong.model.UserModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by root on 16-4-12.
 */
public class HolidayPresenter {

    private ApiService apiService;
    private String sid, password;
    private HolidayView holidayView;

    public HolidayPresenter(HolidayView holidayView) {
        //init Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        sid = UserModel.getaCache().getAsString(Constants.Key.SID);
        password = UserModel.getaCache().getAsString(Constants.Key.ECARD_PASSWORD);
        this.holidayView = holidayView;
    }

    public void getHolidayNext(){
        holidayView.onGetHolidayNext();
        Call<HolidayNextModel> call = apiService.getHolidayNext();
        call.enqueue(new Callback<HolidayNextModel>() {
            @Override
            public void onResponse(Response<HolidayNextModel> response, Retrofit retrofit) {
                int code = response.body().getCode();
                if (code == 0) {
                    HolidayNextModel holidayNextModel = response.body();
                    holidayView.finishGetHolidayNext(holidayNextModel);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                holidayView.finishGetHolidayNext(null);
            }
        });
    }

    public void getHolidayAll(){
        Call<HolidayAllModel> call = apiService.getHolidayAll();
        call.enqueue(new Callback<HolidayAllModel>() {
            @Override
            public void onResponse(Response<HolidayAllModel> response, Retrofit retrofit) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
