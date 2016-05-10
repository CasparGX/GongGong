package com.sky31.gonggong.module.main.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;
import com.sky31.gonggong.config.Constants;
import com.sky31.gonggong.model.ArticleListModel;
import com.sky31.gonggong.model.SecondhandModelList;
import com.sky31.gonggong.model.SecondhandModel;
import com.sky31.gonggong.module.article.ArticlePresenter;
import com.sky31.gonggong.module.article.detail.ArticleDetailActivity;
import com.sky31.gonggong.module.article.list.ArticleListActivity;
import com.sky31.gonggong.module.article.list.ArticleListQuery;
import com.sky31.gonggong.module.article.list.ArticleListView;
import com.sky31.gonggong.module.secondhand.SecondhandPresent;
import com.sky31.gonggong.module.secondhand.SecondhandView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class InformationFragment extends Fragment implements ArticleListView,SecondhandView,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private int infofragmentHeight;

    @Bind(R.id.second_hand_img1)
    ImageView secondHandImg1;
    @Bind(R.id.second_hand_img2)
    ImageView secondHandImg2;
    @Bind(R.id.second_hand_img3)
    ImageView secondHandImg3;
    @Bind(R.id.second_hand)
    LinearLayout secondHand;
    @Bind(R.id.activity_title_tip)
    RelativeLayout activityTitleTip;
    @Bind(R.id.activity_thumb_first)
    ImageView activityThumbFirst;
    @Bind(R.id.activity_title_first)
    TextView activityTitleFirst;
    @Bind(R.id.activity_description_first)
    TextView activityDescriptionFirst;
    @Bind(R.id.activity_first)
    LinearLayout activityFirst;
    @Bind(R.id.info_title_tip)
    RelativeLayout infoTitleTip;
    @Bind(R.id.info_thumb_first)
    ImageView infoThumbFirst;
    @Bind(R.id.info_title_first)
    TextView infoTitleFirst;
    @Bind(R.id.info_description_first)
    TextView infoDescriptionFirst;
    @Bind(R.id.info_first)
    LinearLayout infoFirst;
    @Bind(R.id.second_hand_title_tip)
    RelativeLayout secondHandTitleTip;
    @Bind(R.id.activity_info_first)
    LinearLayout activityInfoFirst;
    @Bind(R.id.info)
    LinearLayout info;



    private ArticleListModel.Data infoData;
    private ArticleListModel.Data activityData;
    private List<SecondhandModel> secondhandData;
    private static InformationFragment fragment;

    // TODO: Rename and change types of parameters


    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();



        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        ButterKnife.bind(this, view);

        fragment = this;
        initData();
        return view;
    }



    public static InformationFragment getInstance(){

        return fragment;
    }


    /***
     * 初始化容器。位于MainActivity加载时候调用。
     */
    public void layoutInit() {

        ViewGroup.LayoutParams paramsSecondhand = secondHand.getLayoutParams();
        ViewGroup.LayoutParams paramsInfo = info.getLayoutParams();
        ViewGroup.LayoutParams paramsActivity = activityInfoFirst.getLayoutParams();
        int height = (int) (CommonFunction.homeLayoutHeight - CommonFunction.convertDpToPixel((1+8),getContext()))/3-10;

        paramsSecondhand.height = height;
        paramsInfo.height = height;
        paramsActivity.height = height;

        secondHand.setLayoutParams(paramsSecondhand);
        info.setLayoutParams(paramsInfo);
        activityInfoFirst.setLayoutParams(paramsActivity);


    }

    private void initData() {

        initSecondhandData();
        initInfoData();
        initActivityData();

    }

    private void initActivityData() {

        //加载活动日历数据
        ArticleListQuery query = new ArticleListQuery();
        query.setOrder("id DESC");
        query.setLimit(1);
        query.setCatname("活动日历");
        query.setCheckID(0);
        query.setCo("act_time");
        ArticlePresenter presenter = new ArticlePresenter(this);
        presenter.initReqService(query.getHashMap());
        presenter.getDatas();
    }

    private void initInfoData() {

        //加载资讯数据
        ArticleListQuery query = new ArticleListQuery();
        query.setOrder("id DESC");
        query.setLimit(1);
        query.setCatname("学习宝典");
        query.setCheckID(0);
        ArticlePresenter presenter = new ArticlePresenter(this);
        presenter.initReqService(query.getHashMap());
        presenter.getDatas();

    }

    private void initSecondhandData() {

        //加载二手街数据
        SecondhandPresent present = new SecondhandPresent(this);
        present.requestServer();

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getAriticleList(int code, ArticleListModel model) {

        if (code==0){
            ArticleListModel.Data data = model.getData().get(0);
            if (data.getAct_time()==null || data.getAct_time().equals("")){
                infoData = data;
                initInfoView();
            }
            else {
                activityData = data;
                initActivityView();
            }
        }
        else {
            CommonFunction.errorToast(getContext(),code);
        }

    }


    /***
     * 加载活动日历视图
     */
    private void initActivityView() {


        activityTitleFirst.setText(activityData.getTitle());
        activityDescriptionFirst.setText(activityData.getDescription());

        //上边标题监听。用于进入资讯列表页
        activityTitleTip.setOnClickListener(this);
        //头条监听，用于进入资讯详情页
        activityFirst.setOnClickListener(this);

    }

    /**
     * 加载资讯视图方法。
     */
    private void initInfoView() {

        infoDescriptionFirst.setText(infoData.getDescription());
        infoTitleFirst.setText(infoData.getTitle());
        Picasso.with(getContext()).load(infoData.getThumb()).into(infoThumbFirst);
        //上边标题监听。用于进入资讯列表页
        infoTitleTip.setOnClickListener(this);
        //头条监听，用于进入资讯详情页
        infoFirst.setOnClickListener(this);

    }

    /***
     * 加载二手街前三张图片。。注意数组越界。即返回结果不足三条时候。。
     */
    private void initsecondhandView() {

        int len = this.secondhandData.size();
            SecondhandModel model1 = secondhandData.get(0);
            SecondhandModel model2 = secondhandData.get(1);
            SecondhandModel model3 = secondhandData.get(2);
        String imgUrl = Constants.Api.SECOND_HAND+"/fleaapi"+model1.getPicsrc()+model1.getPicname().getValue1();
        Log.e("URL_TAG",imgUrl);
            Picasso.with(getContext()).
                    load(imgUrl
                    ).into(secondHandImg1);
        Picasso.with(getContext()).
                load(Constants.Api.SECOND_HAND+"/fleaapi"+model2.getPicsrc()+model2.getPicname().getValue1()
                ).into(secondHandImg2);
        Picasso.with(getContext()).
                load(Constants.Api.SECOND_HAND+"/fleaapi"+model3.getPicsrc()+model3.getPicname().getValue1()
                ).into(secondHandImg3);

        //整个模块监听。用于直接跳转。
        secondHand.setOnClickListener(this);
    }
    @Override
    public void getSecondhandData(SecondhandModelList secondHandModelList, int code) {

        if (code==0){
           this.secondhandData = secondHandModelList.getModels();
            initsecondhandView();
        }
        else {
            CommonFunction.errorToast(getContext(),code);
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.activity_title_tip:
            //打开资讯的viewpager页面
            case R.id.info_title_tip:
                //打开资讯的viewpager页面
                Intent intent = new Intent(getContext(), ArticleListActivity.class);
                startActivity(intent);

                break;
            case R.id.second_hand:
                //打开二手街
                Intent intent1 = new Intent(getContext(), ArticleDetailActivity.class);
                intent1.putExtra("url","http://bug.sky31.com");
                intent1.putExtra("title","二手街");
                startActivity(intent1);
                break;
            case R.id.activity_first:
                //打开第一条文章页
                Intent intent2 = new Intent(getContext(),ArticleDetailActivity.class);
                intent2.putExtra("url",activityData.getUrl());
                intent2.putExtra("title","文章详情");
                startActivity(intent2);
                break;
            case R.id.info_first:
                //打开第一条文章页
                Intent intent3 = new Intent(getContext(),ArticleDetailActivity.class);
                intent3.putExtra("url",infoData.getUrl());
                intent3.putExtra("title","文章详情");
                startActivity(intent3);
        }
    }
}
