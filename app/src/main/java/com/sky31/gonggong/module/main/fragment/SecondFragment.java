package com.sky31.gonggong.module.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.sky31.gonggong.R;
import com.sky31.gonggong.model.ArticleListModel;
import com.sky31.gonggong.module.article.ArticlePresenter;
import com.sky31.gonggong.module.article.detail.ArticleDetailActivity;
import com.sky31.gonggong.module.article.list.ArticleListQuery;
import com.sky31.gonggong.module.article.list.ArticleListView;
import com.sky31.gonggong.module.course_list.CourseListActivity;
import com.sky31.gonggong.module.grade.GradeActivity;
import com.sky31.gonggong.module.library.LibraryActivity;
import com.sky31.gonggong.module.main.FunctionGridViewAdapter;
import com.sky31.gonggong.module.main.HeaderImagesViewPagesAdapter;
import com.sky31.gonggong.module.swzl.SwzlActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment implements ArticleListView, Runnable {


    private static SecondFragment instance;
    @Bind(R.id.function_grid_view)
    GridView functionGridView;
    //    @Bind(R.id.pre_header_view)
//    ImageView preHeaderView;
//    @Bind(R.id.next_header_view)
//    ImageView nextHeaderView;
    @Bind(R.id.header_scrolling_image)
    ViewPager headerScrollingImage;
    @Bind(R.id.header_image_dot1)
    View headerImageDot1;
    @Bind(R.id.header_image_dot2)
    View headerImageDot2;
    @Bind(R.id.header_image_dot3)
    View headerImageDot3;
    @Bind(R.id.header_image_dot4)
    View headerImageDot4;


    // private static final String TAG = "SecondFragment";
    @Bind(R.id.header_image_dot5)
    View headerImageDot5;
    //private int startX;
    private ArticleListModel model;
    private ImageView[] imageViews;

    private View[] dotView;

    private Handler scrollHandle;

    public SecondFragment() {
        // Required empty public constructor
    }


    public static SecondFragment getInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ButterKnife.bind(this, view);

        instance = this;
        initDontView();

        if (imageViews == null) {
            initPagerData();
        }

        initGridView();

        return view;
    }

    private void initDontView() {

        dotView = new View[5];
        dotView[0] = headerImageDot1;
        dotView[1] = headerImageDot2;
        dotView[2] = headerImageDot3;
        dotView[3] = headerImageDot4;
        dotView[4] = headerImageDot5;

    }

    private void initPagerData() {

        imageViews = new ImageView[5];
        ArticlePresenter presenter = new ArticlePresenter(this);
        ArticleListQuery query = new ArticleListQuery();

        query.setCatname("焦点图移动端");
        query.setCheckID(0);
        query.setLimit(5);
        query.setOrder("id DESC");
        presenter.initReqService(query.getHashMap());
        presenter.getDatas();

    }

    public void initImgViewPagerHeight(float width) {
        //改变viewpager高度
        ViewGroup.LayoutParams param = headerScrollingImage.getLayoutParams();
        param.height = (int) (width / 2.25);
        headerScrollingImage.setLayoutParams(param);
    }

    private void initViewPagerImage() {


        final List<ArticleListModel.Data> dataList = model.getData();

        for (int i = 0; i < imageViews.length; i++) {

            ViewPager.LayoutParams imgParam = new ViewPager.LayoutParams();
            imageViews[i] = new ImageView(getContext());
            imageViews[i].setLayoutParams(imgParam);
            //获取bitmap
            int thumbWidth = headerScrollingImage.getWidth() > 720 ? 720 : headerScrollingImage.getWidth();
            int thumbHeight = (int) (thumbWidth / 2.25);
            Picasso.with(getContext()).load(dataList.get(i).getThumb()).
                    resize(thumbWidth, thumbHeight).
                    into(imageViews[i]);

            final int w = i;

            //设置跳转监听。
            imageViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //用于导图监听跳转
                    Intent intent = new Intent(getContext(), ArticleDetailActivity.class);
                    intent.putExtra("url", dataList.get(w).getUrl());
                    intent.putExtra("title", "文章详情");
                    startActivity(intent);
                }
            });
        }

        HeaderImagesViewPagesAdapter adapter =
                new HeaderImagesViewPagesAdapter(getContext(), imageViews);

        headerScrollingImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    position = 6;
                }

                dotView[(position - 1) % imageViews.length].
                        setBackgroundDrawable(getResources().
                                getDrawable(R.drawable.header_image_dot_shape));
                dotView[(position + 1) % imageViews.length].
                        setBackgroundDrawable(getResources().
                                getDrawable(R.drawable.header_image_dot_shape));
                dotView[(position) % imageViews.length].
                        setBackgroundDrawable(getResources().
                                getDrawable(R.drawable.header_image_dot_shape_selector));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        /***
         * 设置当导图滚动到最右边时候，不被父容器拦截。从而能够循环滚动。
         */
        headerScrollingImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float startX = 0;

                switch (event.getAction()) {


                    case MotionEvent.ACTION_DOWN:

                        startX = event.getX();
                    case MotionEvent.ACTION_MOVE:


                        if (headerScrollingImage.getCurrentItem() == imageViews.length - 1 && startX - v.getX() > 1300) {
                            headerScrollingImage.requestDisallowInterceptTouchEvent(true);
                            headerScrollingImage.setCurrentItem(0);
                            //headerScrollingImage.setPageTransformer();
                        }
                        break;
                    case MotionEvent.ACTION_UP:


                    case MotionEvent.ACTION_CANCEL:
                        headerScrollingImage.requestDisallowInterceptTouchEvent(false);
                }

                return false;
            }
        });
        headerScrollingImage.requestDisallowInterceptTouchEvent(true);
        headerScrollingImage.setAdapter(adapter);

        scrollHandle = new Handler();

        //设置滚动图 handler
        scrollHandle.post(this);

//
//        preHeaderView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                headerScrollingImage.
//                        setCurrentItem((headerScrollingImage.
//                                getCurrentItem()-1)%imageViews.length);
//            }
//        });
//        nextHeaderView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                headerScrollingImage.
//                        setCurrentItem((headerScrollingImage.
//                                getCurrentItem()+1)%imageViews.length);
//            }
//        });

    }

    private void initGridView() {
        FunctionGridViewAdapter adapter = new FunctionGridViewAdapter(getContext());
        functionGridView.setAdapter(adapter);

        functionGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(getContext(), CourseListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(getContext(), GradeActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(getContext(), LibraryActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        Toast.makeText(getContext(), "选修课作业", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        intent.setClass(getContext(), SwzlActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void getAriticleList(int code, ArticleListModel model) {

        Log.d("second_frg", code + "");
        if (code == 0) {
            this.model = model;
            initViewPagerImage();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (scrollHandle!=null){
            scrollHandle.removeCallbacks(this);
        }

    }

    @Override
    public void run() {
        int currentPage = headerScrollingImage.getCurrentItem();

        // Log.d("currentPage",currentPage+"");

        // Log.d(TAG, "run: "+imageViews.length);

        //设置图片轮播。递归。
        headerScrollingImage.setCurrentItem((currentPage + 1), true);
        scrollHandle.postDelayed(this, 5000);

    }
}
