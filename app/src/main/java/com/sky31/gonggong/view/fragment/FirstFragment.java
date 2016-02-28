package com.sky31.gonggong.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sky31.gonggong.R;
import com.sky31.gonggong.app.App;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    @Bind(R.id.blank_layout)
    LinearLayout blankLayout;
    @Bind(R.id.project_layout)
    LinearLayout projectLayout;
    @Bind(R.id.countdown_layout)
    LinearLayout countdownLayout;

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
        homeLayoutHeight = App.getInstance().getHomeLayoutHeight();
        ViewGroup.LayoutParams blankLayoutParam = blankLayout.getLayoutParams();
        ViewGroup.LayoutParams projectLayoutParam = projectLayout.getLayoutParams();
        ViewGroup.LayoutParams countdownLayoutParam = countdownLayout.getLayoutParams();
        blankLayoutParam.height = homeLayoutHeight/3;
        projectLayoutParam.height = homeLayoutHeight/3;
        countdownLayoutParam.height = homeLayoutHeight/3;
        blankLayout.setLayoutParams(blankLayoutParam);
        projectLayout.setLayoutParams(projectLayoutParam);
        countdownLayout.setLayoutParams(countdownLayoutParam);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
