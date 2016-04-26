package com.sky31.gonggong.module.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sky31.gonggong.R;

import com.sky31.gonggong.module.article.list.ArticleListActivity;
import com.sky31.gonggong.module.course_list.CourseListActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    @Bind(R.id.btn_cha)
    Button btnCha;

    @Bind(R.id.openNews)
    Button btnNews;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);
        btnNews.setText("查课表");
        return view;
    }

    //查水表按钮
    @OnClick(R.id.btn_cha)
    void onClickChaShuiBiao(View view) {
        Intent intent = new Intent();
        //intent.setClass(getActivity(), TestActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.openNews)
    void onClickOpenNewsList(){
        Intent intent = new Intent();

        intent.setClass(getActivity(), CourseListActivity.class);
        startActivity(intent);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
