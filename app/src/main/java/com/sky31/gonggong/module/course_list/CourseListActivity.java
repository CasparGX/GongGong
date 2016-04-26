package com.sky31.gonggong.module.course_list;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.util.ACache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseListActivity extends BaseActivity implements CourseListView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        Map<String,String> map = new HashMap<>();

        map.put("style","3");

        ACache aCache = UserModel.getaCache();

        map.put(Constants.Key.SID,aCache.getAsString(Constants.Key.SID));
        map.put(Constants.Key.PASSWORD,aCache.getAsString(Constants.Key.PASSWORD));

        CoursePresent present = new CoursePresent(this,CourseListActivity.this);
        present.getCourseList(map);


    }

    @Override
    public CourseListModel courseList(CourseListModel courseList, int code) {

        if (code==0){
            for (List<CourseListModel.DataBean> listModels: courseList.getData()){

                for (CourseListModel.DataBean data: listModels){

                    Log.d("list",data.getWeek());
                    Log.d("list",data.getCourse());

                }

            }
        }
        else {
            CommonFunction.errorToast(CourseListActivity.this,code);
        }

        return courseList;

    }
}
