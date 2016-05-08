package com.sky31.gonggong.module.course_list;

import android.content.Context;
import android.widget.Toast;

import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.UserModel;
import com.sky31.gonggong.util.ACache;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wukunguang on 16-4-27.
 */
public class CourseListRequestProxy {


    private CourseListView courseListView;
    private Context context;

    public CourseListRequestProxy(Context context, CourseListView courseListView) {
        this.context = context;
        this.courseListView = courseListView;
    }

    //设置代理。
    public void setReauestProxy(){

        Map<String,String> map = new HashMap<>();
        ACache aCache = UserModel.getaCache();
        if (aCache.getAsString(Constants.Key.SID)!=null && aCache.getAsString(Constants.Key.PASSWORD)!=null){
            map.put("style","3");
            map.put(Constants.Key.SID,aCache.getAsString(Constants.Key.SID));
            map.put(Constants.Key.PASSWORD,aCache.getAsString(Constants.Key.PASSWORD));
            CoursePresent present = new CoursePresent(courseListView, context);
            present.getCourseList(map);
        }
        else {
            Toast.makeText(context,"还未登陆，请先登陆！",Toast.LENGTH_SHORT).show();
        }

    }
}
