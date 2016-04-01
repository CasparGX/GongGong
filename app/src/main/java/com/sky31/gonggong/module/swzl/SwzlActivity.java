package com.sky31.gonggong.module.swzl;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import com.sky31.gonggong.R;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwzlActivity extends FragmentActivity implements SwzlFragment.OnFragmentInteractionListener ,SwzlFragment2.OnFragmentInteractionListener{


    @Bind(R.id.swzl_back)
    Button backButton ;
    @Bind(R.id.swzl_get)
    Button getButton ;
    @Bind(R.id.swzl_lost)
    Button lostButton ;
    @Bind(R.id.swzl_publish)
    Button publishButton;

    private String msg = "SWZL_MSG:";
    private int xxx = 0;

    private View view;

    @Bind(R.id.swzl_viewpager)
    ViewPager viewPager;

    @Bind(R.id.swzl_pager_title)
    PagerTitleStrip pagerTitleStrip;


    private SwzlViewPagerAdapter pagerAdapter;
    private PagerTitleStrip titleStrip;
    SwzlFragment swzlFragmentOfGet = null;
    SwzlFragment2 swzlFragmentOfLost = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swzl);

        ButterKnife.bind(this);



        //init default fragment.
        //default fragement is get
        List<Fragment> fragmentList = new ArrayList<>();
        swzlFragmentOfGet = SwzlFragment.newInstance(0);
        fragmentList.add(swzlFragmentOfGet);
        swzlFragmentOfLost = SwzlFragment2.newInstance(1);

        pagerTitleStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);

        fragmentList.add(swzlFragmentOfLost);
        List<String> titles = new ArrayList<>();
        titles.add("找失主");
        titles.add("丢东西");
        viewPager.setOffscreenPageLimit(1);
        pagerAdapter = new SwzlViewPagerAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(pagerAdapter);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getButton.setClickable(false);

    }


    @Override
    public void onFragmentInteraction(SwzlFragment fragment) {

        //fragment.initData();

        Log.d(msg, fragment.getTag() + "->>>>>>");
    }

    @OnClick(R.id.swzl_back)
    void backToLast(){
        Log.d(msg, "back");
        finish();
    }

//    //jump 2 get fragment
//    @OnClick(R.id.swzl_get)
//    void repGetFragment(){
//
//        transaction = manager.beginTransaction();
//        if (swzlFragmentOfGet==null){
//            Log.d("swzl_d","get is null ");
//            swzlFragmentOfGet = SwzlFragment.newInstance(0);
//            transaction.replace(R.id.swzl_fragment, swzlFragmentOfGet, "getFragment");
//        } else {
//            Log.d("swzl_d", "get is not null ");
//
//            transaction.replace(R.id.swzl_fragment, swzlFragmentOfGet,"getFragment");
//        }
//
//
//        swzlFragmentOfGet.initData();
//        transaction.commit();
//
//
//
//
//        //replaceFragment(swzlFragmentOfGet);
//        getButton.setClickable(false);
//        lostButton.setClickable(true);
//        Log.d(msg, "open swzl_get_fragment!");
//    }
//
//    // jump 2 lost fragment
//    @OnClick(R.id.swzl_lost)
//    void repLostFragment(){
//
//        transaction = manager.beginTransaction();
//
//        if (swzlFragmentOfLost==null){
//            xxx++;
//            Log.d("swzl_d","lost is null ");
//            swzlFragmentOfLost = SwzlFragment.newInstance(1);
//
//            transaction.replace(R.id.swzl_fragment, swzlFragmentOfLost, "lostFragment");
//
//        }
//        else {
//            Log.d("swzl_d","lost is not null ");
//
//
//            transaction.replace(R.id.swzl_fragment, swzlFragmentOfLost, "lostFragment");
//            Log.d("swzl_d","replace complate");
//        }
//
//        swzlFragmentOfLost.initData();
//        transaction.commit();
//
//
//
//
//
//        //replaceFragment(swzlFragmentOfLost);
//        lostButton.setClickable(false);
//        getButton.setClickable(true);
//        Log.d(msg, "open swzl_lost_fragment");
//    }


    @OnClick(R.id.swzl_publish)
    void jump2PublishAct(){
        Intent intent = new Intent(SwzlActivity.this,PublishSwzlActivity.class);

        startActivity(intent);

    }

    @Override
    public void onFragmentInteraction(SwzlFragment2 fragment) {

    }


    public interface FinishInit{
        void onFinish();

    }


    // replace current fragment
//    private void replaceFragment(SwzlFragment fragment){
//
//        if (fragment==null){
//            xxx++;
//            fragment = SwzlFragment.newInstance(xxx+"",xxx+"");
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.swzl_fragment, fragment);
//            transaction.commit();
//            Log.d(msg,"init a swzl_get_fragment");
//        }
//        else {
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.replace(R.id.swzl_fragment, fragment);
//            transaction.commit();
//        }
//
//
//    }


}
