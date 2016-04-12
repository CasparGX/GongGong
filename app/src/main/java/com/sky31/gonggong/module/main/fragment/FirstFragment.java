package com.sky31.gonggong.module.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.app.App;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.HolidayNextModel;
import com.sky31.gonggong.module.holiday.HolidayPresenter;
import com.sky31.gonggong.module.holiday.HolidayView;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    private int homeLayoutHeight;

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
        return view;
    }

    public void initLayoutHeight() {
        homeLayoutHeight = App.getApp().getHomeLayoutHeight();
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

    public void getHoliday(String action){
        HolidayPresenter holidayPresenter = new HolidayPresenter(this);
        if (action.equals(Constants.Key.HOLIDAY_ACTION_NEXT)){
            holidayPresenter.getHolidayNext();
        } else if(action.equals(Constants.Key.HOLIDAY_ACTION_ALL)){
            holidayPresenter.getHolidayNext();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onGetHolidayNext() {

    }

    @Override
    public void finishGetHolidayNext(HolidayNextModel holidayNextModel) {
        HolidayNextModel.DataEntity data = holidayNextModel.getData();
        tvHolidayDays.setText(data.getTotal_days());
        tvHolidayEnd.setText(data.getEnd_date());
        tvHolidayInterval.setText(data.getInterval());
        tvHolidayName.setText(data.getName());
        tvHolidayStart.setText(data.getStart_date());
    }

    @Override
    public void onGetHolidayAll() {

    }

    @Override
    public void finishGetHolidayAll(HolidayNextModel holidayNextModel) {

    }
}
