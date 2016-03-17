package com.sky31.gonggong.module.swzl;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Button;

import com.sky31.gonggong.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SwzlActivity extends FragmentActivity implements SwzlFragment.OnFragmentInteractionListener {


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

    FragmentManager manager = getSupportFragmentManager();

    SwzlFragment swzlFragmentOfGet = null;
    SwzlFragment swzlFragmentOfLost = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swzl);

        ButterKnife.bind(this);



        //init default fragment.
        //default fragement is get
        swzlFragmentOfGet = SwzlFragment.newInstance(xxx+"",xxx+"");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.swzl_fragment, swzlFragmentOfGet);
        transaction.commit();
        getButton.setClickable(false);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {



    }

    @OnClick(R.id.swzl_back)
    void backToLast(){
        Log.d(msg, "back");
        finish();
    }

    //jump 2 get fragment
    @OnClick(R.id.swzl_get)
    void repGetFragment(){

        FragmentTransaction transaction = manager.beginTransaction();
        if (swzlFragmentOfGet==null){
            xxx++;
            swzlFragmentOfGet = SwzlFragment.newInstance(xxx+"",xxx+"");
            transaction.add(R.id.swzl_fragment, swzlFragmentOfGet);
        }
        else {
            transaction.replace(R.id.swzl_fragment, swzlFragmentOfGet);
        }

        transaction.commit();
        //replaceFragment(swzlFragmentOfGet);
        getButton.setClickable(false);
        lostButton.setClickable(true);
        Log.d(msg, "open swzl_get_fragment!");
    }

    // jump 2 lost fragment
    @OnClick(R.id.swzl_lost)
    void repLostFragment(){

        FragmentTransaction transaction = manager.beginTransaction();
        if (swzlFragmentOfLost==null){
            xxx++;
            swzlFragmentOfLost = SwzlFragment.newInstance(xxx+"",xxx+"");
            transaction.add(R.id.swzl_fragment, swzlFragmentOfLost);
        }
        else {
            transaction.replace(R.id.swzl_fragment, swzlFragmentOfLost);
        }

        transaction.commit();
        //replaceFragment(swzlFragmentOfLost);
        lostButton.setClickable(false);
        getButton.setClickable(true);
        Log.d(msg, "open swzl_lost_fragment");
    }


    @OnClick(R.id.swzl_publish)
    void jump2PublishAct(){
        Intent intent = new Intent(SwzlActivity.this,PublishSwzlActivity.class);

        startActivity(intent);

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
