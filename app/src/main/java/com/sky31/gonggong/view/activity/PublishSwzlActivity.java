package com.sky31.gonggong.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sky31.gonggong.R;
import com.sky31.gonggong.view.fragment.PublishSwzlFragment;

public class PublishSwzlActivity extends FragmentActivity implements PublishSwzlFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_swzl);

        PublishSwzlFragment fragment = PublishSwzlFragment.newInstance("11", "22");

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.swzl_publish_fragment, fragment);
        transaction.commit();


    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
