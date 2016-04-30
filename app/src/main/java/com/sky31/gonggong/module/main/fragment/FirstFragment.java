package com.sky31.gonggong.module.main.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rey.material.widget.ProgressView;
import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.CourseListModel;
import com.sky31.gonggong.model.CurrentWeekModel;
import com.sky31.gonggong.model.HolidayNextModel;
import com.sky31.gonggong.module.course_list.CourseListRequestProxy;
import com.sky31.gonggong.module.course_list.CourseListView;
import com.sky31.gonggong.module.course_list.CoursePresent;
import com.sky31.gonggong.module.course_list.CurrentCourseItemFragment;
import com.sky31.gonggong.module.current_week.CurrentWeekPresent;
import com.sky31.gonggong.module.current_week.CurrentWeekProxy;
import com.sky31.gonggong.module.current_week.CurrentWeekView;
import com.sky31.gonggong.module.holiday.HolidayPresenter;
import com.sky31.gonggong.module.holiday.HolidayView;
import com.sky31.gonggong.module.main.CurrentCoursePageAdapter;
import com.sky31.gonggong.util.ACache;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements HolidayView,CourseListView,CurrentWeekView {


    @Bind(R.id.blank_layout)
    LinearLayout blankLayout;
    @Bind(R.id.project_layout)
    LinearLayout projectLayout;
    @Bind(R.id.countdown_layout)
    LinearLayout countdownLayout;
    @Bind(R.id.tv_holiday_name)
    TextView tvHolidayName;
    @Bind(R.id.tv_holiday_start)
    TextView tvHolidayStart;
    @Bind(R.id.tv_holiday_end)
    TextView tvHolidayEnd;
    @Bind(R.id.tv_holiday_days)
    TextView tvHolidayDays;
    @Bind(R.id.tv_holiday_interval)
    TextView tvHolidayInterval;
    @Bind(R.id.img_btn_refresh)
    ImageView imgBtnRefresh;
    @Bind(R.id.pv_holiday)
    ProgressView pvHoliday;
    @Bind(R.id.current_course_pager)
    ViewPager currentCoursePager;


    private int homeLayoutHeight;
    private ACache aCache;

    private static FirstFragment instance;

    private int clickCount = 0;//推迟天数
    private CourseListModel courseListModel;
    private int currentWeek = 0;

    private List<CourseListModel.DataBean> resultList;
    public static FirstFragment getInstance() {
        return instance;
    }

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        ButterKnife.bind(this, view);
        instance = this;
        aCache = ACache.get(getActivity());
        initViewData();
        initCurrentCourse();
        return view;
    }

    private void initViewData() {

        if (aCache.getAsJSONObject(Constants.Key.HOLIDAY_CACHE_NEXT) != null) {
            //cache不为空，加载cache数据，cache缓存时间在HolidayModel中的setCache（）中定义
            Gson gson = new Gson();
            HolidayNextModel holidayNextModel = new HolidayNextModel();
            HolidayNextModel.DataEntity data = gson.fromJson(aCache.getAsString(Constants.Key.HOLIDAY_CACHE_NEXT), HolidayNextModel.DataEntity.class);
            holidayNextModel.setData(data);
            finishGetHolidayNext(holidayNextModel);
        } else {


        }
    }

    private void initCurrentCourse(){

        courseListModel = new CourseListModel();
        if (aCache.getAsJSONObject(Constants.Key.COURSE_LIST)!=null){
            Gson gson = new Gson();

            List<List<CourseListModel.DataBean>> dataList = gson.fromJson(
                    aCache.getAsString(Constants.Key.COURSE_LIST),
                    new TypeToken<List<List<CourseListModel.DataBean>>>(){}.getType());

            courseListModel.setData(dataList);

            CurrentWeekPresent present = new CurrentWeekPresent(this,getContext());
            present.requestServer();

        }
        else {

            //设置代理请求模式。
            CourseListRequestProxy proxy = new CourseListRequestProxy(getContext(),this);
            proxy.setReauestProxy();





        }

    }

    /***
     * 解析返回字符串。获取当天上的课程。
     * @return
     */
    private void getCurrentCourse(List<List<CourseListModel.DataBean>> dataList){


        int pushDayOfWeek = CommonFunction.countCurrentDay(clickCount);
        int pushWeek = CommonFunction.countCurrentWeek(clickCount,currentWeek);

        resultList = new ArrayList<>();
        for (List<CourseListModel.DataBean>  modelList : dataList){

            for (CourseListModel.DataBean dataBean : modelList){

                //首先对周进行判定，再对当天进行判定。
                if (dataBean.getWeek().contains(pushWeek+"") &&
                        dataBean.getDay().equals(pushDayOfWeek+"")){
                    resultList.add(dataBean);
                }

            }
        }

        initCurrentCourseView();
    }

    /***
     *  加载当日课程表视图。
     */
    private void initCurrentCourseView(){

        List<Fragment> fragmentList = new ArrayList<>();
        int lenth = resultList.size();
        for (int i=0; i < lenth;i++){
            CurrentCourseItemFragment fragment =
                    CurrentCourseItemFragment.newInstance(resultList.get(i));
            fragmentList.add(fragment);

        }

        CurrentCoursePageAdapter adapter =
                new CurrentCoursePageAdapter(getActivity().getSupportFragmentManager(),fragmentList);

        currentCoursePager.setAdapter(adapter);


    }

    public void initLayoutHeight() {
        homeLayoutHeight = CommonFunction.getHomeLayoutHeight();
        ViewGroup.LayoutParams blankLayoutParam = blankLayout.getLayoutParams();
        ViewGroup.LayoutParams projectLayoutParam = projectLayout.getLayoutParams();
        ViewGroup.LayoutParams countdownLayoutParam = countdownLayout.getLayoutParams();
        blankLayoutParam.height = homeLayoutHeight / 3;
        projectLayoutParam.height = homeLayoutHeight / 3;
        countdownLayoutParam.height = homeLayoutHeight / 3;
        blankLayout.setLayoutParams(blankLayoutParam);
        projectLayout.setLayoutParams(projectLayoutParam);
        countdownLayout.setLayoutParams(countdownLayoutParam);
    }

    public void getHoliday(String action) {
        HolidayPresenter holidayPresenter = new HolidayPresenter(this);
        if (action.equals(Constants.Key.HOLIDAY_ACTION_NEXT)) {
            holidayPresenter.getHolidayNext();
        } else if (action.equals(Constants.Key.HOLIDAY_ACTION_ALL)) {
            holidayPresenter.getHolidayNext();
        }
    }

    /* onClick */
    @OnClick(R.id.img_btn_refresh)
    void onClickImgBtnRefresh(View view) {
        getHoliday(Constants.Key.HOLIDAY_ACTION_NEXT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onGetHolidayNext() {
        imgBtnRefresh.setVisibility(View.GONE);
        pvHoliday.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishGetHolidayNext(HolidayNextModel holidayNextModel) {
        if (holidayNextModel != null) {
            HolidayNextModel.DataEntity data = holidayNextModel.getData();
            tvHolidayDays.setText(data.getTotal_days() + "");
            tvHolidayEnd.setText(data.getEnd_date() + "");
            tvHolidayInterval.setText(data.getInterval() + "");
            tvHolidayName.setText(data.getName());
            tvHolidayStart.setText(data.getStart_date() + "");
        }


        imgBtnRefresh.setVisibility(View.VISIBLE);
        pvHoliday.setVisibility(View.GONE);
    }

    @Override
    public void onGetHolidayAll() {

    }

    @Override
    public void finishGetHolidayAll(HolidayNextModel holidayNextModel) {

    }

    /**
     * 当缓存数据不存在时候。调用该回调接口。
     * @param courseList
     * @param code
     * @return
     */
    @Override
    public CourseListModel courseList(CourseListModel courseList, int code) {

        if (code==0){
            courseListModel = courseList;
            courseListModel.setCache();
            CurrentWeekProxy weekProxy = new CurrentWeekProxy(getContext(),this);
            weekProxy.setRequestProxy();
        }
        else {
            CommonFunction.errorToast(getContext(),code);
        }
        return courseList;
    }

    /***
     * //用于请求当前周数的回调接口。
     * @param model
     * @param code
     */

    @Override
    public void currentWeek(CurrentWeekModel model, int code) {

        if (code==0){
            currentWeek = model.getData().getWeek();
            if (courseListModel.getData()!=null){
                getCurrentCourse(courseListModel.getData());
            }

        }
        else {
            CommonFunction.errorToast(getContext(),code);
        }

    }

}
