package com.sky31.gonggong.module.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rey.material.widget.ProgressView;
import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.HolidayNextModel;
import com.sky31.gonggong.module.holiday.HolidayPresenter;
import com.sky31.gonggong.module.holiday.HolidayView;
import com.sky31.gonggong.util.ACache;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements HolidayView {


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

    private int homeLayoutHeight;
    private ACache aCache;

    private static FirstFragment instance;

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
        return view;
    }

    private void initViewData() {

        if (aCache.getAsJSONObject(Constants.Key.HOLIDAY_CACHE_NEXT)!=null){
            //cache不为空，加载cache数据，cache缓存时间在HolidayModel中的setCache（）中定义
            Gson gson = new Gson();
            HolidayNextModel holidayNextModel = new HolidayNextModel();
            HolidayNextModel.DataEntity data = gson.fromJson(aCache.getAsString(Constants.Key.HOLIDAY_CACHE_NEXT), HolidayNextModel.DataEntity.class);
            holidayNextModel.setData(data);
            finishGetHolidayNext(holidayNextModel);
        } else{

        }
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
    @OnClick(R.id.img_btn_refresh) void onClickImgBtnRefresh(View view){
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
        if (holidayNextModel!=null) {
            HolidayNextModel.DataEntity data = holidayNextModel.getData();
            tvHolidayDays.setText(data.getTotal_days()+"");
            tvHolidayEnd.setText(data.getEnd_date()+"");
            tvHolidayInterval.setText(data.getInterval()+"");
            tvHolidayName.setText(data.getName());
            tvHolidayStart.setText(data.getStart_date()+"");
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
}
