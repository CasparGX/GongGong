package com.sky31.gonggong.module.course_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ApiService;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.SwzlService;
import com.sky31.gonggong.module.main.ApiView;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by wukunguang on 16-4-25.
 */
public class CoursePresent {

    private ApiService service;
    private Context context;
    private CourseListView courseListView;
    public CoursePresent(CourseListView listView,Context context) {
        this.context = context;
        this.courseListView = listView;
        Retrofit retrofit  = new Retrofit.Builder()
                .baseUrl(Constants.Api.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public void getCourseList(@NonNull Map<String, String> map){

        Call<CourseListModel> call = service.getCourse(map);


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
        });

    }

}
