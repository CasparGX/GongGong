package com.sky31.gonggong.module.current_week;

import android.content.Context;
import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.CurrentWeekModel;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by wukunguang on 16-4-25.
 */
public class CurrentWeekPresent {

    private CurrentWeekView weekView;
    private ApiService apiService;
    private Context context;

    public CurrentWeekPresent(CurrentWeekView weekView,Context context) {

        this.context = context;
        this.weekView = weekView;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public void requestServer(){

        Call<CurrentWeekModel> modelCall = apiService.getCurrentWeek();

        modelCall.enqueue(new Callback<CurrentWeekModel>() {
            @Override
            public void onResponse(Response<CurrentWeekModel> response, Retrofit retrofit) {

                if (response.body().getCode()==0){
                    weekView.currentWeek(response.body(),0);
                }
                else {
                    weekView.currentWeek(null,response.body().getCode());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                Toast.makeText(context,"无法获取数据，请稍后再试。",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
