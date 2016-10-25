package com.sky31.gonggong.module.course_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.sky31.gonggong.base.BasePresenter;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.ArticleListModel;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.SwzlService;
import com.sky31.gonggong.module.main.ApiView;
import com.sky31.gonggong.util.ApiException;
import com.sky31.gonggong.util.BaseSubscriber;

import java.util.List;
import java.util.Map;

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
public class CoursePresent extends BasePresenter {

    private ApiService service;
    private Context context;
    private CourseListView courseListView;
    public CoursePresent(CourseListView listView,Context context) {
        this.context = context;
        this.courseListView = listView;
        /*Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
        service = mRetrofit.create(ApiService.class);
    }

    public void getCourseList(@NonNull Map<String, String> map){

        service.getCourse(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<CourseListModel>() {

                    @Override
                    public void onNext(CourseListModel courseListModel) {
                        CourseListModel model = courseListModel;
                        courseListView.courseList(model,0);
                    }

                    @Override
                    public void onApiException(ApiException e) {
                        courseListView.courseList(null,e.getErrorCode());
                    }

                });

        /*Call<CourseListModel> call = service.getCourse(map);
        call.enqueue(new Callback<CourseListModel>() {
            @Override
            public void onResponse(Response<CourseListModel> response, Retrofit retrofit) {
                if (response.body().getCode()==0){
                    CourseListModel model = response.body();
                    courseListView.courseList(model,0);
                }
                else {
                    courseListView.courseList(null,response.body().getCode());
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Toast.makeText(context,"网络链接异常，请稍后再试！",Toast.LENGTH_SHORT).show();
            }
        });*/

    }

}
