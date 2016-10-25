package com.sky31.gonggong.module.current_week;

import android.content.Context;
import android.widget.Toast;

import com.sky31.gonggong.base.BasePresenter;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.CurrentWeekModel;
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
 * Created by wukunguang on 16-4-25.
 */
public class CurrentWeekPresent extends BasePresenter {

    private CurrentWeekView weekView;
    private ApiService apiService;
    private Context context;

    public CurrentWeekPresent(CurrentWeekView weekView,Context context) {

        this.context = context;
        this.weekView = weekView;

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        apiService = mRetrofit.create(ApiService.class);

    }

    public void requestServer(){
        apiService.getCurrentWeek()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<CurrentWeekModel>() {

                    @Override
                    public void onNext(CurrentWeekModel currentWeekModel) {
                        weekView.currentWeek(currentWeekModel,0);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        weekView.currentWeek(null,e.getErrorCode());
                    }

                });
        /*Call<CurrentWeekModel> modelCall = apiService.getCurrentWeek();

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
        });*/

    }
}
