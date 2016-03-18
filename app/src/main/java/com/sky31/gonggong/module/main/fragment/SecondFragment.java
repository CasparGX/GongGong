package com.sky31.gonggong.module.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sky31.gonggong.R;
import com.sky31.gonggong.module.search_mate.SearchMateActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    @Bind(R.id.btn_cha)
    Button btnCha;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //查水表按钮
    @OnClick(R.id.btn_cha)
    void onClickChaShuiBiao(View view) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), SearchMateActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
