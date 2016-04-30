package com.sky31.gonggong.module.grade.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky31.gonggong.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradeReportFragment extends Fragment {


    public GradeReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grade_report, container, false);
    }

}
