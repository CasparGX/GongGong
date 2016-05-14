package com.sky31.gonggong.module.swzl;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rey.material.widget.FloatingActionButton;

import com.sky31.gonggong.R;
import com.sky31.gonggong.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SwzlActivity extends BaseActivity implements SwzlFragment.OnFragmentInteractionListener ,SwzlFragment2.OnFragmentInteractionListener{



    private String msg = "SWZL_MSG:";


    @Bind(R.id.toolbar_swzl)
    Toolbar swzlToolbar;

    @Bind(R.id.swzl_viewpager)
    ViewPager viewPager;


    @Bind(R.id.buttonFloat)
    FloatingActionButton publishBtn;


    @Bind(R.id.page_strip_underline)
    ImageView underLine;    //下划线

    @Bind(R.id.swzl_get)
    TextView getTextView;
    @Bind(R.id.swzl_lost)
    TextView lostTextVIew;

    private SwzlViewPagerAdapter pagerAdapter;

    SwzlFragment swzlFragmentOfGet = null;
    SwzlFragment2 swzlFragmentOfLost = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swzl);

        ButterKnife.bind(this);


        initTitleStrip();
        initViewPager();
        initToolbar();

        publishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SwzlActivity.this, PublishSwzlActivity.class);
                startActivity(intent);
            }
        });
    }




    private void initTitleStrip() {


        getTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(1,true);
                getTextView.setClickable(false);
                lostTextVIew.setClickable(true);
            }
        });

        lostTextVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(0,true);
                getTextView.setClickable(true);
                lostTextVIew.setClickable(false);
            }
        });
    }

    private void initViewPager() {

        //init default fragment.
        //default fragement is get
        List<Fragment> fragmentList = new ArrayList<>();
        swzlFragmentOfGet = SwzlFragment.newInstance(0);
        fragmentList.add(swzlFragmentOfGet);
        swzlFragmentOfLost = SwzlFragment2.newInstance(1);




        fragmentList.add(swzlFragmentOfLost);


        viewPager.setOffscreenPageLimit(1);
        pagerAdapter = new SwzlViewPagerAdapter(getSupportFragmentManager(),fragmentList,null);
        viewPager.setAdapter(pagerAdapter);


        viewPager.setCurrentItem(0);
        getTextView.setTextColor(getResources().getColor(R.color.grey_c));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



                if (positionOffset > 0 && positionOffset < 1) {
                    underLine.setTranslationX(positionOffsetPixels / 2);
                    //Log.d("offset Pix", positionOffset + "");
                }
            }

            @Override
            public void onPageSelected(int position) {


                //设置顶部标题，根据当前滑动来设置可用性。
                if (position == 0){
                    getTextView.setClickable(true);
                    lostTextVIew.setClickable(false);
                    lostTextVIew.setTextColor(getResources().getColor(R.color.white));
                    getTextView.setTextColor(getResources().getColor(R.color.grey_c));

                }
                else if (position == 1){
                    getTextView.setClickable(false);
                    lostTextVIew.setClickable(true);
                    getTextView.setTextColor(getResources().getColor(R.color.white));
                    lostTextVIew.setTextColor(getResources().getColor(R.color.grey_c));

                }

                Log.d("page selected",position+"");

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initToolbar() {
        //setSupportActionBar(swzlToolbar);

        Resources resources = getResources();
        swzlToolbar.setNavigationIcon(resources.getDrawable(R.drawable.ic_arrow_back));

        //设置侧边栏返回。
        swzlToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        swzlToolbar.inflateMenu(R.menu.swzl_toolbar_menu);
        swzlToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.swzl_search:
                        Toast.makeText(SwzlActivity.this,"搜索",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.swzl_refresh:
                        swzlFragmentOfGet.initData();
                        swzlFragmentOfLost.initData();
                        Toast.makeText(SwzlActivity.this,"刷新",Toast.LENGTH_SHORT).show();

                }
                return false;
            }
        });
    }


    @Override
    public void onFragmentInteraction(SwzlFragment fragment) {

        //fragment.initData();

        Log.d(msg, fragment.getTag() + "->>>>>>");
    }

//    @OnClick(R.id.swzl_back)
//    void backToLast(){
//        Log.d(msg, "back");
//        finish();
//    }

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
